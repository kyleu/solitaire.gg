package models.game.rules

import java.util.UUID

import models.game.generated.GameRulesSet
import models.game.{ Deck, Suit, Rank, GameState }
import models.game.pile._

import scala.util.Random

object GameRules {
  val allSources = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
  val completed = Seq("canfield", "freecell", "golf", "gypsy", "klondike", "klondike1card", "nestor", "pyramid", "spider", "trustytwelve", "yukon")
  lazy val unfinished = GameRulesSet.all.filter(r => !completed.contains(r.id))
}

case class GameRules(
    id: String,
    title: String,
    description: String,
    victoryCondition: VictoryCondition = VictoryCondition.AllOnFoundation,
    cardRemovalMethod: CardRemovalMethod = CardRemovalMethod.BuildSequencesOnFoundation,
    deckOptions: DeckOptions = DeckOptions(),
    stock: Option[StockRules] = None,
    waste: Option[WasteRules] = None,
    reserves: Option[ReserveRules] = None,
    cells: Option[CellRules] = None,
    foundations: Seq[FoundationRules] = Nil,
    tableaus: Seq[TableauRules] = Nil,
    pyramids: Seq[PyramidRules] = Nil,
    complete: Boolean = false
) {
  def newGame(gameId: UUID, seed: Int) = {
    val rng = new Random(new java.util.Random(seed))
    val maxPlayers = 1
    val deck = newShuffledDecks(seed, rng, deckOptions.numDecks, deckOptions.ranks, deckOptions.suits)
    val pileSets = newPileSets()
    val layout = Layouts.forRules(id)
    val gameState = GameState(gameId, id, maxPlayers, seed, deck, pileSets, layout)
    gameState
  }

  private[this] def newShuffledDecks(seed: Int, rng: Random, numDecks: Int = 1, ranks: Seq[Rank], suits: Seq[Suit]) = if (seed == 0) {
    Deck((0 to numDecks - 1).flatMap(i => Deck.fresh(ranks, suits).cards))
  } else {
    Deck.shuffled(rng, numDecks, ranks, suits)
  }

  private[this] def newPileSets() = prototypePileSets.map(ps => PileSet(ps.behavior, ps.piles.map { p =>
    p.copy(cards = collection.mutable.ArrayBuffer.empty)
  }))

  private[this] lazy val pileIdsByType = Map(
    "stock" -> stock.map(s => Seq("stock")).getOrElse(Nil),
    "waste" -> waste.map(w => (1 to w.numPiles).map(i => "waste-" + i)).getOrElse(Nil),
    "reserves" -> reserves.map(r => (1 to r.numPiles).map(i => "reserve-" + i)).getOrElse(Nil),
    "cells" -> cells.map(c => (1 to c.numPiles).map(i => "cells-" + i)).getOrElse(Nil),
    "foundations" -> foundations.flatMap(fs => (1 to fs.numPiles).map { i =>
      if (fs.setNumber == 0) { "foundation-" + i } else { "foundation" + fs.setNumber + "-" + i }
    }),
    "tableaus" -> tableaus.flatMap(ts => (1 to ts.numPiles).map { i =>
      if (ts.setNumber == 0) { "tableau-" + i } else { "tableau" + ts.setNumber + "-" + i }
    }),
    "pyramids" -> pyramids.flatMap { ps =>
      (1 to ps.height).flatMap { i =>
        (1 to i).map { j =>
          if (ps.setNumber == 0) { "pyramid-" + i + "-" + j } else { "pyramid" + ps.setNumber + "-" + i + "-" + j }
        }
      }
    }
  )

  private[this] lazy val prototypePileSets = {
    stock.map(s => StockSet(s, pileIdsByType)) ++
      waste.map(w => WasteSet(w)) ++
      reserves.map(r => ReserveSet(r)) ++
      cells.map(c => CellSet(c)) ++
      foundations.map(f => FoundationSet(f)) ++
      tableaus.map(t => TableauSet(t)) ++
      pyramids.map(p => PyramidSet(p))
  }.toSeq
}
