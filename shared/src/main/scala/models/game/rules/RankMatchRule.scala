package models.game.rules

import models.game.Rank

sealed trait RankMatchRule {
  def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean): Boolean
}

object RankMatchRule {
  private[this] def upBy(i: Int, l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = if (lowRank == Rank.Ace && l == Rank.Ace) {
    i match {
      case 1 => r == Rank.Two
      case 2 => r == Rank.Three
      case 3 => r == Rank.Four
      case 4 => r == Rank.Five
    }
  } else {
    val rVal = if (r.value - i < 2 && wrap) {
      (r.value - i) + 14
    } else {
      r.value - i
    }
    l.value == rVal
  }

  private[this] def downBy(i: Int, l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = if (lowRank == Rank.Ace && l == Rank.Ace) {
    i match {
      case 1 => r == Rank.King
      case 2 => r == Rank.Queen
      case 3 => r == Rank.Jack
      case 4 => r == Rank.Ten
    }
  } else {
    val rVal = if (r.value + i > 14 && wrap) {
      (r.value + i) - 14
    } else {
      r.value + i
    }
    l.value == rVal
  }

  case object None extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = false
  }
  case object Up extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(1, l, r, lowRank, wrap)
  }
  case object Down extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(1, l, r, lowRank, wrap)
  }
  case object Equal extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = l.value == r.value
  }
  case object UpOrDown extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = Up.check(l, r, lowRank, wrap) || Down.check(l, r, lowRank, wrap)
  }
  case object UpBy2 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(2, l, r, lowRank, wrap)
  }
  case object DownBy2 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(2, l, r, lowRank, wrap)
  }
  case object UpBy3 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(3, l, r, lowRank, wrap)
  }
  case object DownBy3 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(3, l, r, lowRank, wrap)
  }
  case object UpBy4 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = upBy(4, l, r, lowRank, wrap)
  }
  case object DownBy4 extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = downBy(4, l, r, lowRank, wrap)
  }
  case object UpByPileIndex extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = throw new NotImplementedError("up-by-pile-index")
  }
  case object Any extends RankMatchRule {
    override def check(l: Rank, r: Rank, lowRank: Rank, wrap: Boolean) = true
  }
}
