package models.game.rules

import models.game._

case class DeckOptions(
    numDecks: Int = 1,
    suits: Seq[Suit] = Suit.standard,
    ranks: Seq[Rank] = Rank.all,
    lowRank: Option[Rank] = Some(Rank.Ace)
) {
  val highRank = lowRank.map(lr => if (lr == Rank.Ace) { Rank.King } else { Rank.Ace })
}
