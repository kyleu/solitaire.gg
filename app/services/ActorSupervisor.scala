package services

import java.util.UUID

import akka.actor.SupervisorStrategy.Stop
import akka.actor.{ActorRef, OneForOneStrategy, Props, SupervisorStrategy}
import models._
import play.api.libs.concurrent.Akka
import utils.Logging
import utils.metrics.{InstrumentedActor, MetricsServletActor}

import scala.util.Random

object ActorSupervisor extends Logging {
  lazy val instance =  {
    import play.api.Play.current
    val instanceRef = Akka.system.actorOf(Props[ActorSupervisor], "supervisor")
    log.info("Actor Supervisor [" + instanceRef.path.toString + "] started for [scalataire].")
    instanceRef
  }
}

private class ActorSupervisor extends InstrumentedActor with Logging {
  private def masterRng = new Random()
  private val connections = collection.mutable.HashMap.empty[String, ActorRef]
  private val games = collection.mutable.HashMap.empty[String, ActorRef]

  override def preStart() {
    context.actorOf(Props[MetricsServletActor], "metrics-servlet")
  }

  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    case _ => Stop
  }

  override def receiveRequest = {
    case cs: ConnectionStarted => handleConnectionStarted(cs.id, cs.conn)
    case cs: ConnectionStopped => handleConnectionStopped(cs.id)

    case cg: CreateGame => handleCreateGame(cg.gameType, cg.session, cg.username, cg.conn)

    case gs: GameStopped => handleGameStopped(gs.id)

    case GetSystemStatus => sender() ! SystemStatus(games.keys.toList.sorted, connections.keys.toList.sorted)

    case sm: InternalMessage => log.warn("Unhandled internal message [" + sm.getClass.getSimpleName + "] received.")
    case x => log.warn("ActorSupervisor encountered unknown message: " + x.toString)
  }

  private def handleConnectionStarted(id: String, conn: ActorRef) {
    log.debug("Connection [" + id + "] registered to [" + conn.path + "].")
    connections(id) = conn
  }

  private def handleConnectionStopped(id: String) {
    connections.remove(id) match {
      case Some(conn) => log.debug("Connection [" + id + "] [" + conn.path + "] stopped.")
      case None => log.warn("Connection [" + id + "] stopped but is not registered.")
    }
  }

  private def handleCreateGame(gameType: String, session: String, username: String, conn: ActorRef) {
    val id = UUID.randomUUID.toString
    val seed = Math.abs(masterRng.nextInt())
    val actor = context.actorOf(Props(new GameService(id, gameType, seed, List((session, username, conn)))))
    games(id) = actor
  }

  private def handleGameStopped(id: String) {
    games.remove(id)
  }
}
