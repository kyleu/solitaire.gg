package services.game

import models.game._

case class Sandbox(override val id: String, override val seed: Int) extends GameVariant(id, seed) {
  override val name = "Sandbox"

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
