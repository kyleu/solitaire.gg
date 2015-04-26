package models.game.variants

import java.util.UUID

import models.game._

object Klondike extends GameVariant.Description {
  override val key = "klondike"
  override val name = "Klondike"
  override val body = "The standard Solitaire game. Enjoy!"

  def initialMoves(gameState: GameState, deck: Deck) = {
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
}

class Klondike(gameId: UUID, seed: Int) extends GameVariant("klondike", Klondike, gameId, seed, Klondike.initialMoves)
