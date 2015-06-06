package models.game.rules

import models.game._

case class DeckOptions(
    numDecks: Int = 1,
    suits: Seq[Suit] = Suit.standard,
    ranks: Seq[Rank] = Rank.all,
    lowRank: Rank = Rank.Ace
) {
  val highRank: Rank = lowRank match {
    case Rank.Ace => Rank.King
    case Rank.Two => Rank.Ace
    case Rank.Unknown => Rank.Unknown
    case x => Rank.allByValue(x.value - 1)
  }
}
