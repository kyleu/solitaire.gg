package models.game.variants

import java.util.UUID

import models.game.{ Deck, Layout, GameState }

import scala.util.Random

object GameVariant {
  abstract class Description {
    val key: String
    val name: String
    val body: String
    val layouts: Seq[Layout]
    val maxPlayers: Int = 1
    val completed: Boolean = false
    val undoAllowed: Boolean = true
  }

  def apply(variant: String, gameId: UUID, seed: Int) = variant match {
    case Canfield.key => new Canfield(gameId, seed)
    case FreeCell.key => new FreeCell(gameId, seed)
    case Golf.key => new Golf(gameId, seed)
    case KlondikeDrawThree.key => new KlondikeDrawThree(gameId, seed)
    case KlondikeDrawOne.key => new KlondikeDrawOne(gameId, seed)
    case Nestor.key => new Nestor(gameId, seed)
    case Pyramid.key => new Pyramid(gameId, seed)
    case Sandbox.key => new Sandbox(gameId, seed)
    case Spider.key => new Spider(gameId, seed)
    case TrustyTwelve.key => new TrustyTwelve(gameId, seed)
    case _ => throw new IllegalArgumentException("Invalid game variant [" + variant + "].")
  }

  val all = Seq(Canfield, FreeCell, Golf, KlondikeDrawThree, KlondikeDrawOne, Nestor, Pyramid, Sandbox, Spider, TrustyTwelve)
}

abstract class GameVariant(val gameId: UUID, val seed: Int) {
  def description: GameVariant.Description
  val rng = new Random(new java.util.Random(seed))
  val gameState: GameState
  def initialMoves(): Unit
  def isWin: Boolean

  def newShuffledDecks(numDecks: Int = 1) = if (seed == 0) {
    Deck((0 to numDecks - 1).flatMap(i => Deck.fresh().cards))
  } else {
    Deck.shuffled(rng, numDecks)
  }
}
