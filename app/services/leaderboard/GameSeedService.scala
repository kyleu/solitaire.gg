package services.leaderboard

import java.util.UUID

import models.queries.leaderboard.GameSeedQueries
import models.leaderboard.GameSeed
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.test.TestService
import utils.Logging

import scala.concurrent.Future

object GameSeedService extends Logging {
  def registerWin(rules: String, seed: Int, player: UUID, moves: Int, elapsedMs: Int, completed: LocalDateTime) = Database.transaction { conn =>
    Database.query(GameSeedQueries.getById(Seq(rules, seed)), Some(conn)).flatMap {
      case Some(existing) =>
        val update = if (existing.player.contains(TestService.testUserId) && player == TestService.testUserId) {
          false
        } else if (existing.player.contains(TestService.testUserId) && player != TestService.testUserId) {
          true
        } else if (existing.moves.isEmpty || existing.moves.exists(_ > moves)) {
          true
        } else if (existing.moves.contains(moves)) {
          elapsedMs <= existing.elapsed.getOrElse(Integer.MAX_VALUE)
        } else {
          false
        }
        if (update) {
          log.info(s"Overwriting existing winning [$rules] game with seed [$seed].")
          Database.execute(GameSeedQueries.UpdateGameSeedWin(rules, seed, player, moves, elapsedMs, completed), Some(conn)).map(x => (false, true))
        } else {
          log.info(s"Not overwriting existing winning [$rules] game with seed [$seed].")
          Future.successful(false -> false)
        }
      case None =>
        Database.query(GameSeedQueries.WinCount(rules)).flatMap { winCount =>
          log.info(s"Inserting new winning [$rules] game with seed [$seed].")
          val gs = GameSeed(rules, seed, 1, 1, Some(player), Some(moves), Some(elapsedMs), Some(completed))
          Database.execute(GameSeedQueries.insert(gs), Some(conn)).map { unused =>
            winCount.exists(x => x > 0) -> true
          }
        }
    }.recover {
      case x =>
        log.error(s"Error processing winning [$rules] game with seed [$seed].", x)
        false -> false
    }
  }

  def registerLoss(rules: String, seed: Int) = Database.execute(GameSeedQueries.UpdateGameSeedLoss(rules, seed)).flatMap {
    case affected if affected == 1 => Future.successful(true)
    case _ => Database.execute(GameSeedQueries.insert(GameSeed(rules, seed, 1, 0, None, None, None, None))).map(unused => true)
  }

  def getWinnableSeed(rules: String) = Database.query(GameSeedQueries.RandomWinnableSeed(rules))
}
