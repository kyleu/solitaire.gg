package services.game

import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.leaderboard.GameSeedService
import utils.DateUtils

trait GameServiceVictoryHelper { this: GameService =>
  protected[this] def checkWinCondition() = if (!gameWon && gameRules.victoryCondition.check(gameRules, gameState)) {
    gameWon = true
    setStatus("win")
    val completed = DateUtils.now
    val elapsed = (DateUtils.toMillis(completed) - DateUtils.toMillis(started)).toInt
    log.debug(s"Processing winning [$rules] game with seed [$seed].")
    GameSeedService.registerWin(rules, seed, player.userId, moveCount, elapsed, completed).map { firsts =>
      val (firstForRules, firstForSeed) = firsts
      sendToAll("GameWon", GameWon(id, firstForRules, firstForSeed))
    }
    true
  } else {
    false
  }
}
