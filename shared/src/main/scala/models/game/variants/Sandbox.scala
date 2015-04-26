package models.game.variants

import java.util.UUID

import models.game._

object Sandbox extends GameVariant.Description {
  override val completed = false
  override val key = "sandbox"
  override val name = "Sandbox"
  override val body = "..."
  override val maxPlayers = 3

  def initialMoves(gameState: GameState, deck: Deck) = {
    gameState.addCards(deck.getCards(), "stock")
  }
}

class Sandbox(override val gameId: UUID, override val seed: Int) extends GameVariant("sandbox", Sandbox, gameId, seed, Sandbox.initialMoves)
