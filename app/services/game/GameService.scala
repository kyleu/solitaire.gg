package services.game

import java.util.UUID

import akka.actor.ActorRef
import models._
import models.game.variants.GameVariant

case class PlayerRecord(accountId: UUID, name: String, var connectionId: Option[UUID], var connectionActor: Option[ActorRef])

class GameService(val id: UUID, val variant: String, val seed: Int, private[this] val initialPlayers: List[PlayerRecord]) extends GameServiceHelper {
  log.info("Started game [" + variant + "] for players [" + initialPlayers.map(_.name).mkString(", ") + "] with seed [" + seed + "].")

  val playerConnections = collection.mutable.ArrayBuffer[PlayerRecord](initialPlayers: _*)
  val observerConnections = collection.mutable.ArrayBuffer.empty[(PlayerRecord, Option[UUID])]

  val gameVariant = GameVariant(variant, id, seed)
  val gameState = gameVariant.gameState

  initialPlayers.foreach { p =>
    gameState.addPlayer(p.accountId, p.name)
  }

  val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    gameVariant.initialMoves()

    playerConnections.foreach { c =>
      c.connectionActor.foreach(_ ! GameStarted(id, self))
      c.connectionActor.foreach(_ ! GameJoined(id, gameState.view(c.accountId), possibleMoves(Some(c.accountId))))
    }
  }

  override def receiveRequest = {
    case gr: GameRequest => handleGameRequest(gr)
    case im: InternalMessage => handleInternalMessage(im)
    case x => log.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }

  private[this] def handleGameRequest(gr: GameRequest) = {
    //log.debug("Handling [" + gr.message.getClass.getSimpleName.replace("$", "") + "] message from user [" + gr.accountId + "] for game [" + id + "].")
    try {
      gameMessages += gr.message
      gr.message match {
        case GetPossibleMoves => handleGetPossibleMoves(gr.accountId)
        case sc: SelectCard => handleSelectCard(gr.accountId, sc.card, sc.pile)
        case sp: SelectPile => handleSelectPile(gr.accountId, sp.pile)
        case mc: MoveCards => handleMoveCards(gr.accountId, mc.cards, mc.src, mc.tgt)
        case Undo => handleUndo(gr.accountId)
        case Redo => handleRedo(gr.accountId)
        case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName.replace("$", "") + "].")
      }
    } catch {
      case x: Exception =>
        log.error("Exception processing game request [" + gr + "].", x)
        sender() ! ServerError(x.getClass.getSimpleName, x.getMessage)
    }
  }

  private[this] def handleInternalMessage(im: InternalMessage) = {
    //log.debug("Handling [" + im.getClass.getSimpleName.replace("$", "") + "] internal message for game [" + id + "].")
    try {
      im match {
        case ap: AddPlayer => handleAddPlayer(ap.accountId, ap.name, ap.connectionId, ap.connectionActor)
        case ao: AddObserver => handleAddObserver(ao.accountId, ao.name, ao.connectionId, ao.connectionActor, ao.as)
        case cs: ConnectionStopped => handleConnectionStopped(cs.connectionId)
        case sg: StopGame => handleStopGame(sg.reason)
        case StopGameIfEmpty => handleStopGameIfEmpty()
        case gt: GameTrace => handleGameTrace()
        case _ => log.warn("GameService received unhandled internal message [" + im.getClass.getSimpleName + "].")
      }
    } catch {
      case x: Exception => log.error("Exception processing internal message [" + im + "].", x)
    }
  }
}
