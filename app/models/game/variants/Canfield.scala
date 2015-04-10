package models.game.variants

import java.util.UUID

import models.game.pile._
import models.game.{ Deck, GameState, Layout, PileLocation }

object Canfield extends GameVariant.Description {
  override val key = "canfield"
  override val name = "Canfield"
  override val body = "Originally created for casinos, this game is very hard to win."
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

case class Canfield(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Canfield

  private[this] val piles = List(
    Pile("stock", "stock", PileOptionsHelper.stock(3, "waste", Some("waste"))),
    Pile("waste", "waste", PileOptionsHelper.waste),
    Pile("reserve", "reserve", PileOptionsHelper.waste.combine(PileOptions(cardsShown = Some(1)))),

    Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-4", "foundation", PileOptionsHelper.foundation),

    Pile("tableau-1", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-2", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-3", "tableau", PileOptionsHelper.tableau),
    Pile("tableau-4", "tableau", PileOptionsHelper.tableau)
  )

  private[this] val deck = Deck.shuffled(rng)

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(34), "stock")
    gameState.addCards(deck.getCards(13, turnFaceUp = true), "reserve", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
