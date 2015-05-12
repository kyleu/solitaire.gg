package services

import java.util.UUID

import akka.actor.{ ActorRef, Props }
import models._
import org.joda.time.LocalDateTime
import services.ActorSupervisor.{ ConnectionRecord, GameRecord }
import services.game.GameService
import utils.metrics.InstrumentedActor

import scala.util.Random

trait ActorSupervisorHelper extends InstrumentedActor { this: ActorSupervisor =>
  private[this] def masterRng = new Random()

  protected[this] def handleConnectionStarted(accountId: UUID, username: String, connectionId: UUID, conn: ActorRef) {
    log.debug("Connection [" + connectionId + "] registered to [" + username + "] with path [" + conn.path + "].")
    connections(connectionId) = ConnectionRecord(accountId, username, conn, None, new LocalDateTime())
    connectionsCounter.inc()
  }

  protected[this] def handleConnectionStopped(id: UUID) {
    connections.remove(id) match {
      case Some(conn) =>
        connectionsCounter.dec()
        log.debug("Connection [" + id + "] [" + conn.actorRef.path + "] stopped.")
      case None => log.warn("Connection [" + id + "] stopped but is not registered.")
    }
  }

  protected[this] def handleCreateGame(rules: String, connectionId: UUID, seed: Option[Int]) {
    val id = UUID.randomUUID
    val s = Math.abs(seed.getOrElse(masterRng.nextInt()))
    val c = connections(connectionId)

    val started = new LocalDateTime()
    val pr = PlayerRecord(c.accountId, c.name, Some(connectionId), Some(c.actorRef))
    val actor = context.actorOf(Props(new GameService(id, rules, s, started, pr)), "game:" + id)

    c.activeGame = Some(id)
    games(id) = GameRecord(List((connectionId, c.name)), actor, started)
    gamesCounter.inc()
  }

  protected[this] def handleConnectionGameJoin(id: UUID, connectionId: UUID) = games.get(id) match {
    case Some(g) =>
      log.info("Joining game [" + id + "].")
      val c = connections(connectionId)
      c.activeGame = Some(id)
      g.actorRef ! AddPlayer(c.accountId, c.name, connectionId, c.actorRef)
    case None =>
      log.warn("Attempted to observe invalid game [" + id + "].")
      sender() ! ServerError("Invalid Game", id.toString)
  }

  protected[this] def handleConnectionGameObserve(gameId: UUID, connectionId: UUID, as: Option[UUID]) = {
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
        c.actorRef ! GameStarted(gameId, g.actorRef, g.started)
        g.actorRef ! AddObserver(c.accountId, c.name, connectionId, c.actorRef, as)
      case None =>
        log.warn("Attempted to observe invalid game [" + gameId + "].")
        sender() ! ServerError("Invalid Game", gameId.toString)
    }
  }

  protected[this] def handleGameStopped(id: UUID) = games.remove(id) match {
    case Some(g) =>
      gamesCounter.dec()
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
