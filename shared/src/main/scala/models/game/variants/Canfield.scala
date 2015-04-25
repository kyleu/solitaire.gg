package models.game.variants

import java.util.UUID

import models.game.{ Deck, GameState }

object Canfield extends GameVariant.Description {
  override val completed = false
  override val key = "canfield"
  override val name = "Canfield"
  override val body = "Originally created for casinos, this game is very hard to win."

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
