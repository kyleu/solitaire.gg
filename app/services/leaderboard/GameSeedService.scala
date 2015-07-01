package services.leaderboard

import models.database.queries.leaderboard.GameSeedQueries
import models.leaderboard.GameSeed
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.test.TestService
import utils.Logging

import scala.concurrent.Future

object GameSeedService extends Logging {
  def register(gs: GameSeed) = Database.transaction { conn =>
    Database.query(GameSeedQueries.getById(Seq(gs.rules, gs.seed)), Some(conn)).flatMap {
      case Some(existing) =>
        val update = if (existing.player != TestService.testUserId && gs.player == TestService.testUserId) {
          false
        } else if (existing.player == TestService.testUserId && gs.player != TestService.testUserId) {
          true
        } else if (gs.moves < existing.moves) {
          true
        } else if (gs.moves == existing.moves) {
          gs.elapsed <= existing.elapsed
        } else {
          false
        }
        if (update) {
          log.info(s"Overwriting existing winning [${gs.rules}] game with seed [${gs.seed}].")
          Database.execute(GameSeedQueries.UpdateGameSeed(gs), Some(conn)).map(x => true)
        } else {
          log.info(s"Not overwriting existing winning [${gs.rules}] game with seed [${gs.seed}].")
          Future.successful(false)
        }
      case None =>
        log.info(s"Inserting new winning [${gs.rules}] game with seed [${gs.seed}].")
        Database.execute(GameSeedQueries.insert(gs), Some(conn)).map(x => true)
    }.recover {
      case x =>
        log.error(s"Error processing winning [${gs.rules}] game with seed [${gs.seed}].", x)
        false
    }
  }
}
