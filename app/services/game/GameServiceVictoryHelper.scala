package services.game

import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

trait GameServiceVictoryHelper { this: GameService =>
  protected[this] def checkWinCondition() = {
    val ret = gameRules.victoryCondition.check(gameRules, gameState)
    if (completed.isEmpty && ret) {
      log.debug(s"Processing winning [$rules] game with seed [$seed].")
      completeGame(true).map {
        case (firstForRules, firstForSeed, stats) => sendToAll("GameWon", GameWon(id, rules, firstForRules, firstForSeed, getResult, stats))
      }
    }
    ret
  }
}
