package models.game.variants

import java.util.UUID

import models.game.pile._
import models.game.{ Deck, GameState, Layout, PileLocation }

object Canfield extends GameVariant.Description {
  override val key = "canfield"
  override val name = "Canfield"
  override val body = "Originally created for casinos, this game is very hard to win."
}

case class Canfield(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Canfield

  private val piles = List(
    new Pile("stock", "stock", PileOptionsHelper.stock(3, "waste", Some("waste"))),
    new Pile("waste", "waste", PileOptionsHelper.waste),
    new Pile("reserve", "reserve", PileOptionsHelper.waste.combine(PileOptions(cardsShown = Some(1)))),

    new Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
    new Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
    new Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
    new Pile("foundation-4", "foundation", PileOptionsHelper.foundation),

    new Pile("tableau-1", "tableau", PileOptionsHelper.tableau),
    new Pile("tableau-2", "tableau", PileOptionsHelper.tableau),
    new Pile("tableau-3", "tableau", PileOptionsHelper.tableau),
    new Pile("tableau-4", "tableau", PileOptionsHelper.tableau)
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = Seq(
    Layout(
      width = 7.4,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),
        PileLocation("waste", 1.7, 0.7),
        PileLocation("reserve", 1.7, 1.8),

        PileLocation("foundation-1", 3.5, 0.7),
        PileLocation("foundation-2", 4.6, 0.7),
        PileLocation("foundation-3", 5.7, 0.7),
        PileLocation("foundation-4", 6.8, 0.7),

        PileLocation("tableau-1", 3.5, 1.8),
        PileLocation("tableau-2", 4.6, 1.8),
        PileLocation("tableau-3", 5.7, 1.8),
        PileLocation("tableau-4", 6.8, 1.8)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, layouts)

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
