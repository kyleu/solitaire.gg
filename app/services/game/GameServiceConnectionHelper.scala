package services.game

import java.util.UUID

import akka.actor.ActorRef
import models._
import play.api.libs.concurrent.Akka

trait GameServiceConnectionHelper { this: GameService =>
  protected[this] def handleAddPlayer(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef) {
    if(player.accountId == accountId) {
      player.connectionActor.foreach(_ ! Disconnected("Joined from another connection."))
      player.connectionId = Some(connectionId)
      player.connectionActor = Some(connectionActor)
    } else {
      // playerConnections += PlayerRecord(accountId, name, Some(connectionId), Some(connectionActor))
      gameState.addPlayer(accountId, name)
      throw new NotImplementedError()
    }

    connectionActor ! GameStarted(id, self, started)
    connectionActor ! GameJoined(id, gameState.view(accountId), possibleMoves(Some(accountId)))
  }

  protected[this] def handleAddObserver(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef, as: Option[UUID]) {
    observerConnections += (PlayerRecord(accountId, name, Some(connectionId), Some(connectionActor)) -> as)
    val gs = as match {
      case Some(p) => gameState.view(p)
      case None => gameState
    }
    connectionActor ! GameJoined(id, gs, possibleMoves(None))
  }

  protected[this] def handleConnectionStopped(connectionId: UUID) {
    import play.api.Play.current
    import play.api.libs.concurrent.Execution.Implicits.defaultContext

    import scala.concurrent.duration._

    if(player.connectionId == Some(connectionId)) {
      log.info("Player connection [" + connectionId + "] stopped.")
      player.connectionId = None
      player.connectionActor = None
    }
    observerConnections.find(_._1.connectionId.contains(connectionId)) match {
      case Some(observerConnection) =>
        log.info("Observer connection [" + connectionId + "] stopped.")
        observerConnection._1.connectionId = None
        observerConnection._1.connectionActor = None
      case None => // noop
    }

    val hasPlayer = player.connectionId.isDefined || observerConnections.exists(_._1.connectionId.isDefined)
    if (!hasPlayer) {
      Akka.system.scheduler.scheduleOnce(30.seconds, self, StopGameIfEmpty)
    }
  }
}
