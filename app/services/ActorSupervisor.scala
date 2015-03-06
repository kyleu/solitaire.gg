package services

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{ActorRef, OneForOneStrategy, Props, SupervisorStrategy}
import models._
import play.api.libs.concurrent.Akka
import services.game.GameService
import utils.Logging
import utils.metrics.{InstrumentedActor, MetricsServletActor}

import scala.util.Random

object ActorSupervisor extends Logging {
  lazy val instance =  {
    import play.api.Play.current
    val instanceRef = Akka.system.actorOf(Props[ActorSupervisor], "supervisor")
    log.info("Actor Supervisor [" + instanceRef.path.toString + "] started for [" + utils.Config.projectId + "].")
    instanceRef
  }

  private case class GameRecord(connections: Seq[(String, String)], actorRef: ActorRef)
  private case class ConnectionRecord(username: String, actorRef: ActorRef)
}

private class ActorSupervisor extends InstrumentedActor with Logging {
  import services.ActorSupervisor.{GameRecord, ConnectionRecord}

  private def masterRng = new Random()
  private val connections = collection.mutable.HashMap.empty[String, ConnectionRecord]
  private val games = collection.mutable.HashMap.empty[String, GameRecord]

  override def preStart() {
    context.actorOf(Props[MetricsServletActor], "metrics-servlet")
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }

  override def receiveRequest = {
    case cs: ConnectionStarted => handleConnectionStarted(cs.id, cs.username, cs.conn)
    case cs: ConnectionStopped => handleConnectionStopped(cs.id)
    case cg: CreateGame => handleCreateGame(cg.gameType, cg.session, cg.username, cg.conn)
    case gs: GameStopped => handleGameStopped(gs.id)

    case GetSystemStatus =>
      val gameStatuses = games.toList.sortBy(_._1).map(x => x._1 -> x._2.connections)
      val connectionStatuses = connections.toList.sortBy(_._2.username).map(x => x._1 -> x._2.username)
      sender() ! SystemStatus(gameStatuses, connectionStatuses)
    case gt: GameTrace => games.find(_._1 == gt.id) match {
      case Some(g) => g._2.actorRef forward gt
      case None => sender() ! ServerError("Unknown Game", gt.id)
    }
    case ct: ConnectionTrace => connections.find(_._1 == ct.id) match {
      case Some(g) => g._2.actorRef forward ct
      case None => sender() ! ServerError("Unknown Game", ct.id)
    }

    case sm: InternalMessage => log.warn("Unhandled internal message [" + sm.getClass.getSimpleName + "] received.")
    case x => log.warn("ActorSupervisor encountered unknown message: " + x.toString)
  }

  private def handleConnectionStarted(id: String, username: String, conn: ActorRef) {
    log.debug("Connection [" + id + "] registered to [" + conn.path + "].")
    connections(id) = ConnectionRecord(username, conn)
  }

  private def handleConnectionStopped(id: String) {
    connections.remove(id) match {
      case Some(conn) => log.debug("Connection [" + id + "] [" + conn.actorRef.path + "] stopped.")
      case None => log.warn("Connection [" + id + "] stopped but is not registered.")
    }
  }

  private def handleCreateGame(gameType: String, connectionId: String, username: String, conn: ActorRef) {
    val id = UUID.randomUUID.toString
    val seed = Math.abs(masterRng.nextInt())
    val actor = context.actorOf(Props(new GameService(id, gameType, seed, List((connectionId, username, conn)))), "game:" + id)
    games(id) = GameRecord(List((connectionId, username)), actor)
  }

  private def handleGameStopped(id: String) {
    games.remove(id)
  }
}
