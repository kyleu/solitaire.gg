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
    special: Option[SpecialRules] = None,
    complete: Boolean = false
) {
  def newGame(gameId: UUID, seed: Int) = {
    val rng = new Random(new java.util.Random(seed))
    val maxPlayers = 1
    val lowRank = if(deckOptions.lowRank == Rank.Unknown) {
      Rank.all(rng.nextInt(Rank.all.length))
    } else {
      deckOptions.lowRank
    }
    val highRank = if(lowRank == Rank.Ace) {
      Rank.King
    } else if(lowRank == Rank.Two) {
      Rank.Ace
    } else {
      Rank.allByValue(lowRank.value - 1)
    }
    val deck = newShuffledDecks(seed, rng, deckOptions.numDecks, deckOptions.ranks, deckOptions.suits, lowRank, highRank)
    val pileSets = newPileSets()
    val layout = Layouts.forRules(id)
    val gameState = GameState(gameId, id, maxPlayers, seed, deck, pileSets, layout)
    gameState
  }

  private[this] def newShuffledDecks(seed: Int, rng: Random, numDecks: Int = 1, ranks: Seq[Rank], suits: Seq[Suit], lowRank: Rank, highRank: Rank) = {
    val cards = (0 to numDecks - 1).flatMap(i => Deck.freshCards(ranks, suits))
    if (seed == 0) {
      Deck(cards, lowRank, highRank)
    } else {
      val shuffledCards = rng.shuffle(cards)
      Deck(shuffledCards, lowRank, highRank)
    }
  }

  private[this] def newPileSets() = prototypePileSets.map(ps => PileSet(ps.behavior, ps.piles.map { p =>
    p.copy(cards = collection.mutable.ArrayBuffer.empty)
  }, ps.visible))

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
      foundations.map(f => FoundationSet(f, deckOptions)) ++
      tableaus.map(t => TableauSet(t)) ++
      pyramids.map(p => PyramidSet(p))
  }.toSeq
}
