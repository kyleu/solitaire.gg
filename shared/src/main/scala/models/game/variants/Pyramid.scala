//package models.game.variants
//
//import java.util.UUID
//
//import models.game.Rank.King
//import models.game._
//import models.game.pile.actions.{ DragToActions, SelectCardActions }
//import models.game.pile.constraints._
//import models.game.pile._
//
//object Pyramid extends GameVariant.Description {
//  override val key = "pyramid"
//  override val name = "Pyramid"
//  override val body = """
//    Build the bottom pile up or down regardless of suit. Ranking of cards is not continuous: an Ace may be built only on a 2, a King only on a Queen.
//  """
//  override val layouts = Seq(PyramidLayout.layout)
//}
//
//case class Pyramid(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
//  override def description = Pyramid
//
//  private[this] val pileOptions = PileOptionsHelper.waste.combine(PileOptions(
//    dragFromConstraint = Some(Constraints.topCardOnly),
//    dragToConstraint = Some(Constraints.total(13, aceHigh = false)),
//    selectCardConstraint = Some(Constraints.allOf("top-card-king", Constraints.topCardOnly, Constraints.specificRank(King))),
//    selectCardAction = Some(SelectCardActions.drawToPile(1, "graveyard")),
//    dragToAction = Some(DragToActions.remove())
//  ))
//
//  private[this] def pileOptionsFor(emptyPiles: String*) = {
//    val c = Constraints.pilesEmpty(emptyPiles: _*)
//    pileOptions.copy(
//      cardsShown = Some(1),
//      direction = None,
//      dragFromConstraint = Some(c),
//      dragToConstraint = Some(Constraints.allOf("pyramid-total", c, Constraints.total(13, aceHigh = false))),
//      selectCardConstraint = Some(Constraints.allOf("pyramid-king", c, Constraints.specificRank(King)))
//    )
//  }
//
//  private[this] val piles = List(
//    Pile("graveyard", "graveyard", PileOptionsHelper.empty),
//    Pile("stock", "stock", PileOptionsHelper.stock(1, "waste", None).combine(PileOptions(selectPileConstraint = Some(Constraints.never)))),
//    Pile("waste", "waste", pileOptions)
//  ) ++ (1 to 7).flatMap { i =>
//      (1 to i).map { j =>
//        val po = if (i == 7) { pileOptions } else { pileOptionsFor("pile-" + (i + 1) + "-" + j, "pile-" + (i + 1) + "-" + (j + 1)) }
//        Pile("pile-" + i + "-" + j, "tableau", po)
//      }
//    }
//
//  private[this] val deck = newShuffledDecks()
//
//  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)
//
//  override def initialMoves() = {
//    for (p <- gameState.piles) {
//      if (p.id.startsWith("pile-")) {
//        gameState.addCards(deck.getCards(1, turnFaceUp = true), p.id, reveal = true)
//      }
//    }
//    gameState.addCards(deck.getCards(1, turnFaceUp = true), "waste", reveal = true)
//    gameState.addCards(deck.getCards(), "stock")
//  }
//
//  override def isWin: Boolean = !gameState.piles.exists(p => p.id.startsWith("pile-") && p.cards.nonEmpty)
//}
