package services.game

import java.util.UUID

import akka.actor.ActorRef
import models._
import models.game.variants.GameVariant
import utils.Logging
import utils.metrics.InstrumentedActor

class GameService(
  val id: UUID,
  val variant: String,
  val seed: Int,
  private val initialPlayers: List[(String, UUID, ActorRef)]
) extends InstrumentedActor with GameServiceHelper with Logging {
  log.info("Started game [" + variant + "] for players [" + initialPlayers.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  val playerConnections = collection.mutable.HashMap[String, Option[(UUID, ActorRef)]](initialPlayers.map(x => x._1 -> Some((x._2, x._3))): _*)
  val observerConnections = collection.mutable.HashMap.empty[(String, Option[String]), Option[(UUID, ActorRef)]]

  val gameVariant = GameVariant(variant, id, seed)
  val gameState = gameVariant.gameState
  val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    initialPlayers.foreach( s => gameState.addPlayer(s._1) )
    gameVariant.initialMoves()

    val message = GameStarted(id, self)
    playerConnections.foreach { c =>
      c._2.foreach(_._2 ! message)
    }

    playerConnections.foreach { c =>
      c._2.foreach(_._2 ! GameJoined(id, initialPlayers.map(_._1), gameState.view(c._1), possibleMoves(Some(c._1))))
    }
  }

  override def receiveRequest = {
    case gr: GameRequest =>
      log.debug("Handling [" + gr.message.getClass.getSimpleName.replace("$", "") + "] message from user [" + gr.player + "] for game [" + id + "].")
      try {
        gameMessages += gr.message
        gr.message match {
          case GetPossibleMoves => handleGetPossibleMoves(gr.player)
          case sc: SelectCard => handleSelectCard(gr.player, sc.card, sc.pile, sc.pileIndex)
          case sp: SelectPile => handleSelectPile(gr.player, sp.pile)
          case mc: MoveCards => handleMoveCards(gr.player, mc.cards, mc.src, mc.tgt)
          case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName.replace("$", "") + "].")
        }
      } catch {
        case x: Exception =>
          log.error("Exception processing game request [" + gr + "].", x)
          sender() ! ServerError(x.getClass.getSimpleName, x.getMessage)
      }
    case im: InternalMessage =>
      log.debug("Handling [" + im.getClass.getSimpleName.replace("$", "") + "] internal message for game [" + id + "].")
      try {
        im match {
          case ap: AddPlayer => handleAddPlayer(ap.id, ap.name, ap.actorRef)
          case ao: AddObserver => handleAddObserver(ao.id, ao.name, ao.as, ao.actorRef)
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
}
