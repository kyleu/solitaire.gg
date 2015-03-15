package services.game

import java.util.UUID

import akka.actor.{ActorRef, PoisonPill}
import models._
import models.game.GameVariant
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.InstrumentedActor

class GameService(
  val id: UUID,
  val variant: String,
  val seed: Int,
  private val initialPlayers: List[(String, UUID, ActorRef)]
) extends InstrumentedActor with GameServiceTraceHelper with GameServiceCardHelper with Logging {
  log.info("Started game [" + variant + "] for players [" + initialPlayers.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  protected val playerConnections = collection.mutable.HashMap[String, Option[(UUID, ActorRef)]](initialPlayers.map(x => x._1 -> Some((x._2, x._3))): _*)
  protected val observerConnections = collection.mutable.HashMap.empty[(String, Option[String]), Option[(UUID, ActorRef)]]

  protected val gameVariant = GameVariant(variant, id, seed)
  protected val gameState = gameVariant.gameState
  protected val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    initialPlayers.foreach( s => gameState.addPlayer(s._1) )
    gameVariant.initialMoves()

    val message = GameStarted(id, self)
    playerConnections.foreach { c =>
      c._2.map(_._2 ! message)
    }

    playerConnections.foreach { c =>
      c._2.map(_._2 ! GameJoined(id, initialPlayers.map(_._1), gameState.view(c._1)))
    }
  }

  override def receiveRequest = {
    case gr: GameRequest =>
      log.debug("Handling [" + gr.message.getClass.getSimpleName.replace("$", "") + "] message from user [" + gr.username + "].")
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
          case ap: AddPlayer => handleAddPlayer(ap.id, ap.name, ap.actorRef)
          case ap: AddObserver => handleAddObserver(ap.id, ap.name, ap.as, ap.actorRef)
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

  private def handleAddPlayer(connectionId: UUID, name: String, actorRef: ActorRef) {
    playerConnections(name) = Some((connectionId, actorRef))
    actorRef ! GameJoined(id, playerConnections.keys.toSeq, gameState.view(name))
  }

  private def handleAddObserver(connectionId: UUID, name: String, as: Option[String], actorRef: ActorRef) {
    observerConnections(name -> as) = Some(connectionId -> actorRef)
    val gs = as match {
      case Some(player) => gameState.view(player)
      case None => gameState
    }
    actorRef ! GameJoined(id, initialPlayers.map(_._1), gs)
  }

  private def handleConnectionStopped(connectionId: UUID) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits._
    import scala.concurrent.duration._

    playerConnections.find(_._2.exists(_._1 == connectionId)) match {
      case Some(playerConnection) =>
        log.info("Player connection [" + connectionId + "] stopped.")
        playerConnections(playerConnection._1) = None
      case None => // noop
    }
    observerConnections.find(_._2.exists(_._1 == connectionId)) match {
      case Some(observerConnection) =>
        log.info("Observer connection [" + connectionId + "] stopped.")
        observerConnections(observerConnection._1) = None
      case None => // noop
    }
    Akka.system.scheduler.scheduleOnce(30.seconds, self, StopGameIfEmpty)
  }

  private def handleStopGameIfEmpty() {
    if(playerConnections.isEmpty && observerConnections.isEmpty) {
      log.info("Stopping empty game [" + id + "] after timeout.")
      context.parent ! GameStopped(id)
      self ! PoisonPill
    }
  }

  protected def sendToAll(message: ResponseMessage): Unit = {
    playerConnections.foreach { c =>
      c._2.map(_._2 ! message)
    }
    observerConnections.foreach { c =>
      c._2.map(_._2 ! message)
    }
  }

  protected def sendToAll(messages: Seq[ResponseMessage]): Unit = {
    if(messages.isEmpty) {
      // noop
    } else if(messages.tail.isEmpty) {
      sendToAll(messages.head)
    } else {
      sendToAll(MessageSet(messages))
    }
  }
}
