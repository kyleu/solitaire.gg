package services.supervisor

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{ActorRef, OneForOneStrategy, SupervisorStrategy}
import models._
import java.time.LocalDateTime
import util.metrics.InstrumentedActor
import util.{Application, DateUtils, Logging}

object ActorSupervisor {
  case class SocketRecord(userId: UUID, username: Option[String], actorRef: ActorRef, started: LocalDateTime)
  protected val sockets = collection.mutable.HashMap.empty[UUID, SocketRecord]
}

class ActorSupervisor(val app: Application) extends InstrumentedActor with Logging {
  import services.supervisor.ActorSupervisor._

  private[this] val socketsCount = gauge("active_connections", "Actor Supervisor active connections.")

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }

  override def receiveRequest = {
    case ss: SocketStarted => handleSocketStarted(ss.userId, ss.username, ss.socketId, ss.conn)
    case ss: SocketStopped => handleSocketStopped(ss.socketId)

    case GetSystemStatus => handleGetSystemStatus()

    case im: InternalMessage => log.warn(s"Unhandled internal message [${im.getClass.getSimpleName}] received.")
    case x => log.warn(s"ActorSupervisor encountered unknown message: ${x.toString}")
  }

  private[this] def handleGetSystemStatus() = {
    val connectionStatuses = ActorSupervisor.sockets.toList.sortBy(_._2.username).map(x => x._1 -> x._2.username)
    sender() ! SystemStatus(connectionStatuses)
  }

  protected[this] def handleSocketStarted(userId: UUID, username: Option[String], socketId: UUID, socket: ActorRef) = {
    log.debug(s"Socket [$socketId] registered to [$userId] with path [${socket.path}].")
    ActorSupervisor.sockets(socketId) = SocketRecord(userId, username, socket, DateUtils.now)
    socketsCount.inc()
  }

  protected[this] def handleSocketStopped(id: UUID) = {
    ActorSupervisor.sockets.remove(id) match {
      case Some(sock) =>
        socketsCount.dec()
        log.debug(s"Connection [$id] [${sock.actorRef.path}] stopped.")
      case None => log.warn(s"Socket [$id] stopped but is not registered.")
    }
  }
}
