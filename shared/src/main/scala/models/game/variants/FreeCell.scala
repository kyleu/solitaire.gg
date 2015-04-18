package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.constraints.Constraints
import models.game.pile._

object FreeCell extends GameVariant.Description {
  override val key = "freecell"
  override val name = "FreeCell"
  override val body = """
    Move all the cards to the home cells, using the free cells as placeholders.
    To win, make four stacks of cards on the home cells, one for each suit, stacked in order of rank, from lowest (ace) to highest (king).
  """
  override val layouts = Seq(
    Layout(
      width = 9.9,
      height = 5.0,
      piles = List(
        PileLocation("cell-1", 0.6, 0.7),
        PileLocation("cell-2", 1.7, 0.7),
        PileLocation("cell-3", 2.8, 0.7),
        PileLocation("cell-4", 3.9, 0.7),

        PileLocation("foundation-1", 6.0, 0.7),
        PileLocation("foundation-2", 7.1, 0.7),
        PileLocation("foundation-3", 8.2, 0.7),
        PileLocation("foundation-4", 9.3, 0.7),

        PileLocation("tableau-1", 1.1, 1.8),
        PileLocation("tableau-2", 2.2, 1.8),
        PileLocation("tableau-3", 3.3, 1.8),
        PileLocation("tableau-4", 4.4, 1.8),
        PileLocation("tableau-5", 5.5, 1.8),
        PileLocation("tableau-6", 6.6, 1.8),
        PileLocation("tableau-7", 7.7, 1.8),
        PileLocation("tableau-8", 8.8, 1.8)
      )
    )
  )
}

case class FreeCell(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = FreeCell

  private[this] val cellOptions = Pile.options.combine(PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Some(Constraints.klondikeSelectCard),
    dragToConstraint = Some(Constraints.empty),
    dragFromConstraint = Some(Constraints.topCardOnly)
  ))
  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(dragFromConstraint = Some(Constraints.klondikeDragFrom)))

  private[this] val piles = List(
    Pile("cell-1", "pile", cellOptions),
    Pile("cell-2", "pile", cellOptions),
    Pile("cell-3", "pile", cellOptions),
    Pile("cell-4", "pile", cellOptions),

    Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-4", "foundation", PileOptionsHelper.foundation),

    Pile("tableau-1", "tableau", tableauOptions),
    Pile("tableau-2", "tableau", tableauOptions),
    Pile("tableau-3", "tableau", tableauOptions),
    Pile("tableau-4", "tableau", tableauOptions),
    Pile("tableau-5", "tableau", tableauOptions),
    Pile("tableau-6", "tableau", tableauOptions),
    Pile("tableau-7", "tableau", tableauOptions),
    Pile("tableau-8", "tableau", tableauOptions)
  )

  private[this] val deck = newShuffledDecks()

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

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
