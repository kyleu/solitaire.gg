package models.game.rules

import models.game.Rank

sealed trait RankMatchRule {
  def toWords: String
  def check(l: Rank, r: Rank, lowRank: Rank): Boolean
}

object RankMatchRule {
  private[this] def upBy(i: Int, l: Rank, r: Rank, lowRank: Rank) = if (lowRank == Rank.Ace && l == Rank.Ace) {
    i match {
      case 1 => r == Rank.Two
      case 2 => r == Rank.Three
      case 3 => r == Rank.Four
      case 4 => r == Rank.Five
    }
  } else {
    l.value == r.value - i
  }

  private[this] def downBy(i: Int, l: Rank, r: Rank, lowRank: Rank) = if (lowRank == Rank.Ace && l == Rank.Ace) {
    i match {
      case 1 => r == Rank.King
      case 2 => r == Rank.Queen
      case 3 => r == Rank.Jack
      case 4 => r == Rank.Ten
    }
  } else {
    l.value == r.value + i
  }

  case object None extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = false
    override def toWords = "never"
  }
  case object Up extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = upBy(1, l, r, lowRank)
    override def toWords = "one higher"
  }
  case object Down extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 1
    override def toWords = "one lower"
  }
  case object Equal extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value
    override def toWords = "the same rank"
  }
  case object UpOrDown extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = upBy(1, l, r, lowRank) || Down.check(l, r, lowRank)
    override def toWords = "one lower or higher"
  }
  case object UpBy2 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = upBy(2, l, r, lowRank)
    override def toWords = "two higher"
  }
  case object DownBy2 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 2
    override def toWords = "two lower"
  }
  case object UpBy3 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = upBy(3, l, r, lowRank)
    override def toWords = "three higher"
  }
  case object DownBy3 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 3
    override def toWords = "three lower"
  }
  case object UpBy4 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = upBy(4, l, r, lowRank)
    override def toWords = "four higher"
  }
  case object DownBy4 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 4
    override def toWords = "four lower"
  }
  case object UpByPileIndex extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = throw new NotImplementedError("up-by-pile-index")
    override def toWords = "???"
  }
  case object Any extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank) = true
    override def toWords = "any rank"
  }
}
