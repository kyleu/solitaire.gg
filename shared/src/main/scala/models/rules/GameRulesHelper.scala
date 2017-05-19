package models.rules

import java.util.UUID

import models.card.{Deck, Rank, Suit}
import models.game.GameState
import models.pile.set._

import scala.util.Random

trait GameRulesHelper { this: GameRules =>
  def newGame(gameId: UUID, seed: Int, withRules: String) = {
    val rng = new Random(new java.util.Random(seed.toLong))
    val maxPlayers = 1
    val lowRank = if (deckOptions.lowRank == Rank.Unknown) {
      deckOptions.ranks(rng.nextInt(deckOptions.ranks.length))
    } else {
      deckOptions.lowRank
    }
    val deck = newShuffledDecks(seed, rng, deckOptions.numDecks, deckOptions.ranks, deckOptions.suits, lowRank, deckOptions.highRank)
    val pileSets = newPileSets()
    val rulesTitle = if (withRules == id) { title } else { aka(withRules) }
    val gameState = GameState(gameId, withRules, rulesTitle, maxPlayers, seed, deck, pileSets, this.layout)
    gameState
  }

  protected[this] def newShuffledDecks(seed: Int, rng: Random, numDecks: Int = 1, ranks: Seq[Rank], suits: Seq[Suit], lowRank: Rank, highRank: Rank) = {
    val cards = Deck.freshCards(numDecks, ranks, suits)
    if (seed == 0) {
      Deck(cards, lowRank, highRank, cards.map(_.id))
    } else {
      val shuffledCards = rng.shuffle(cards)
      Deck(shuffledCards, lowRank, highRank, shuffledCards.map(_.id))
    }
  }

  protected[this] def newPileSets() = prototypePileSets.map(ps => PileSet(ps.behavior, ps.piles.map { p =>
    p.copy(cards = collection.mutable.ArrayBuffer.empty)
  }, ps.visible))

  protected[this] lazy val pileIdsByType = Map(
    "stock" -> stock.map(_ => Seq("stock")).getOrElse(Nil),
    "waste" -> waste.map(w => (1 to w.numPiles).map(i => "waste-" + i)).getOrElse(Nil),
    "foundations" -> foundations.flatMap(fs => (1 to fs.numPiles).map { i =>
      if (fs.setNumber == 0) { s"foundation-$i" } else { s"foundation${fs.setNumber}-$i" }
    }),
    "tableaus" -> tableaus.flatMap(ts => (1 to ts.numPiles).map { i =>
      if (ts.setNumber == 0) { s"tableau-$i" } else { s"tableau${ts.setNumber}-$i" }
    }),
    "pyramids" -> pyramids.flatMap { ps =>
      (1 to ps.height).flatMap { i =>
        (1 to i).map { j =>
          if (ps.setNumber == 0) { s"pyramid-$i-$j" } else { s"pyramid${ps.setNumber}-$i-$j" }
        }
      }
    },
    "reserves" -> reserves.map(r => (1 to r.numPiles).map(i => s"reserve-$i")).getOrElse(Nil),
    "cells" -> cells.map(c => (1 to c.numPiles).map(i => s"cells-$i")).getOrElse(Nil)
  )

  protected[this] lazy val prototypePileSets = {
    tableaus.map(t => TableauSet(t, deckOptions, cardRemovalMethod)) ++
      reserves.map(r => ReserveSet(r)) ++
      waste.map(w => WasteSet(w, cardRemovalMethod)) ++
      pyramids.map(p => PyramidSet(p, cardRemovalMethod)) ++
      cells.map(c => CellSet(c)) ++
      stock.map(s => StockSet(s, pileIdsByType)) ++
      foundations.map(f => FoundationSet(f, deckOptions))
  }
}
