package services.sandbox

import models.game.pile.Pile
import models.game.rules.{ SuitMatchRule, RankMatchRule }
import models.game.{ Suit, Rank, Card }

import scala.concurrent.Future

object Scratchpad {
  def run() = {
    val cards = collection.mutable.ArrayBuffer(
      Card(r = Rank.Two, s = Suit.Hearts, u = true),
      Card(r = Rank.Three, s = Suit.Hearts, u = true),
      Card(r = Rank.Four, s = Suit.Hearts, u = true)
    )
    val p = Pile("scratchpad", Pile.options, cards)
    val isSorted = p.isSorted(
      requireFaceUp = true,
      rmr = RankMatchRule.Any,
      smr = SuitMatchRule.Any,
      lowRank = Rank.Ace,
      wrap = false
    )
    val ret = "Ok: " + isSorted
    Future.successful(ret)
  }
}
