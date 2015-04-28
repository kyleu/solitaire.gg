package models.game.variants

import models.game.GameState

object Canfield {
  def initialMoves(gameState: GameState) = {
    val deck = gameState.deck
    gameState.addCards(deck.getCards(34), "stock")
    gameState.addCards(deck.getCards(13, turnFaceUp = true), "reserve-1", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)
  }
}
