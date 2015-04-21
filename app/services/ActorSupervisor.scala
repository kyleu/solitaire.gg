package services

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor._
import models._
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.MetricsServletActor

object ActorSupervisor extends Logging {
  lazy val instance = {
    import play.api.Play.current
    val instanceRef = Akka.system.actorOf(Props[ActorSupervisor], "supervisor")
    log.info("Actor Supervisor [" + instanceRef.path.toString + "] started for [" + utils.Config.projectId + "].")
    instanceRef
  }

  case class GameRecord(connections: Seq[(UUID, String)], actorRef: ActorRef, started: LocalDateTime)
  case class ConnectionRecord(accountId: UUID, name: String, actorRef: ActorRef, var activeGame: Option[UUID], started: LocalDateTime)
}

class ActorSupervisor extends ActorSupervisorHelper with Logging {
  import ActorSupervisor.{ ConnectionRecord, GameRecord }

  protected[this] val connections = collection.mutable.HashMap.empty[UUID, ConnectionRecord]
  protected[this] val connectionsCounter = metrics.counter("active-connections")

  protected[this] val games = collection.mutable.HashMap.empty[UUID, GameRecord]
  protected[this] val gamesCounter = metrics.counter("active-games")

  override def preStart() {
    context.actorOf(Props[MetricsServletActor], "metrics-servlet")
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }

  override def receiveRequest = {
    case cs: ConnectionStarted => handleConnectionStarted(cs.accountId, cs.username, cs.connectionId, cs.conn)
    case cs: ConnectionStopped => handleConnectionStopped(cs.connectionId)

    case cg: CreateGame => handleCreateGame(cg.variant, cg.connectionId, cg.seed)
    case cgj: ConnectionGameJoin => handleConnectionGameJoin(cgj.id, cgj.connectionId)
    case cgo: ConnectionGameObserve => handleConnectionGameObserve(cgo.id, cgo.connectionId, cgo.as)
    case gs: GameStopped => handleGameStopped(gs.id)

    case GetSystemStatus => handleGetSystemStatus()
    case ct: ConnectionTrace => handleConnectionTrace(ct)
    case ct: ClientTrace => handleClientTrace(ct)
    case gt: GameTrace => handleGameTrace(gt)

    case sm: InternalMessage => log.warn("Unhandled internal message [" + sm.getClass.getSimpleName + "] received.")
    case x => log.warn("ActorSupervisor encountered unknown message: " + x.toString)
  }

  private[this] def handleGetSystemStatus() = {
    val gameStatuses = games.toList.sortBy(_._1).map(x => x._1 -> x._2.connections)
    val connectionStatuses = connections.toList.sortBy(_._2.name).map(x => x._1 -> x._2.name)
    sender() ! SystemStatus(gameStatuses, connectionStatuses)
  }

  private[this] def handleConnectionTrace(ct: ConnectionTrace) = connections.find(_._1 == ct.id) match {
    case Some(g) => g._2.actorRef forward ct
    case None => sender() ! ServerError("Unknown Connection", ct.id.toString)
  }

  private[this] def handleClientTrace(ct: ClientTrace) = connections.find(_._1 == ct.id) match {
    case Some(g) => g._2.actorRef forward ct
    case None => sender() ! ServerError("Unknown Client", ct.id.toString)
  }

  private[this] def handleGameTrace(gt: GameTrace) = games.find(_._1 == gt.id) match {
    case Some(g) => g._2.actorRef forward gt
    case None => sender() ! ServerError("Unknown Game", gt.id.toString)
  }
}
