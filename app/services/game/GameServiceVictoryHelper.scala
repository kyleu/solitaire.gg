package services.game

import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

trait GameServiceVictoryHelper { this: GameService =>
  protected[this] def checkWinCondition() = {
    val ret = gameRules.victoryCondition.check(gameRules, gameState)
    if (completed.isEmpty && ret) {
      log.debug(s"Processing winning [$rules] game with seed [$seed].")
      completeGame(true).map {
        case (firstForRules, firstForSeed) => sendToAll("GameWon", GameWon(id, firstForRules, firstForSeed, getResult))
      }
    }
    ret
  }
}
