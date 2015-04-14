package services.game

import java.util.UUID

import akka.actor.{ PoisonPill, ActorRef }
import models._
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Akka
import services.GameHistoryService

trait GameServiceConnectionHelper { this: GameService =>
  protected[this] def handleAddPlayer(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef) {
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

  protected[this] def handleAddObserver(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef, as: Option[UUID]) {
    observerConnections += (PlayerRecord(accountId, name, Some(connectionId), Some(connectionActor)) -> as)
    val gs = as match {
      case Some(player) => gameState.view(player)
      case None => gameState
    }
    connectionActor ! GameJoined(id, gs, possibleMoves(None))
  }

  protected[this] def handleConnectionStopped(connectionId: UUID) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    import scala.concurrent.duration._

    playerConnections.find(_.connectionId == Some(connectionId)) match {
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

    val hasPlayer = playerConnections.exists(_.connectionId.isDefined) || observerConnections.exists(_._1.connectionId.isDefined)
    if(!hasPlayer) {
      Akka.system.scheduler.scheduleOnce(30.seconds, self, StopGameIfEmpty)
    }
  }

  protected[this] def handleStopGameIfEmpty() {
    val hasPlayer = playerConnections.exists(_.connectionId.isDefined) || observerConnections.exists(_._1.connectionId.isDefined)
    if(!hasPlayer) {
      self ! StopGame("timeout")
    }
  }

  protected[this] def handleStopGame(reason: String) {
    log.info("Stopping empty game [" + id + "] for reason [" + reason + "].")
    GameHistoryService.updateGameHistory(id, reason, moveCount, undoCount, redoCount, Some(new LocalDateTime))
    context.parent ! GameStopped(id)
    self ! PoisonPill
  }

  protected[this] def sendToAll(messages: Seq[ResponseMessage]): Unit = {
    if (messages.isEmpty) {
      log.info("No messages to send to all players.")
    } else if (messages.tail.isEmpty) {
      sendToAll(messages.headOption.getOrElse(throw new IllegalStateException()))
    } else {
      sendToAll(MessageSet(messages))
    }
  }

  protected[this] def sendToAll(message: ResponseMessage, registerUndoResponse: Boolean = true): Unit = {
    if (registerUndoResponse) {
      registerResponse(message)
    }
    playerConnections.foreach { c =>
      c.connectionActor.foreach(_ ! message)
    }
    observerConnections.foreach { c =>
      c._1.connectionActor.foreach(_ ! message)
    }
  }
}
