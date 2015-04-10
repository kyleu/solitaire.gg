package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{ Pile, PileOptionsHelper }

object Sandbox extends GameVariant.Description {
  override val key = "sandbox"
  override val name = "Sandbox"
  override val body = "..."
  override val maxPlayers = 3
  override val layouts = Seq(
    Layout(
      width = 8.0,
      height = 6.0,
      piles = List(
        PileLocation("sandbox-1", 4.0, 3.0)
      )
    )
  )
}

case class Sandbox(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override val description = Sandbox

  val piles = List(
    Pile("sandbox-1", "tableau", PileOptionsHelper.tableau)
  )

  val deck = Deck.fresh()

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(), "sandbox-1")
  }

  override def isWin: Boolean = false
}
