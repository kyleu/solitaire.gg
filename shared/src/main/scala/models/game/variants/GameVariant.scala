package models.game.variants

import java.util.UUID

import models.game.generated.GameRulesSet
import models.game.pile._
import models.game.{ Deck, Layout, GameState }

import scala.util.Random

object GameVariant {
  abstract class Description {
    val key: String
    val name: String
    val body: String
    val layouts: Seq[Layout]
    val maxPlayers: Int = 1
    val completed: Boolean = true
    val undoAllowed: Boolean = true
  }

  def apply(variant: String, gameId: UUID, seed: Int) = variant match {
    case Canfield.key => new Canfield(gameId, seed)
//    case FreeCell.key => new FreeCell(gameId, seed)
//    case Golf.key => new Golf(gameId, seed)
//    case Gypsy.key => new Gypsy(gameId, seed)
    case Klondike.key => new Klondike(gameId, seed)
//    case Nestor.key => new Nestor(gameId, seed)
//    case Pyramid.key => new Pyramid(gameId, seed)
//    case Sandbox.key => new Sandbox(gameId, seed)
//    case SandboxB.key => new SandboxB(gameId, seed)
//    case Spider.key => new Spider(gameId, seed)
//    case TrustyTwelve.key => new TrustyTwelve(gameId, seed)
//    case Yukon.key => new Yukon(gameId, seed)
    case _ => throw new IllegalArgumentException("Invalid game variant [" + variant + "].")
  }

//  val all = Seq(Canfield, FreeCell, Golf, Gypsy, Klondike, Nestor, Pyramid, Sandbox, SandboxB, Spider, TrustyTwelve, Yukon)
  val all = Seq(Canfield, Klondike)
}

case class GameVariant(rulesKey: String, description: GameVariant.Description, gameId: UUID, seed: Int) {
  val rules = GameRulesSet.allById(rulesKey)

  val rng = new Random(new java.util.Random(seed))

  private[this] val pileSets = {
    rules.stock.map(s => StockSet(s)) ++
    rules.waste.map(w => WasteSet(w)) ++
    rules.reserves.map(r => ReserveSet(r)) ++
    rules.foundations.map(f => FoundationSet(f)) ++
    rules.tableaus.map(t => TableauSet(t))
  }.toSeq

  private[this] val deck = newShuffledDecks(rules.deckOptions.numDecks)

  val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, pileSets, description.layouts)

  def initialMoves() = {
    // TODO
  }

  def isWin = {
    rules.victoryCondition.check(gameState)
  }

  private[this] def newShuffledDecks(numDecks: Int = 1) = if (seed == 0) {
    Deck((0 to numDecks - 1).flatMap(i => Deck.fresh().cards))
  } else {
    Deck.shuffled(rng, numDecks)
  }
}
