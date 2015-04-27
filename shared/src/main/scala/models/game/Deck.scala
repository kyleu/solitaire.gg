package models.game

import scala.util.Random

object Deck {
  def fresh(ranks: Seq[Rank], suits: Seq[Suit]) = {
    val cards = for {
      suit <- suits
      rank <- ranks.reverse
    } yield Card(r = rank, s = suit, u = false)
    Deck(cards.toList)
  }

  def shuffled(random: Random, numDecks: Int = 1, ranks: Seq[Rank], suits: Seq[Suit]) = {
    val cards = (0 to numDecks - 1).flatMap(i => fresh(ranks, suits).cards)
    Deck(random.shuffle(cards))
  }
}

case class Deck(var cards: Seq[Card]) {
  def getCards(numCards: Int = this.cards.size, turnFaceUp: Boolean = false) = {
    val ret = this.cards.take(numCards)
    if (turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    this.cards = this.cards.drop(numCards)
    ret
  }

  def getCardsUniqueRanks(numCards: Int, turnFaceUp: Boolean = false) = {
    var ret = Seq.empty[Card]
    var enough = false
    while (!enough) {
      val c = cards.headOption.getOrElse(throw new IllegalStateException())
      if (ret.exists(_.r == c.r)) {
        cards = cards.tail :+ c
      } else {
        cards = cards.tail
        ret = ret :+ c
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
