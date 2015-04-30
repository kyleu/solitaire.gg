package models.game.variants

import models.game.GameState

object Pyramid {
  def initialMoves(gameState: GameState) = {
    val deck = gameState.deck
    for (p <- gameState.piles) {
      if (p.id.startsWith("pyramid-")) {
        gameState.addCards(deck.getCards(1, turnFaceUp = true), p.id, reveal = true)
      }
    }
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "waste-1", reveal = true)
    gameState.addCards(deck.getCards(), "stock")
  }
}

//  private[this] val piles = List(
//    Pile("stock", "stock", PileOptionsHelper.stock(1, "waste", None).combine(PileOptions(selectPileConstraint = Some(Constraints.never)))),
//    Pile("waste", "waste", pileOptions)
//  ) ++ (1 to 7).flatMap { i =>
//    (1 to i).map { j =>
//      val po = if (i == 7) { pileOptions } else { pileOptionsFor("pile-" + (i + 1) + "-" + j, "pile-" + (i + 1) + "-" + (j + 1)) }
//      Pile("pile-" + i + "-" + j, "tableau", po)
//    }
//  }
