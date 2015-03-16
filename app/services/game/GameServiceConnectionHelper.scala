package services.game

import java.util.UUID

import akka.actor.{PoisonPill, ActorRef}
import models._
import play.api.libs.concurrent.Akka

trait GameServiceConnectionHelper { this: GameService =>
  protected def handleAddPlayer(connectionId: UUID, name: String, actorRef: ActorRef) {
    playerConnections(name) = Some((connectionId, actorRef))
    actorRef ! GameJoined(id, playerConnections.keys.toSeq, gameState.view(name), possibleMoves(Some(name)))
  }

  protected def handleAddObserver(connectionId: UUID, name: String, as: Option[String], actorRef: ActorRef) {
    observerConnections(name -> as) = Some(connectionId -> actorRef)
    val gs = as match {
      case Some(player) => gameState.view(player)
      case None => gameState
    }
    actorRef ! GameJoined(id, playerConnections.keys.toSeq, gs, possibleMoves(None))
  }

  protected def handleConnectionStopped(connectionId: UUID) {
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

  protected def handleStopGameIfEmpty() {
    if(playerConnections.isEmpty && observerConnections.isEmpty) {
      log.info("Stopping empty game [" + id + "] after timeout.")
      context.parent ! GameStopped(id)
      self ! PoisonPill
    }
  }

  protected def sendToPlayer(player: String, message: ResponseMessage): Unit = {
    playerConnections(player).foreach { c =>
      c._2 ! message
    }
    observerConnections.filter(o => o._1._2.isEmpty || o._1._2 == Some(player)).foreach { c =>
      c._2.foreach(_._2 ! message)
    }
  }

  protected def sendToPlayer(player: String, messages: Seq[ResponseMessage]): Unit = {
    if(messages.isEmpty) {
      // noop
    } else if(messages.tail.isEmpty) {
      sendToPlayer(player, messages.head)
    } else {
      sendToPlayer(player, MessageSet(messages))
    }
  }

  protected def sendToAll(message: ResponseMessage): Unit = {
    playerConnections.foreach { c =>
      c._2.foreach(_._2 ! message)
    }
    observerConnections.foreach { c =>
      c._2.foreach(_._2 ! message)
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
