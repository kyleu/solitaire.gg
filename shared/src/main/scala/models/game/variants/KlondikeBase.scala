package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile._

object KlondikeBase {
  val layouts = Seq(
    Layout(
      width = 7.8,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),
        PileLocation("waste", 1.7, 0.7),

        PileLocation("foundation-1", 3.9, 0.7),
        PileLocation("foundation-2", 5.0, 0.7),
        PileLocation("foundation-3", 6.1, 0.7),
        PileLocation("foundation-4", 7.2, 0.7),

        PileLocation("tableau-1", 0.6, 1.8),
        PileLocation("tableau-2", 1.7, 1.8),
        PileLocation("tableau-3", 2.8, 1.8),
        PileLocation("tableau-4", 3.9, 1.8),
        PileLocation("tableau-5", 5.0, 1.8),
        PileLocation("tableau-6", 6.1, 1.8),
        PileLocation("tableau-7", 7.2, 1.8)
      )
    )
  )
}

abstract class KlondikeBase(override val gameId: UUID, override val seed: Int, cardsToDraw: Int) extends GameVariant(gameId, seed) {
  private[this] val piles = List(
    Pile("stock", "stock", PileOptionsHelper.stock(cardsToDraw, "waste", Some("waste"))),
    Pile("waste", "waste", PileOptionsHelper.waste),

    Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-4", "foundation", PileOptionsHelper.foundation),

    Pile("tableau-1", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-2", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-3", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-4", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-5", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-6", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-7", "tableau", PileOptionsHelper.tableau)
  )

  private[this] val deck = newShuffledDecks()

  lazy val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)

    gameState.addCards(deck.getCards(1), "tableau-2")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)

    gameState.addCards(deck.getCards(2), "tableau-3")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)

    gameState.addCards(deck.getCards(3), "tableau-4")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)

    gameState.addCards(deck.getCards(4), "tableau-5")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-5", reveal = true)

    gameState.addCards(deck.getCards(5), "tableau-6")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-6", reveal = true)

    gameState.addCards(deck.getCards(6), "tableau-7")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-7", reveal = true)

    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
