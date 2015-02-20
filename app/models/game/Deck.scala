package models.game

object Deck {
  def fresh = {
    val cards = for {
      suit <- Suit.all
      rank <- Rank.all.reverse
    } yield Card(r = rank, s = suit)
    Deck(cards.toList)
  }
}

case class Deck(var cards: List[Card])
