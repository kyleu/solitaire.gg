package services.game

import akka.actor.{ActorRef, PoisonPill}
import models._
import models.game.GameVariant
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.InstrumentedActor

class GameService(
  val id: String,
  val variant: String,
  val seed: Int,
  private val initialPlayers: List[(String, String, ActorRef)]
) extends InstrumentedActor with GameServiceTraceHelper with GameServiceCardHelper with Logging {
  log.info("Started game [" + variant + "] for players [" + initialPlayers.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  protected val playerConnections = collection.mutable.HashMap[String, (String, ActorRef)](initialPlayers.map(x => x._1 -> (x._2, x._3)): _*)
  protected val observerConnections = collection.mutable.HashMap.empty[String, (String, Option[String], ActorRef)]

  protected val gameVariant = GameVariant(variant, id, seed)
  protected val gameState = gameVariant.gameState
  protected val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    initialPlayers.foreach( s => gameState.addPlayer(s._1) )
    gameVariant.initialMoves()

    val message = GameStarted(id, self)
    playerConnections.foreach { c =>
      c._2._2 ! message
    }

    playerConnections.foreach { c =>
      c._2._2 ! GameJoined(id, initialPlayers.map(_._2), gameState.view(c._1))
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

  private def handleAddPlayer(connectionId: String, name: String, actorRef: ActorRef) {
    actorRef ! GameJoined(id, initialPlayers.map(_._2), gameState.view(name))
  }

  private def handleAddObserver(connectionId: String, name: String, as: Option[String], actorRef: ActorRef) {
    observerConnections(id) = (name, as, actorRef)
    val gs = as match {
      case Some(player) => gameState.view(player)
      case None => gameState
    }
    actorRef ! GameJoined(id, initialPlayers.map(_._2), gs)
  }

  private def handleConnectionStopped(id: String) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits._

    import scala.concurrent.duration._
    if(playerConnections.contains(id)) {
      log.info("Player connection [" + id + "] stopped.")
      playerConnections.remove(id)
    } else if(observerConnections.contains(id)) {
      log.info("Observer connection [" + id + "] stopped.")
      observerConnections.remove(id)
    } else {
      log.warn("Unknown connection [" + id + "] was stopped.")
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
      c._2._2 ! message
    }
    observerConnections.foreach { c =>
      c._2._3 ! message
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
