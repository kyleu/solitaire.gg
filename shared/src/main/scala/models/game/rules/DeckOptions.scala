package models.game.rules

import models.game._

case class DeckOptions(
    numDecks: Int = 1,
    suits: Seq[Suit] = Suit.standard,
    ranks: Seq[Rank] = Rank.all,
    lowRank: Rank = Rank.Ace
) {
  val highRank: Rank = if (lowRank == Rank.Ace) {
    Rank.King
  } else if (lowRank == Rank.Two) {
    Rank.Ace
  } else if (lowRank == Rank.Unknown) {
    Rank.Unknown
  } else {
    Rank.allByValue(lowRank.value - 1)
  }
}
