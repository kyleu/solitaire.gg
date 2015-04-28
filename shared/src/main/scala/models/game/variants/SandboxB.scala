package models.game.variants

import models.game.GameState

object SandboxB {
//  override val completed = false

  def initialMoves(gameState: GameState) = {
    gameState.addCards(gameState.deck.getCards(turnFaceUp = true), "waste-1", reveal = true)
  }
}

//  val piles = List(
//    Pile("waste", "waste", PileOptionsHelper.waste.combine(PileOptions(cardsShown = Some(52), direction = Some("r"))))
//  )
