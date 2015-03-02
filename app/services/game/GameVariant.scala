package services.game

import models.game.GameState

import scala.util.Random

object GameVariant {
  def apply(variant: String, id: String, seed: Int) = variant match {
    case "klondike" => new KlondikeThreeCard(id, seed)
    //case "klondike1card" => new KlondikeOneCard(id, seed)
  }
}

abstract class GameVariant(val id: String, val seed: Int) {
  val rng = new Random(new java.util.Random(seed))

  val name: String

  val gameState: GameState

  def initialMoves(): Unit
}
