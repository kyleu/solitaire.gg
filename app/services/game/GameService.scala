package services.game

import akka.actor.{ActorRef, PoisonPill}
import models._
import org.joda.time.DateTime
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.InstrumentedActor

class GameService(id: String, gameType: String, seed: Int, initialSessions: List[(String, String, ActorRef)]) extends InstrumentedActor with Logging {
  log.info("Started game [" + gameType + "] for players [" + initialSessions.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  private val started = new DateTime()

  private val connections = collection.mutable.HashMap[String, ActorRef](initialSessions.map(x => x._1 -> x._3): _*)

  private val gameVariant = GameVariant(gameType, id, seed)
  private val gameState = gameVariant.gameState

  private val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    initialSessions.foreach( s => gameState.addPlayer(s._1) )
    gameVariant.initialMoves()
    sendToAll(GameStarted(id, self))
    connections.foreach { c =>
      c._2 ! GameJoined(id, initialSessions.map(_._2), gameState.view(c._1))
    }
  }

  override def receiveRequest = {
    case gr: GameRequest =>
      log.debug("Handling [" + gr.message.getClass.getSimpleName + "] message from user [" + gr.username + "].")
      try {
        gameMessages += gr.message
        gr.message match {
          case sc: SelectCard => handleSelectCard(sc.card, sc.pile, sc.pileIndex)
          case sp: SelectPile => handleSelectPile(sp.pile)
          case mc: MoveCards => handleMoveCards(mc.cards, mc.src, mc.tgt)
          case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName + "].")
        }
      } catch {
        case x: Exception =>
          log.error("Exception processing game request [" + gr + "].", x)
          sender() ! ServerError(x.getClass.getSimpleName, x.getMessage)
      }
    case im: InternalMessage =>
      log.debug("Handling [" + im.getClass.getSimpleName + "] internal message.")
      try {
        im match {
          case cs: ConnectionStopped => handleConnectionStopped(cs.id)
          case StopGameIfEmpty => handleStopGameIfEmpty()
          case gt: GameTrace => handleGameTrace()
          case _ => log.warn("GameService received unhandled internal message [" + im.getClass.getSimpleName + "].")
        }
      } catch {
        case x: Exception => log.error("Exception processing internal message [" + im + "].", x)
      }
    case x => log.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }

  private def handleSelectCard(cardId: String, pileId: String, pileIndex: Int) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if(!pile.cards.contains(card)) {
      log.warn("SelectCard for game [" + id + "]: Card [" + card.toString + "] is not part of the [" + pileId + "] pile.")
    }
    if(pile.behavior == "stock") {
      val waste = gameState.pilesById("waste")

      val messages = (0 to 2).flatMap { i =>
        val topCard = pile.cards.lastOption
        topCard match {
          case Some(tc) =>
            if(i == 0) {
              if(tc != card) {
                log.warn("SelectCard for game [" + id + "]: Selected card [" + card + "] is not stock top card [" + topCard + "].")
              }
            }
            pile.removeCard(tc)
            val revealed = gameState.revealCardToAll(tc)

            waste.addCard(tc)
            tc.u = true
            log.info("Stock card [" + tc + "] moved to waste.")
            revealed :+ CardMoved(tc.id, "stock", "waste", turnFaceUp = true)
          case None => Nil
        }
      }

      if(messages.isEmpty) {
        // noop
      } else if(messages.size == 1) {
        sendToAll(messages(0))
      } else {
        sendToAll(MessageSet(messages.toList))
      }
    } else if(pile.behavior == "tableau") {
      if(!card.u) {
        card.u = true
        sendToAll(CardRevealed(card))
      }
    } else {
      log.warn("Card [" + card + "] selected with no action.")
    }
  }

  private def handleSelectPile(pileId: String) {
    val pile = gameState.pilesById(pileId)
    if(pile.behavior == "stock") {
      if(pile.cards.length > 0) {
        log.warn("SelectPile [" + pileId + "] called on a non-empty deck.")
      }

      val waste = gameState.pilesById("waste")

      val messages = waste.cards.reverse.map { card =>
        waste.removeCard(card)
        pile.addCard(card)
        CardMoved(card.id, "waste", "stock", turnFaceDown = true)
      }
      sendToAll(MessageSet(messages))
    } else {
      log.warn("Pile [" + pileId + "] selected with no action.")
    }
  }

  private def handleMoveCards(cardIds: List[String], sourceId: String, targetId: String) {
    val cards = cardIds.map(gameState.cardsById)
    val source = gameState.pilesById(sourceId)
    val target = gameState.pilesById(targetId)

    for(c <- cards) {
      if(!source.cards.contains(c)) {
        throw new IllegalArgumentException("Card [" + c + "] is not a part of source pile [" + source.id + "].")
      }
    }

    if(source.canDragFrom(cards)) {
      if(target.canDragTo(cards)) {
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
      } else {
        log.warn("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] to pile [" + target.id + "].")
        sendToAll(CardMoveCancelled(cardIds, sourceId))
      }
    } else {
      log.warn("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] from pile [" + source.id + "].")
      sendToAll(CardMoveCancelled(cardIds, sourceId))
    }

  }

  private def handleConnectionStopped(id: String) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits._

    import scala.concurrent.duration._
    log.info("Connection [" + id + "] stopped.")
    connections.remove(id)
    Akka.system.scheduler.scheduleOnce(30.seconds, self, StopGameIfEmpty)
  }

  private def handleStopGameIfEmpty() {
    if(connections.isEmpty) {
      log.info("Stopping empty game [" + id + "] after timeout.")
      context.parent ! GameStopped(id)
      self ! PoisonPill
    }
  }

  private def handleGameTrace() {
    val ret = TraceResponse(List(
      "id" -> id,
      "variant" -> gameVariant.description.id,
      "seed" -> gameVariant.seed,
      "started" -> started,
      "connections" -> connections.keys.toList.sorted,
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }

  private def sendToAll(message: Any) = connections.foreach { c =>
    c._2 ! message
  }
}
