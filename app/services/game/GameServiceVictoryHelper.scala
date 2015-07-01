package services.game

import java.util.UUID

import models._
import models.leaderboard.GameSeed
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.leaderboard.GameSeedService
import utils.DateUtils

trait GameServiceVictoryHelper { this: GameService =>
  protected[this] def checkWinCondition() = if (!gameWon && gameRules.victoryCondition.check(gameRules, gameState)) {
    gameWon = true
    setStatus("win")
    val completed = DateUtils.now
    val elapsed = (DateUtils.toMillis(completed) - DateUtils.toMillis(started)).toInt
    val gs = GameSeed(
      rules = rules,
      seed = seed,
      player = player.userId,
      moves = moveCount,
      elapsed = elapsed,
      completed = completed
    )
    log.debug(s"Processing winning [${gs.rules}] game with seed [${gs.seed}].")
    GameSeedService.register(gs).map { firstForSeed =>
      sendToAll("GameWon", GameWon(id, firstForSeed))
    }
    true
  } else {
    false
  }
}
