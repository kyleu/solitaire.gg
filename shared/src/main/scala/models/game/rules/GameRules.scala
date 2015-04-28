package models.game.rules

import java.util.UUID

import models.game.{ Deck, Suit, Rank, GameState }
import models.game.pile._

import scala.util.Random

object GameRules {
  val allSources = Seq("Stock", "Pyramid", "Waste", "Pocket", "Reserve", "Cell", "Foundation", "Tableau")
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
    val layout = Layouts.forVariant(id)
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
    "waste" -> waste.map(w => (1 to w.numPiles).map( i => "waste-" + i)).getOrElse(Nil),
    "reserves" -> reserves.map(r => (1 to r.numPiles).map( i => "reserve-" + i)).getOrElse(Nil),
    "cells" -> cells.map(c => (1 to c.numPiles).map( i => "cells-" + i)).getOrElse(Nil),
    "foundations" -> foundations.zipWithIndex.flatMap( fs => (1 to fs._1.numPiles).map { i =>
      val id = if(fs._2 == 0) { i.toString } else { throw new NotImplementedError() }
      "foundation-" + id
    }),
    "tableaus" -> tableaus.zipWithIndex.flatMap( ts => (1 to ts._1.numPiles).map { i =>
      val id = if(ts._2 == 0) { i.toString } else { throw new NotImplementedError() }
      "tableau-" + id
    }),
    "pyramids" -> pyramids.zipWithIndex.flatMap { ps =>
      (1 to ps._1.height).flatMap { i =>
        (1 to i).map { j =>
          val location = i + "-" + j
          val id = if (ps._2 == 0) { location } else { throw new NotImplementedError() }
          "pyramid-" + id
        }
      }
    }
  )

  private[this] lazy val prototypePileSets = {
    stock.map( s => StockSet(s, pileIdsByType)) ++
      waste.map( w =>  WasteSet(w)) ++
      reserves.map(r => ReserveSet(r)) ++
      cells.map(c => CellSet(c)) ++
      foundations.map(f => FoundationSet(f)) ++
      tableaus.map(t => TableauSet(t)) ++
      pyramids.map(p => PyramidSet(p))
  }.toSeq
}
