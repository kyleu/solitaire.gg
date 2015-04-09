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
  override val body = """
    Build the bottom pile up or down regardless of suit. Ranking of cards is not continuous: an Ace may be built only on a 2, a King only on a Queen.
  """
}

case class Pyramid(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Pyramid

  private[this] val pileOptions = PileOptionsHelper.waste.combine(PileOptions(
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.total(13, aceHigh = false)),
    selectCardConstraint = Some(Constraints.specificRank(King)),
    selectCardAction = Some(SelectCardActions.drawToPile(1, "graveyard")),
    dragToAction = Some(DragToActions.remove())
  ))

  private[this] def pileOptionsFor(emptyPiles: String*) = {
    val c = Some(Constraints.pilesEmpty(emptyPiles: _*))
    pileOptions.copy(
      dragFromConstraint = c,
      dragToConstraint = c,
      selectCardConstraint = Some(Constraints.allOf("pyramid-king", c.orNull, pileOptions.selectCardConstraint.orNull))
    )
  }

  private[this] val piles = List(
    new Pile("graveyard", "graveyard", PileOptionsHelper.empty),

    new Pile("stock", "stock", PileOptionsHelper.stock(1, "waste", Some("waste")).combine(PileOptions(selectPileConstraint = Some(Constraints.never)))),
    new Pile("waste", "waste", pileOptions)
  ) ++
    (1 to 7).flatMap { i =>
      (1 to i).map { j =>
        val po = if(i == 7) { pileOptions } else { pileOptionsFor("pile-" + i + "-" + j, "pile-" + i + "-" + (j + 1)) }
        new Pile("pile-1-" + j, "tableau", pileOptions)
      }
    }

  private[this] val deck = Deck.shuffled(rng)

  private[this] val layouts = Seq(
    Layout(
      width = 7.8,
      height = 4.3,
      piles = List(
        PileLocation("graveyard", -1.0, -1.0),

        PileLocation("stock", 3.3, 3.6),
        PileLocation("waste", 4.4, 3.6),

        PileLocation("pile-1-1", 3.9, 0.7),

        PileLocation("pile-2-1", 3.3, 1.0), PileLocation("pile-2-2", 4.4, 1.0),

        PileLocation("pile-3-1", 2.8, 1.3), PileLocation("pile-3-2", 3.9, 1.3), PileLocation("pile-3-3", 5.0, 1.3),

        PileLocation("pile-4-1", 2.2, 1.6), PileLocation("pile-4-2", 3.3, 1.6), PileLocation("pile-4-3", 4.4, 1.6), PileLocation("pile-4-4", 5.5, 1.6),

        PileLocation("pile-5-1", 1.7, 1.9), PileLocation("pile-5-2", 2.8, 1.9), PileLocation("pile-5-3", 3.9, 1.9), PileLocation("pile-5-4", 5.0, 1.9),
        PileLocation("pile-5-5", 6.1, 1.9),

        PileLocation("pile-6-1", 1.1, 2.2), PileLocation("pile-6-2", 2.2, 2.2), PileLocation("pile-6-3", 3.3, 2.2), PileLocation("pile-6-4", 4.4, 2.2),
        PileLocation("pile-6-5", 5.5, 2.2), PileLocation("pile-6-6", 6.6, 2.2),

        PileLocation("pile-7-1", 0.6, 2.5), PileLocation("pile-7-2", 1.7, 2.5), PileLocation("pile-7-3", 2.8, 2.5), PileLocation("pile-7-4", 3.9, 2.5),
        PileLocation("pile-7-5", 5.0, 2.5), PileLocation("pile-7-6", 6.1, 2.5), PileLocation("pile-7-7", 7.2, 2.5)
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
