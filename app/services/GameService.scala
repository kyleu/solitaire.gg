package services

import akka.actor.{PoisonPill, ActorRef}
import models.game.{Deck, GameState}
import models._
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.InstrumentedActor

import scala.util.Random

class GameService(id: String, gameType: String, seed: Int, initialSessions: List[(String, String, ActorRef)]) extends InstrumentedActor with Logging {
  log.info("Started game [" + gameType + "] for players [" + initialSessions.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  private val rng = new Random(new java.util.Random(seed))

  private val usernames = initialSessions.map(x => x._1 -> x._2).toMap
  private val connections = collection.mutable.HashMap[String, ActorRef](initialSessions.map(x => x._1 -> x._3): _*)

  private val gameState = gameType match {
    case "klondike" => GameState.klondike(Deck.shuffled(rng))
    case "sandbox" => GameState.sandbox(Deck.shuffled(rng))
    case _ => throw new IllegalArgumentException("Unknown game type: [" + gameType + "].")
  }

  override def preStart() {
    context.parent ! GameStarted(id, self)
    sendToAll(GameJoined(Nil, gameState))
  }

  override def receiveRequest = {
    case gr: GameRequest =>
      log.debug("Handling [" + gr.message.getClass.getSimpleName + "] message from user [" + gr.username + "].")
      gr.message match {
        case sc: SelectCard => handleSelectCard(sc.card, sc.pile, sc.pileIndex)
        case sp: SelectPile => handleSelectPile(sp.pile)
        case mc: MoveCards => handleMoveCards(mc.cards, mc.src, mc.tgt)
        case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName + "].")
      }
    case im: InternalMessage =>
      log.debug("Handling [" + im.getClass.getSimpleName + "] internal message.")
      im match {
        case cs: ConnectionStopped => handleConnectionStopped(cs.id)
        case StopGameIfEmpty => handleStopGameIfEmpty()
        case _ => log.warn("GameService received unhandled internal message [" + im.getClass.getSimpleName + "].")
      }
    case x => log.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }

  private def handleSelectCard(cardId: String, pileId: String, pileIndex: Int) {
    val card = gameState.cardsById(cardId)
    if(pileId == "stock") {
      val stock = gameState.pilesById(pileId)
      if(!stock.cards.contains(card)) {
        throw new IllegalArgumentException("Card [" + card.toString + "] is not part of the [stock] deck.")
      }

      val waste = gameState.pilesById("waste")

      val messages = (0 to 2).map { i =>
        val topCard = stock.cards.last
        if(i == 0) {
          if(topCard != card) {
            throw new IllegalArgumentException("Selected card [" + card + "] is not stock top card [" + topCard + "].")
          }
        }
        stock.cards = stock.cards.dropRight(1)
        waste.addCard(topCard)
        topCard.u = true
        log.info("Stock card [" + topCard + "] moved to waste.")
        CardMoved(topCard.id, "stock", "waste", turnFaceUp = true)
      }

      if(messages.size == 1) {
        sendToAll(messages(0))
      } else {
        sendToAll(MessageSet(messages.toList))
      }
    } else {
      log.warn("Card [" + card + "] selected with no action.")
    }
  }

  private def handleSelectPile(pileId: String) {
    if(pileId == "stock") {
      val stock = gameState.pilesById(pileId)
      if(stock.cards.length > 0) {
        throw new IllegalArgumentException("SelectPile called on a non-empty deck.")
      }

      val waste = gameState.pilesById("waste")

      val messages = waste.cards.map { card =>
        waste.removeCard(card)
        stock.addCard(card)
        CardMoved(card.id, "waste", "stock", turnFaceDown = true)
      }
      sendToAll(MessageSet(messages.reverse))
    } else {
      log.warn("Pile [" + pileId + "] selected with no action.")
    }
  }

  private def handleMoveCards(cardIds: List[String], sourceId: String, targetId: String) {
    val cards = cardIds.map(gameState.cardsById)
    val source = gameState.pilesById(sourceId)
    val target = gameState.pilesById(targetId)

    if(rng.nextBoolean()) {
      sendToAll(CancelCardMove(cardIds, sourceId))
    } else {
      val messages = cards.map { card =>
        source.removeCard(card)
        target.addCard(card)
        CardMoved(card.id, sourceId, targetId)
      }

      if(messages.size == 1) {
        sendToAll(messages(0))
      } else {
        sendToAll(MessageSet(messages))
      }
    }
  }

  private def handleConnectionStopped(id: String) {
    import scala.concurrent.duration._
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits._
    log.info("Connection [" + id + "] stopped.")
    connections.remove(id)
    Akka.system.scheduler.scheduleOnce(1.seconds, self, StopGameIfEmpty)
  }

  private def handleStopGameIfEmpty() {
    if(connections.isEmpty) {
      log.info("Stopping game [" + id + "] after timeout.")
      context.parent ! GameStopped(id)
      self ! PoisonPill
    }
  }

  private def sendToAll(responseMessage: ResponseMessage) = connections.foreach { c =>
    c._2 ! responseMessage
  }
}
