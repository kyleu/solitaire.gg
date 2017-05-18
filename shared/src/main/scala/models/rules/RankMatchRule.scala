package models.rules

import enumeratum.values._
import models.card.Rank

sealed abstract class RankMatchRule(val value: String) extends StringEnumEntry {
  def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean): Boolean
}

object RankMatchRule extends StringEnum[RankMatchRule] with StringCirceEnum[RankMatchRule] {
  private[this] def upBy(i: Int, l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = {
    val highRank = lowRank.previous
    val target = (0 until i).foldLeft[Rank](l)((rank, i) => {
      if (rank == Rank.Unknown) {
        Rank.Unknown
      } else if (rank == highRank) {
        if (wrap) {
          rank.next
        } else {
          Rank.Unknown
        }
      } else {
        rank.next
      }
    })
    target == r
  }

  private[this] def downBy(i: Int, l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = {
    val target = (0 until i).foldLeft[Rank](l)((rank, i) => {
      if (rank == Rank.Unknown) {
        Rank.Unknown
      } else if (rank == lowRank) {
        if (wrap) {
          rank.previous
        } else {
          Rank.Unknown
        }
      } else {
        rank.previous
      }
    })
    target == r
  }

  case object None extends RankMatchRule("none") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = false
  }
  case object Up extends RankMatchRule("up") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(1, l, r, lowRank, wrap)
  }
  case object Down extends RankMatchRule("down") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(1, l, r, lowRank, wrap)
  }
  case object Equal extends RankMatchRule("equal") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = l.index == r.index
  }
  case object UpOrDown extends RankMatchRule("up-or-down") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = {
      Up.check(l, r, lowRank, wrap) || Down.check(l, r, lowRank, wrap)
    }
  }
  case object UpBy2 extends RankMatchRule("up-by-two") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(2, l, r, lowRank, wrap)
  }
  case object DownBy2 extends RankMatchRule("down-by-two") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(2, l, r, lowRank, wrap)
  }
  case object UpBy3 extends RankMatchRule("up-by-three") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(3, l, r, lowRank, wrap)
  }
  case object DownBy3 extends RankMatchRule("down-by-three") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(3, l, r, lowRank, wrap)
  }
  case object UpBy4 extends RankMatchRule("up-by-four") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(4, l, r, lowRank, wrap)
  }
  case object DownBy4 extends RankMatchRule("down-by-four") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(4, l, r, lowRank, wrap)
  }
  case object UpByPileIndex extends RankMatchRule("up-by-pile-index") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(1, l, r, lowRank, wrap)
  }
  case object Any extends RankMatchRule("any") {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = true
  }

  override val values = findValues
}
