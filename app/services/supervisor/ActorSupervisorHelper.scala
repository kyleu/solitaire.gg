package services.supervisor

import java.util.UUID

import akka.actor.ActorRef
import models.user.User
import services.supervisor.ActorSupervisor.ConnectionRecord
import utils.DateUtils
import utils.metrics.InstrumentedActor

trait ActorSupervisorHelper extends InstrumentedActor with ActorSupervisorGameHelper { this: ActorSupervisor =>
  protected[this] def handleConnectionStarted(user: User, connectionId: UUID, conn: ActorRef) {
    log.debug(s"Connection [$connectionId] registered to [${user.username.getOrElse(user.id)}] with path [${conn.path}].")
    connections(connectionId) = ConnectionRecord(user.id, user.username.getOrElse("Guest"), conn, None, DateUtils.now)
    connectionsCounter.inc()
  }

  protected[this] def handleConnectionStopped(id: UUID) {
    connections.remove(id) match {
      case Some(conn) =>
        connectionsCounter.dec()
        log.debug(s"Connection [$id] [${conn.actorRef.path}] stopped.")
      case None => log.warn(s"Connection [$id] stopped but is not registered.")
    }
  }
}
