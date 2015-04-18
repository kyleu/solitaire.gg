package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{ Pile, PileOptionsHelper, PileOptions }
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints

object Gypsy extends GameVariant.Description {
  override val key = "gypsy"
  override val name = "Gypsy"
  override val body = "..."
  override val layouts = Seq(
    Layout(
      width = 10.0,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),

        PileLocation("foundation-1", 1.7, 0.7),
        PileLocation("foundation-2", 2.8, 0.7),
        PileLocation("foundation-3", 3.9, 0.7),
        PileLocation("foundation-4", 5.0, 0.7),
        PileLocation("foundation-5", 6.1, 0.7),
        PileLocation("foundation-6", 7.2, 0.7),
        PileLocation("foundation-7", 8.3, 0.7),
        PileLocation("foundation-8", 9.4, 0.7),

        PileLocation("tableau-1", 1.7, 1.8),
        PileLocation("tableau-2", 2.8, 1.8),
        PileLocation("tableau-3", 3.9, 1.8),
        PileLocation("tableau-4", 5.0, 1.8),
        PileLocation("tableau-5", 6.1, 1.8),
        PileLocation("tableau-6", 7.2, 1.8),
        PileLocation("tableau-7", 8.3, 1.8),
        PileLocation("tableau-8", 9.4, 1.8)
      )
    )
  )
}

case class Gypsy(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Gypsy

  private[this] val drawPiles = (1 to 8).map("tableau-" + _).toSeq

  private[this] val stockOptions = new PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Some(Constraints.topCardOnly),
    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
  )

  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
    dragToConstraint = Some(Constraints.klondikeTableauDragTo(None))
  )

  private[this] val piles = {
    List(new Pile("stock", "stock", stockOptions)) ++
      (1 to 8).map(i => Pile("foundation-" + i, "foundation", PileOptionsHelper.foundation)) ++
      (1 to 8).map(i => Pile("tableau-" + i, "tableau", tableauOptions))
  }

  private[this] val deck = newShuffledDecks(2)

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    (1 to 8).foreach { i =>
      gameState.addCards(deck.getCards(2), "tableau-" + i)
      gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 8
}
