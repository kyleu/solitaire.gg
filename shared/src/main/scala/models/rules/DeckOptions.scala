package models.rules

import models.card.{Rank, Suit}

case class DeckOptions(
    numDecks: Int = 1,
    suits: Seq[Suit] = Suit.standard,
    ranks: Seq[Rank] = Rank.all,
    lowRank: Rank = Rank.Ace
) {
  val highRank: Rank = lowRank.previous
  val cardCount = numDecks * suits.size * ranks.size
}
