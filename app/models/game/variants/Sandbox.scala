package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{ Pile, PileOptionsHelper }

object Sandbox extends GameVariant.Description {
  override val completed = false
  override val key = "sandbox"
  override val name = "Sandbox"
  override val body = "..."
  override val maxPlayers = 3
  override val layouts = Seq(
    Layout(
      width = 4.0,
      height = 3.0,
      piles = List(
        PileLocation("stock", 1.0, 1.5),
        PileLocation("foundation-1", 3.0, 1.5)
      )
    )
  )
}

case class Sandbox(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override val description = Sandbox

  val piles = List(
    Pile("stock", "stock", PileOptionsHelper.stock(3, "foundation-1", None)),
    Pile("foundation-1", "foundation", PileOptionsHelper.foundation)
  )

  val deck = Deck.shuffled(rng)

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin = gameState.pilesById("stock").cards.isEmpty
}
