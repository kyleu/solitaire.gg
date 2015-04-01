package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.pile.{ Stock, Tableau }

object TrustyTwelve extends GameVariant.Description {
  override val key = "trusty-twelve"
  override val name = "Trusty Twelve"
  override val body = "Build the decks down, regardless of suit. Clicking the face down pile will deal cards to any empty spaces. Once you've dealt all the face down cards, you win."
}

case class TrustyTwelve(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = TrustyTwelve

  val tableauOptions = Tableau.options.combine(
    cardsShown = Some(2),
    selectCardConstraint = Some(Constraints.never),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.lowerRank)
  )

  val piles = List(
    new Stock("stock", Stock.options(0, "", None).combine(
      cardsShown = Some(19),
      direction = Some("r"),
      selectCardConstraint = Some(Constraints.topCardOnly),
      selectCardAction = Some(SelectCardActions.drawToEmptyPiles("tableau"))
    )),

    new Tableau("tableau-1", tableauOptions),
    new Tableau("tableau-2", tableauOptions),
    new Tableau("tableau-3", tableauOptions),
    new Tableau("tableau-4", tableauOptions),
    new Tableau("tableau-5", tableauOptions),
    new Tableau("tableau-6", tableauOptions),

    new Tableau("tableau-7", tableauOptions),
    new Tableau("tableau-8", tableauOptions),
    new Tableau("tableau-9", tableauOptions),
    new Tableau("tableau-10", tableauOptions),
    new Tableau("tableau-11", tableauOptions),
    new Tableau("tableau-12", tableauOptions)
  )

  val deck = Deck.shuffled(rng)

  val layouts = Seq(
    Layout(
      width = 6.7,
      height = 3.9,
      piles = List(
        PileLocation("stock", 0.6, 0.7),

        PileLocation("tableau-1", 0.6, 1.8),
        PileLocation("tableau-2", 1.7, 1.8),
        PileLocation("tableau-3", 2.8, 1.8),
        PileLocation("tableau-4", 3.9, 1.8),
        PileLocation("tableau-5", 5.0, 1.8),
        PileLocation("tableau-6", 6.1, 1.8),

        PileLocation("tableau-7", 0.6, 3.0),
        PileLocation("tableau-8", 1.7, 3.0),
        PileLocation("tableau-9", 2.8, 3.0),
        PileLocation("tableau-10", 3.9, 3.0),
        PileLocation("tableau-11", 5.0, 3.0),
        PileLocation("tableau-12", 6.1, 3.0)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, layouts)

  override def initialMoves() = {
    for (i <- 1 to 12) {
      gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.pilesById("stock").cards.isEmpty
}
