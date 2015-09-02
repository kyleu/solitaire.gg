package services.game

trait GameServiceScoreHelper { this: GameService =>
  protected[this] def calculateScore() = {
    (gameState.cardsById.size * 50) - (moveCount * 10) - (undoHelper.undoCount * 100) - (elapsedMs / 1000)
  }
}
