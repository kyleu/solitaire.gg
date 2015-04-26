package models.game.variants

import java.util.UUID

import models.game._

object Yukon extends GameVariant.Description {
  override val key = "yukon"
  override val name = "Yukon"
  override val body = "..."

  def initialMoves(gameState: GameState, deck: Deck) = {
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)

    (2 to 7).foreach { i =>
      gameState.addCards(deck.getCards(i - 1), "tableau-" + i)
      gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
  }
}

class Yukon(override val gameId: UUID, override val seed: Int) extends GameVariant("yukon", Yukon, gameId, seed, Yukon.initialMoves) {
//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(dragFromConstraint = Some(Constraints.faceUp)))
}
