package models.game

import models.game.variants.{Golf, FreeCell, KlondikeDrawOne, KlondikeDrawThree}

import scala.util.Random

object GameVariant {
  abstract class Description {
    val id: String
    val name: String
    val body: String
  }

  def apply(variant: String, id: String, seed: Int) = variant match {
    case FreeCell.id => new FreeCell(id, seed)
    case Golf.id => new Golf(id, seed)
    case KlondikeDrawThree.id => new KlondikeDrawThree(id, seed)
    case KlondikeDrawOne.id => new KlondikeDrawOne(id, seed)
    case _ => throw new IllegalArgumentException("Invalid game variant [" + variant + "].")
  }

  val all = List(
    FreeCell, Golf, KlondikeDrawThree, KlondikeDrawOne
  )
}

abstract class GameVariant(val id: String, val seed: Int) {
  def description: GameVariant.Description
  val rng = new Random(new java.util.Random(seed))
  val gameState: GameState
  def initialMoves(): Unit
}
