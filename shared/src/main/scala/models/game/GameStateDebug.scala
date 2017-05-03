package models.game

import models.pile.Pile
import models.pile.set.PileSet

object GameStateDebug {
  def compare(l: GameState, r: GameState) = {
    val ret = new StringBuffer()
    def log(s: String) = ret.append(s + "\n")
    def check(k: String, l: Any, r: Any) = if(l != r) { log(s"$k [$l] does not match [$r].") }
    def checkSeq(k: String, l: Seq[Any], r: Seq[Any]) = l.zip(r).zipWithIndex.map(x => check(k + ":" + x._2, x._1._1, x._1._2))
    check("ID", l.gameId, r.gameId)
    check("Deck Size", l.deck.cards.size, r.deck.cards.size)
    checkSeq("Deck Card", l.deck.cards, r.deck.cards)
  }

  def toString(s: GameState): String = {
    val header = s"${s.rulesTitle} Game ${s.seed}: [${s.gameId}]\n"
    val layout = s"  Layout: [${s.layout}].\n"
    val cardsById = s"  CardsById: [${s.cardsById.size}].\n"
    val deck = s"  ${s.deck.cards.size} cards in deck.\n"
    val pilesets = s"  ${s.pileSets.size} pile sets:\n" + s.pileSets.map(printPileSet).mkString("\n") + "\n"

    header + layout + cardsById + deck + pilesets
  }

  private[this] def printPileSet(x: PileSet) = {
    s"    ${x.behavior} (${x.piles.size} piles):\n" + x.piles.map(p => s"      ${printPile(p)}").mkString("\n") + "\n"
  }

  private[this] def printPile(p: Pile) = {
    s"      ${p.toString}"
  }
}
