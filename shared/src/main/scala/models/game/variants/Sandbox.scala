package models.game.variants

import models.game.GameState

object Sandbox {
  //  override val completed = false
  //  override val maxPlayers = 3

  def initialMoves(gameState: GameState) = {
    gameState.addCards(gameState.deck.getCards(), "stock")
  }
}

