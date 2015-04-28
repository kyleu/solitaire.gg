package models.game.variants

import models.game.GameState

object Golf {
  def initialMoves(gameState: GameState) = {
    val deck = gameState.deck
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
