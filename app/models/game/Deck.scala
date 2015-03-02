package models.game

import scala.util.Random

object Deck {
  def fresh() = {
    val cards = for {
      suit <- Suit.all
      rank <- Rank.all.reverse
    } yield Card(r = rank, s = suit, u = false)
    Deck(cards.toList)
  }

  def shuffled(random: Random) = {
    Deck(random.shuffle(fresh.cards))
  }
}

case class Deck(var cards: List[Card]) {
  def getCards(numCards: Int = this.cards.size, turnFaceUp: Boolean = false) = {
    val ret = this.cards.take(numCards)
    if(turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    this.cards = this.cards.drop(numCards)
    ret
  }
}
