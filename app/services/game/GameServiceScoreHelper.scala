package services.game

trait GameServiceScoreHelper { this: GameService =>
  protected[this] def calculateScore() = {
    val baseScore = gameState.cardsById.size * 50
    val moveDebit = moveCount * 10
    val undoDebit = undoHelper.undoCount * 100
    val timeDebit = elapsed.getOrElse(0)

    val score = baseScore - moveDebit - undoDebit - timeDebit

    Math.max(1, score)
  }
}
