package services.supervisor

import java.util.UUID

import akka.actor.Props
import models._
import models.user.PlayerRecord
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.game.GameService
import services.leaderboard.GameSeedService
import services.supervisor.ActorSupervisor.GameRecord
import utils.DateUtils

import scala.concurrent.Future
import scala.util.Random

trait ActorSupervisorGameHelper { this: ActorSupervisor =>
  private[this] def masterRng = new Random()

  protected[this] def handleCreateGame(rules: String, connectionId: UUID, seed: Option[Int], testGame: Boolean, autoFlipOption: Boolean) {
    val id = UUID.randomUUID
    val c = connections(connectionId)
    val s = if (seed.contains(-1)) {
      GameSeedService.getWinnableSeed(rules).map {
        case Some(winnableSeed) => winnableSeed
        case None =>
          c.actorRef ! Notification(None, "No winnable games are available for this game, so we've started a random game instead. Good luck!.")
          masterRng.nextInt()
      }
    } else {
      Future.successful(Math.abs(seed.getOrElse(masterRng.nextInt())))
    }

    s.map { finalSeed =>
      val started = DateUtils.now
      val pr = PlayerRecord(c.userId, c.name, Some(connectionId), Some(c.actorRef), autoFlipOption)
      val actor = context.actorOf(Props(new GameService(id, rules, finalSeed, started, pr, testGame)), s"game:$id")

      c.activeGame = Some(id)
      games(id) = GameRecord(List((connectionId, c.name)), actor, started)
      gamesCounter.inc()
    }
  }

  protected[this] def handleConnectionGameJoin(id: UUID, connectionId: UUID, autoFlipOption: Boolean) = games.get(id) match {
    case Some(g) =>
      log.info(s"Joining game [$id].")
      val c = connections(connectionId)
      c.activeGame = Some(id)
      g.actorRef ! AddPlayer(c.userId, c.name, connectionId, c.actorRef, autoFlipOption)
    case None =>
      log.warn(s"Attempted to join invalid game [$id].")
      sender() ! ServerError("Invalid Game", id.toString)
  }

  protected[this] def handleConnectionGameObserve(gameId: UUID, connectionId: UUID, as: Option[UUID]) = {
    val game = if (gameId == services.test.TestService.testGameId) {
      games.headOption.map(_._2)
    } else {
      games.get(gameId)
    }
    game match {
      case Some(g) =>
        log.info(s"Connection [$connectionId] is observing game [$gameId].")
        val c = connections(connectionId)
        c.activeGame = Some(gameId)
        c.actorRef ! GameStarted(gameId, g.actorRef, g.started)
        g.actorRef ! AddObserver(c.userId, c.name, connectionId, c.actorRef, as)
      case None =>
        log.warn(s"Attempted to observe invalid game [$gameId].")
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
      log.debug(s"Game [$id] stopped.")
    case None => log.warn(s"Attempted to stop missing game [$id].")
  }
}
