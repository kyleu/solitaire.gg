package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.Tableau

object Sandbox extends GameVariant.Description {
  override val key = "sandbox"
  override val name = "Sandbox"
  override val body = "..."
}

case class Sandbox(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override val description = Sandbox

  val piles = List(
    new Tableau("sandbox-1")
  )

  val deck = Deck.fresh()

  val layouts = List(
    Layout(
      width = 8.0,
      height = 6.0,
      piles = List(
        PileLocation("sandbox-1", 3.5, 2.5)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, seed, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(), "sandbox-1")
  }

  override def isWin: Boolean = false
}
