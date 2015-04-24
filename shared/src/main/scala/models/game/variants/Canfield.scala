package models.game.variants

import java.util.UUID

import models.game.pile._
import models.game.{ Deck, GameState, Layout, PileLocation }

object Canfield extends GameVariant.Description {
  override val completed = false
  override val key = "canfield"
  override val name = "Canfield"
  override val body = "Originally created for casinos, this game is very hard to win."
  override val layouts = Seq(
    Layout(
      width = 9.9,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),
        PileLocation("waste-1", 1.7, 0.7),
        PileLocation("reserve-1", 2.8, 0.7),

        PileLocation("foundation-1", 6.0, 0.7),
        PileLocation("foundation-2", 7.1, 0.7),
        PileLocation("foundation-3", 8.2, 0.7),
        PileLocation("foundation-4", 9.3, 0.7),

        PileLocation("tableau-1", 1.1, 1.8),
        PileLocation("tableau-2", 2.2, 1.8),
        PileLocation("tableau-3", 3.3, 1.8),
        PileLocation("tableau-4", 4.4, 1.8)
      )
    )
  )

  def initialMoves(gameState: GameState, deck: Deck) = {
    gameState.addCards(deck.getCards(34), "stock")
    gameState.addCards(deck.getCards(13, turnFaceUp = true), "reserve-1", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)
  }
}

class Canfield(gameId: UUID, seed: Int) extends GameVariant("canfield", Canfield, gameId, seed, Canfield.initialMoves)
