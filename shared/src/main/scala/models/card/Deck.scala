package models.card

import scala.util.Random

object Deck {
  def freshCards(numDecks: Int, ranks: Seq[Rank], suits: Seq[Suit]) = {
    val cards = (0 until numDecks).flatMap(_ => suits.flatMap(s => ranks.reverseMap(s -> _)))
    val ids = Random.shuffle(cards.indices.toList)
    cards.zip(ids).map(x => Card(id = x._2, r = x._1._2, s = x._1._1))
  }
}

case class Deck(var cards: Seq[Card], lowRank: Rank, highRank: Rank, originalOrder: Seq[Int]) {
  def getCards(
    numCards: Int = this.cards.size,
    turnFaceUp: Boolean = false,
    rank: Option[Rank] = None,
    forbiddenRanks: Set[Rank] = Set.empty,
    suit: Option[Suit] = None,
    forbiddenSuits: Set[Suit] = Set.empty,
    color: Option[Color] = None,
    forbiddenColors: Set[Color] = Set.empty
  ) = {
    val ret = if (rank.isEmpty && forbiddenRanks.isEmpty && suit.isEmpty && forbiddenSuits.isEmpty && color.isEmpty && forbiddenColors.isEmpty) {
      this.cards.take(numCards)
    } else {
      var cards = Seq.empty[Card]
      (0 until numCards).map { _ =>
        val c = this.cards.find { c =>
          (!cards.contains(c)) && (!forbiddenRanks.contains(c.r)) && (!forbiddenSuits.contains(c.s)) &&
            rank.fold(true)(r => c.r == r) && suit.fold(true)(s => c.s == s) && color.fold(true)(clr => c.s.color == clr)
        }.getOrElse(throw new IllegalStateException("Cannot find card matching options."))
        cards = c +: cards
        c
      }
    }
    if (turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    this.cards = this.cards.filterNot(card => ret.contains(card))
    ret
  }

  def getCardsUniqueRanks(ranks: Seq[Rank], numCards: Int, turnFaceUp: Boolean = false) = {
    var ret = Seq.empty[Card]
    var enough = false
    var seekIndex = 0
    while (!enough) {
      if (seekIndex == cards.size) {
        throw new IllegalStateException("Coulndn't find unique ranks.")
      }
      val c = cards(seekIndex)
      if (ret.exists(_.r == c.r) || ranks.contains(c.r)) {
        //cards = cards.tail :+ c
        seekIndex += 1
      } else {
        cards = cards.filterNot(_ == c)
        ret = ret :+ c
        seekIndex = 0
      }
      enough = ret.length == numCards
    }
    if (turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    ret
  }
}
