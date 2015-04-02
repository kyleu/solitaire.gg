package services

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{ ActorRef, OneForOneStrategy, Props, SupervisorStrategy }
import models._
import play.api.libs.concurrent.Akka
import services.game.{ PlayerRecord, GameService }
import utils.Logging
import utils.metrics.{ InstrumentedActor, MetricsServletActor }

import scala.util.Random

object ActorSupervisor extends Logging {
  lazy val instance = {
    import play.api.Play.current
    val instanceRef = Akka.system.actorOf(Props[ActorSupervisor], "supervisor")
    log.info("Actor Supervisor [" + instanceRef.path.toString + "] started for [" + utils.Config.projectId + "].")
    instanceRef
  }

  private case class GameRecord(connections: Seq[(UUID, String)], actorRef: ActorRef)
  private case class ConnectionRecord(accountId: UUID, name: String, actorRef: ActorRef, var activeGame: Option[UUID])
}

private class ActorSupervisor extends InstrumentedActor with Logging {
  import services.ActorSupervisor.{ GameRecord, ConnectionRecord }

  private def masterRng = new Random()
  private val connections = collection.mutable.HashMap.empty[UUID, ConnectionRecord]
  private val games = collection.mutable.HashMap.empty[UUID, GameRecord]

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

    case GetSystemStatus =>
      val gameStatuses = games.toList.sortBy(_._1).map(x => x._1 -> x._2.connections)
      val connectionStatuses = connections.toList.sortBy(_._2.name).map(x => x._1 -> x._2.name)
      sender() ! SystemStatus(gameStatuses, connectionStatuses)
    case ct: ConnectionTrace => connections.find(_._1 == ct.id) match {
      case Some(g) => g._2.actorRef forward ct
      case None => sender() ! ServerError("Unknown Connection", ct.id.toString)
    }
    case ct: ClientTrace => connections.find(_._1 == ct.id) match {
      case Some(g) => g._2.actorRef forward ct
      case None => sender() ! ServerError("Unknown Client", ct.id.toString)
    }
    case gt: GameTrace => games.find(_._1 == gt.id) match {
      case Some(g) => g._2.actorRef forward gt
      case None => sender() ! ServerError("Unknown Game", gt.id.toString)
    }

    case sm: InternalMessage => log.warn("Unhandled internal message [" + sm.getClass.getSimpleName + "] received.")
    case x => log.warn("ActorSupervisor encountered unknown message: " + x.toString)
  }

  private def handleConnectionStarted(accountId: UUID, username: String, connectionId: UUID, conn: ActorRef) {
    log.debug("Connection [" + connectionId + "] registered to [" + username + "] with path [" + conn.path + "].")
    connections(connectionId) = ConnectionRecord(accountId, username, conn, None)
  }

  private def handleConnectionStopped(id: UUID) {
    connections.remove(id) match {
      case Some(conn) => log.debug("Connection [" + id + "] [" + conn.actorRef.path + "] stopped.")
      case None => log.warn("Connection [" + id + "] stopped but is not registered.")
    }
  }

  private def handleCreateGame(variant: String, connectionId: UUID, seed: Option[Int]) {
    val id = UUID.randomUUID
    val s = Math.abs(seed.getOrElse(masterRng.nextInt()))
    val c = connections(connectionId)

    val actor = context.actorOf(Props(new GameService(id, variant, s, List(
      PlayerRecord(c.accountId, c.name, Some(connectionId), Some(c.actorRef))
    ))), "game:" + id)

    c.activeGame = Some(id)
    games(id) = GameRecord(List((connectionId, c.name)), actor)
  }

  private def handleConnectionGameJoin(id: UUID, connectionId: UUID) = games.get(id) match {
    case Some(g) =>
      log.info("Joining game [" + id + "].")
      val c = connections(connectionId)
      c.activeGame = Some(id)
      g.actorRef ! AddPlayer(c.accountId, c.name, connectionId, c.actorRef)
    case None =>
      log.warn("Attempted to observe invalid game [" + id + "].")
      sender() ! ServerError("Invalid Game", id.toString)
  }

  private def handleConnectionGameObserve(gameId: UUID, connectionId: UUID, as: Option[UUID]) = {
    val game = if (gameId == UUID.fromString("00000000-0000-0000-0000-000000000000")) {
      games.headOption.map(_._2)
    } else {
      games.get(gameId)
    }
    game match {
      case Some(g) =>
        log.info("Connection [" + connectionId + "] is observing game [" + gameId + "].")
        val c = connections(connectionId)
        c.activeGame = Some(gameId)
        c.actorRef ! GameStarted(gameId, g.actorRef)
        g.actorRef ! AddObserver(c.accountId, c.name, connectionId, c.actorRef, as)
      case None =>
        log.warn("Attempted to observe invalid game [" + gameId + "].")
        sender() ! ServerError("Invalid Game", gameId.toString)
    }
  }

  private def handleGameStopped(id: UUID) = games.remove(id) match {
    case Some(g) =>
      g.connections.foreach { c =>
        connections.get(c._1).foreach { cr =>
          cr.activeGame = None
          cr.actorRef ! GameStopped(id)
        }
      }
      log.debug("Game [" + id + "] stopped.")
    case None => log.warn("Attempted to stop missing game [" + id + "].")
  }
}
