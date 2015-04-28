package models.game.variants

import models.game.GameState

object Yukon {
  def initialMoves(gameState: GameState) = {
    val deck = gameState.deck
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)

    (2 to 7).foreach { i =>
      gameState.addCards(deck.getCards(i - 1), "tableau-" + i)
      gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
  }
}

//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(dragFromConstraint = Some(Constraints.faceUp)))
