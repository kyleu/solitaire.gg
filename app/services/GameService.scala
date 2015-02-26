package services

import akka.actor.{Props, ActorRef}
import models.game.{Deck, GameState}
import models._
import utils.Logging
import utils.metrics.InstrumentedActor

import scala.util.Random

object GameService {
  private def props(gameType: String, seed: Int, players: List[String], connections: Map[String, ActorRef]) = Props(new GameService(gameType, seed, players, connections))
  private def masterRng = new Random()

  def joinGame(out: ActorRef, gameId: String, username: String, seed: Int = Math.abs(masterRng.nextInt())) = {
    props(gameId, seed, List(username), Map(username -> out))
  }
}

class GameService(gameType: String, seed: Int, players: List[String], connections: Map[String, ActorRef]) extends InstrumentedActor with Logging {
  log.info("Started game [" + gameType + "] for players [" + players.mkString(", ") + "] with seed [" + seed + "].")

  private val rng = new Random(new java.util.Random(seed))

  private val gameState = gameType match {
    case "klondike" => GameState.klondike(Deck.shuffled(rng))
    case "sandbox" => GameState.sandbox(Deck.shuffled(rng))
    case _ => throw new IllegalArgumentException("Unknown game type: [" + gameType + "].")
  }

  sendToAll(GameJoined(Nil, gameState))

  override def receiveRequest = {
    case gr: GameRequest =>
      log.debug("Handling [" + gr.request.getClass.getSimpleName + "] message from user [" + gr.username + "].")
      gr.request match {
        case sc: SelectCard => handleSelectCard(sc.card, sc.pile, sc.pileIndex)
        case sp: SelectPile => handleSelectPile(sp.pile)
        case mc: MoveCards => handleMoveCards(mc.cards, mc.src, mc.tgt)
        case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName + "].")
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

      val topCard = stock.cards.last
      if(topCard != card) {
        throw new IllegalArgumentException("Selected card [" + card + "] is not stock top card [" + topCard + "].")
      }
      stock.cards = stock.cards.dropRight(1)
      waste.addCard(card)
      card.u = true
      log.info("Stock card [" + card + "] moved to waste.")
      sendToAll(CardMoved(card.id, "stock", "waste", turnFaceUp = true))
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
      sendToAll(MessageSet(messages))
    }
  }

  private def sendToAll(responseMessage: ResponseMessage) = connections.foreach { c =>
    c._2 ! responseMessage
  }
}
