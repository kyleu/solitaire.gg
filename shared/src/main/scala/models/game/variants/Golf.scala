package models.game.variants

import java.util.UUID

import models.game.Deck
import models.game.GameState

object Golf extends GameVariant.Description {
  override val key = "golf"
  override val name = "Golf"
  override val body = """
    Build the bottom pile up or down regardless of suit. Ranking of cards is not continuous: an Ace may be built only on a 2, a King only on a Queen.
  """

  def initialMoves(gameState: GameState, deck: Deck) = {
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-7", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "foundation-1", reveal = true)
    gameState.addCards(deck.getCards(), "stock")
  }
}

class Golf(gameId: UUID, seed: Int) extends GameVariant("golf", Golf, gameId, seed, Golf.initialMoves) {
//  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(
//    selectCardConstraint = Some(Constraints.alternatingRankToFoundation),
//    dragFromConstraint = Some(Constraints.topCardOnly),
//    dragToConstraint = Some(Constraints.never),
//    selectCardAction = Some(SelectCardActions.drawToPile(1, "foundation"))
//  ))

//    Pile("foundation", "foundation", PileOptionsHelper.foundation.combine(PileOptions(
//      cardsShown = Some(4), direction = Some("r"), dragToConstraint = Some(Constraints.alternatingRank)
//    ))),
//    Pile("stock", "stock", PileOptionsHelper.stock(1, "foundation", None).combine(PileOptions(
//      cardsShown = Some(16),
//      direction = Some("r"),
//      selectPileConstraint = Some(Constraints.never)
//    )))
//  )
}
