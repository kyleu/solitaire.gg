package models.game.variants

import java.util.UUID

import models.game._

object SandboxB extends GameVariant.Description {
  override val completed = false
  override val key = "sandboxb"
  override val name = "Sandbox B"
  override val body = "Another work in progress.."
  override val maxPlayers = 3

  def initialMoves(gameState: GameState, deck: Deck) = {
    gameState.addCards(deck.getCards(turnFaceUp = true), "waste-1", reveal = true)
  }
}

class SandboxB(override val gameId: UUID, override val seed: Int) extends GameVariant("sandboxb", SandboxB, gameId, seed, SandboxB.initialMoves) {
  //  val piles = List(
  //    Pile("waste", "waste", PileOptionsHelper.waste.combine(PileOptions(cardsShown = Some(52), direction = Some("r"))))
  //  )
}
