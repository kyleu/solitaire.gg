package models.game

import java.util.UUID

import models.game.variants._

import scala.util.Random

object GameVariant {
  abstract class Description {
    val key: String
    val name: String
    val body: String
  }

  def apply(variant: String, gameId: UUID, seed: Int) = variant match {
    case FreeCell.key => new FreeCell(gameId, seed)
    case Golf.key => new Golf(gameId, seed)
    case KlondikeDrawThree.key => new KlondikeDrawThree(gameId, seed)
    case KlondikeDrawOne.key => new KlondikeDrawOne(gameId, seed)
    case Nestor.key => new Nestor(gameId, seed)
    case TrustyTwelve.key => new TrustyTwelve(gameId, seed)
    case _ => throw new IllegalArgumentException("Invalid game variant [" + variant + "].")
  }

  val all = List(
    FreeCell, Golf, KlondikeDrawThree, KlondikeDrawOne, Nestor, TrustyTwelve
  )
}

abstract class GameVariant(val gameId: UUID, val seed: Int) {
  def description: GameVariant.Description
  val rng = new Random(new java.util.Random(seed))
  val gameState: GameState
  def initialMoves(): Unit
  def isWin: Boolean
}
