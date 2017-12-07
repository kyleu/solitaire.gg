package services.supervisor

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{Actor, ActorRef, OneForOneStrategy, SupervisorStrategy}
import models._
import java.time.LocalDateTime

import io.prometheus.client.{Counter, Gauge, Histogram}
import services.supervisor.ActorSupervisor.SocketRecord
import util.metrics.Instrumented
import util.{Application, DateUtils, Logging}

object ActorSupervisor {
  case class SocketRecord(userId: UUID, username: Option[String], actorRef: ActorRef, started: LocalDateTime)
  protected val sockets = collection.mutable.HashMap.empty[UUID, SocketRecord]
}

class ActorSupervisor(val app: Application) extends Actor with Logging {
  private[this] lazy val metricsName = util.Config.projectId + "_actor_supervisor"
  private[this] lazy val receiveHistogram = Histogram.build(metricsName + "_receive", s"Message metrics for [$metricsName]").labelNames("msg").register()
  private[this] lazy val errorCounter = Counter.build(metricsName + "_exception", s"Exception metrics for [$metricsName]").labelNames("msg", "ex").register()
  private[this] val socketsCount = Gauge.build(metricsName + "_active_connections", "Actor Supervisor active actors.").labelNames("id").register()

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }

  private[this] def time(msg: Any, f: => Unit) = Instrumented.timeReceive(msg, receiveHistogram, errorCounter)(f)

  override def receive = {
    case ss: SocketStarted => time(ss, handleSocketStarted(ss.userId, ss.username, ss.socketId, ss.conn))
    case ss: SocketStopped => time(ss, handleSocketStopped(ss.socketId))

    case GetSystemStatus => time(GetSystemStatus, handleGetSystemStatus())

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
