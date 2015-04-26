package models.game.variants

import java.util.UUID

import models.game._

object TrustyTwelve extends GameVariant.Description {
  override val key = "trustytwelve"
  override val name = "Trusty Twelve"
  override val body = """
    Build the decks down, regardless of suit.
    Clicking the face down pile will deal cards to any empty spaces. Once you've dealt all the face down cards, you win.
  """

  def initialMoves(gameState: GameState, deck: Deck) = {
    for (i <- 1 to 12) {
      gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
    gameState.addCards(deck.getCards(), "stock")
  }
}

class TrustyTwelve(override val gameId: UUID, override val seed: Int) extends GameVariant(
  "trustytwelve", TrustyTwelve, gameId, seed, TrustyTwelve.initialMoves
) {
  //  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(
  //    cardsShown = Some(2),
  //    selectCardConstraint = Some(Constraints.never),
  //    dragFromConstraint = Some(Constraints.topCardOnly),
  //    dragToConstraint = Some(Constraints.lowerRank)
  //  ))
  //
  //  private[this] val piles = List(
  //    Pile("stock", "stock", PileOptionsHelper.stock(0, "", None).combine(PileOptions(
  //      cardsShown = Some(19),
  //      direction = Some("r"),
  //      selectCardConstraint = Some(Constraints.topCardOnly),
  //      selectCardAction = Some(SelectCardActions.drawToEmptyPiles("tableau"))
  //    ))),
  //
  //    Pile("tableau-1", "tableau", tableauOptions),
  //    Pile("tableau-2", "tableau", tableauOptions),
  //    Pile("tableau-3", "tableau", tableauOptions),
  //    Pile("tableau-4", "tableau", tableauOptions),
  //    Pile("tableau-5", "tableau", tableauOptions),
  //    Pile("tableau-6", "tableau", tableauOptions),
  //
  //    Pile("tableau-7", "tableau", tableauOptions),
  //    Pile("tableau-8", "tableau", tableauOptions),
  //    Pile("tableau-9", "tableau", tableauOptions),
  //    Pile("tableau-10", "tableau", tableauOptions),
  //    Pile("tableau-11", "tableau", tableauOptions),
  //    Pile("tableau-12", "tableau", tableauOptions)
  //  )
}
