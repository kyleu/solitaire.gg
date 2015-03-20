package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.constraints.Constraints
import models.game.pile._

object FreeCell extends GameVariant.Description {
  override val key = "freecell"
  override val name = "FreeCell"
  override val body = "Move all the cards to the home cells, using the free cells as placeholders. To win, make four stacks of cards on the home cells, one for each suit, stacked in order of rank, from lowest (ace) to highest (king)."
}

case class FreeCell(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = FreeCell

  private val cellOptions = Pile.options.combine(cardsShown = Some(1), dragToConstraint = Some(Constraints.empty))
  private val tableauOptions = Tableau.options.combine(dragFromConstraint = Some(Constraints.topCardOnly))

  private val piles = List(
    new Pile("cell-1", "pile", cellOptions),
    new Pile("cell-2", "pile", cellOptions),
    new Pile("cell-3", "pile", cellOptions),
    new Pile("cell-4", "pile", cellOptions),

    new Foundation("foundation-1"),
    new Foundation("foundation-2"),
    new Foundation("foundation-3"),
    new Foundation("foundation-4"),

    new Tableau("tableau-1", tableauOptions),
    new Tableau("tableau-2", tableauOptions),
    new Tableau("tableau-3", tableauOptions),
    new Tableau("tableau-4", tableauOptions),
    new Tableau("tableau-5", tableauOptions),
    new Tableau("tableau-6", tableauOptions),
    new Tableau("tableau-7", tableauOptions),
    new Tableau("tableau-8", tableauOptions)
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = Seq(
    Layout(
      width = 9.9,
      height = 5.0,
      piles = List(
        PileLocation("cell-1", 0.1, 0.2),
        PileLocation("cell-2", 1.2, 0.2),
        PileLocation("cell-3", 2.3, 0.2),
        PileLocation("cell-4", 3.4, 0.2),

        PileLocation("foundation-1", 5.5, 0.2),
        PileLocation("foundation-2", 6.6, 0.2),
        PileLocation("foundation-3", 7.7, 0.2),
        PileLocation("foundation-4", 8.8, 0.2),

        PileLocation("tableau-1", 0.6, 1.3),
        PileLocation("tableau-2", 1.7, 1.3),
        PileLocation("tableau-3", 2.8, 1.3),
        PileLocation("tableau-4", 3.9, 1.3),
        PileLocation("tableau-5", 5.0, 1.3),
        PileLocation("tableau-6", 6.1, 1.3),
        PileLocation("tableau-7", 7.2, 1.3),
        PileLocation("tableau-8", 8.3, 1.3)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, seed, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-7", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-8", reveal = true)
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
