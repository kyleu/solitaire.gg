package services.game

import akka.actor.{ActorRef, PoisonPill}
import models._
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.InstrumentedActor

class GameService(
  val id: String,
  val gameType: String,
  val seed: Int,
  protected val initialSessions: List[(String, String, ActorRef)]
) extends InstrumentedActor with GameServiceTraceHelper with GameServiceCardHelper with Logging {
  log.info("Started game [" + gameType + "] for players [" + initialSessions.map(_._2).mkString(", ") + "] with seed [" + seed + "].")

  protected val connections = collection.mutable.HashMap[String, (String, ActorRef)](initialSessions.map(x => x._1 -> (x._2, x._3)): _*)
  protected val observers = collection.mutable.HashMap.empty[String, (String, ActorRef)]

  protected val gameVariant = GameVariant(gameType, id, seed)
  protected val gameState = gameVariant.gameState
  protected val gameMessages = collection.mutable.ArrayBuffer.empty[GameMessage]

  override def preStart() {
    initialSessions.foreach( s => gameState.addPlayer(s._1) )
    gameVariant.initialMoves()
    sendToAll(GameStarted(id, self))
    connections.foreach { c =>
      c._2._2 ! GameJoined(id, initialSessions.map(_._2), gameState.view(c._1))
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

  protected def sendToAll(message: InternalMessage): Unit = connections.foreach { c =>
    c._2._2 ! message
  }

  protected def sendToAll(message: ResponseMessage): Unit = connections.foreach { c =>
    c._2._2 ! message
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
