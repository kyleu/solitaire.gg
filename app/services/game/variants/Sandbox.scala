package services.game.variants

import models.game._
import models.game.pile.Tableau
import services.game.GameVariant

object Sandbox extends GameVariant.Description {
  override val id = "sandbox"
  override val name = "Sandbox"
  override val body = "..."
}

case class Sandbox(override val id: String, override val seed: Int) extends GameVariant(id, seed) {
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

  override val gameState = GameState(id, seed, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(), "sandbox-1")
  }
}
