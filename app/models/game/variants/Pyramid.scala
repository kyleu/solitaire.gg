package models.game.variants

import java.util.UUID

import models.game.Rank.King
import models.game._
import models.game.pile.actions.{ DragToActions, SelectCardActions }
import models.game.pile.constraints._
import models.game.pile._

object Pyramid extends GameVariant.Description {
  override val key = "pyramid"
  override val name = "Pyramid"
  override val body = "Build the bottom pile up or down regardless of suit. Ranking of cards is not continuous: an Ace may be built only on a 2, a King only on a Queen."
}

case class Pyramid(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Pyramid

  private val pileOptions = Waste.options.combine(
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.total(13, aceHigh = false)),
    selectCardConstraint = Some(Constraints.specificRank(King)),
    selectCardAction = Some(SelectCardActions.remove),
    dragToAction = Some(DragToActions.remove)
  )

  private def pileOptionsFor(emptyPiles: String*) = {
    val c = Constraints.pilesEmpty(emptyPiles: _*)
    pileOptions.copy(dragFromConstraint = c, dragToConstraint = c, selectCardConstraint = Constraints.allOf("pyramid-king", c, pileOptions.selectCardConstraint))
  }

  private val piles = List(
    new Stock("stock", Stock.options(1, "waste", Some("waste")).combine(selectPileConstraint = Some(Constraints.never))),
    new Waste("waste", pileOptions),

    new Tableau("pile-1-1", pileOptionsFor("pile-2-1", "pile-2-2")),

    new Tableau("pile-2-1", pileOptionsFor("pile-3-1", "pile-3-2")),
    new Tableau("pile-2-2", pileOptionsFor("pile-3-2", "pile-3-3")),

    new Tableau("pile-3-1", pileOptionsFor("pile-4-1", "pile-4-2")),
    new Tableau("pile-3-2", pileOptionsFor("pile-4-2", "pile-4-3")),
    new Tableau("pile-3-3", pileOptionsFor("pile-4-3", "pile-4-4")),

    new Tableau("pile-4-1", pileOptionsFor("pile-5-1", "pile-5-2")),
    new Tableau("pile-4-2", pileOptionsFor("pile-5-2", "pile-5-3")),
    new Tableau("pile-4-3", pileOptionsFor("pile-5-3", "pile-5-4")),
    new Tableau("pile-4-4", pileOptionsFor("pile-5-4", "pile-5-5")),

    new Tableau("pile-5-1", pileOptionsFor("pile-6-1", "pile-6-2")),
    new Tableau("pile-5-2", pileOptionsFor("pile-6-2", "pile-6-3")),
    new Tableau("pile-5-3", pileOptionsFor("pile-6-3", "pile-6-4")),
    new Tableau("pile-5-4", pileOptionsFor("pile-6-4", "pile-6-5")),
    new Tableau("pile-5-5", pileOptionsFor("pile-6-5", "pile-6-6")),

    new Tableau("pile-6-1", pileOptionsFor("pile-7-1", "pile-7-2")),
    new Tableau("pile-6-2", pileOptionsFor("pile-7-2", "pile-7-3")),
    new Tableau("pile-6-3", pileOptionsFor("pile-7-3", "pile-7-4")),
    new Tableau("pile-6-4", pileOptionsFor("pile-7-4", "pile-7-5")),
    new Tableau("pile-6-5", pileOptionsFor("pile-7-5", "pile-7-6")),
    new Tableau("pile-6-6", pileOptionsFor("pile-7-6", "pile-7-7")),

    new Tableau("pile-7-1", pileOptions),
    new Tableau("pile-7-2", pileOptions),
    new Tableau("pile-7-3", pileOptions),
    new Tableau("pile-7-4", pileOptions),
    new Tableau("pile-7-5", pileOptions),
    new Tableau("pile-7-6", pileOptions),
    new Tableau("pile-7-7", pileOptions)
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = Seq(
    Layout(
      width = 7.8,
      height = 4.3,
      piles = List(
        PileLocation("stock", 3.3, 3.6),
        PileLocation("waste", 4.4, 3.6),

        PileLocation("pile-1-1", 3.9, 0.7),

        PileLocation("pile-2-1", 3.3, 1.0),
        PileLocation("pile-2-2", 4.4, 1.0),

        PileLocation("pile-3-1", 2.8, 1.3),
        PileLocation("pile-3-2", 3.9, 1.3),
        PileLocation("pile-3-3", 5.0, 1.3),

        PileLocation("pile-4-1", 2.2, 1.6),
        PileLocation("pile-4-2", 3.3, 1.6),
        PileLocation("pile-4-3", 4.4, 1.6),
        PileLocation("pile-4-4", 5.5, 1.6),

        PileLocation("pile-5-1", 1.7, 1.9),
        PileLocation("pile-5-2", 2.8, 1.9),
        PileLocation("pile-5-3", 3.9, 1.9),
        PileLocation("pile-5-4", 5.0, 1.9),
        PileLocation("pile-5-5", 6.1, 1.9),

        PileLocation("pile-6-1", 1.1, 2.2),
        PileLocation("pile-6-2", 2.2, 2.2),
        PileLocation("pile-6-3", 3.3, 2.2),
        PileLocation("pile-6-4", 4.4, 2.2),
        PileLocation("pile-6-5", 5.5, 2.2),
        PileLocation("pile-6-6", 6.6, 2.2),

        PileLocation("pile-7-1", 0.6, 2.5),
        PileLocation("pile-7-2", 1.7, 2.5),
        PileLocation("pile-7-3", 2.8, 2.5),
        PileLocation("pile-7-4", 3.9, 2.5),
        PileLocation("pile-7-5", 5.0, 2.5),
        PileLocation("pile-7-6", 6.1, 2.5),
        PileLocation("pile-7-7", 7.2, 2.5)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, layouts)

  override def initialMoves() = {
    for (p <- gameState.piles) {
      if (p.id.startsWith("pile-")) {
        gameState.addCards(deck.getCards(1, turnFaceUp = true), p.id, reveal = true)
      }
    }
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "waste", reveal = true)
    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = !gameState.piles.exists(p => p.id.startsWith("pile-") && p.cards.nonEmpty)
}
