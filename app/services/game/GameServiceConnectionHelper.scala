package services.game

import java.util.UUID

import akka.actor.{PoisonPill, ActorRef}
import models._
import play.api.libs.concurrent.Akka

trait GameServiceConnectionHelper { this: GameService =>
  protected def handleAddPlayer(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef) {
    playerConnections.find(_.accountId == accountId) match {
      case Some(p) =>
        p.connectionActor.foreach(_ ! Disconnected("Joined from another connection."))
        p.connectionId = Some(connectionId)
        p.connectionActor = Some(connectionActor)
      case None =>
        playerConnections += PlayerRecord(accountId, name, Some(connectionId), Some(connectionActor))
    }

    gameState.addPlayer(accountId, name)
    connectionActor ! GameStarted(id, self)
    connectionActor ! GameJoined(id, gameState.view(accountId), possibleMoves(Some(accountId)))
  }

  protected def handleAddObserver(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef, as: Option[UUID]) {
    observerConnections += (PlayerRecord(accountId, name, Some(connectionId), Some(connectionActor)) -> as)
    val gs = as match {
      case Some(player) => gameState.view(player)
      case None => gameState
    }
    connectionActor ! GameJoined(id, gs, possibleMoves(None))
  }

  protected def handleConnectionStopped(connectionId: UUID) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    import scala.concurrent.duration._

    playerConnections.find(_.connectionId == connectionId) match {
      case Some(playerConnection) =>
        log.info("Player connection [" + connectionId + "] stopped.")
        playerConnection.connectionId = None
        playerConnection.connectionActor = None
      case None => // noop
    }
    observerConnections.find(_._1.connectionId.contains(connectionId)) match {
      case Some(observerConnection) =>
        log.info("Observer connection [" + connectionId + "] stopped.")
        observerConnection._1.connectionId = None
        observerConnection._1.connectionActor = None
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

  protected def sendToPlayer(player: UUID, message: ResponseMessage): Unit = {
    playerConnections.find(_.accountId == player).foreach { c =>
      c.connectionActor.foreach(_ ! message)
    }
    observerConnections.filter(o => o._2.isEmpty || o._2 == Some(player)).foreach { c =>
      if(c._2.isEmpty || c._2.get == player) {
        c._1.connectionActor.foreach(_ ! message)
      }
    }
  }

  protected def sendToPlayer(player: UUID, messages: Seq[ResponseMessage]): Unit = {
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
      c.connectionActor.foreach(_ ! message)
    }
    observerConnections.foreach { c =>
      c._1.connectionActor.foreach(_ ! message)
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
