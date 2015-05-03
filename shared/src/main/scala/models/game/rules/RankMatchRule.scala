package models.game.rules

import models.game.Rank

sealed trait RankMatchRule {
  def check(l: Rank, r: Rank, lowRank: Rank): Boolean
}

object RankMatchRule {
  private[this] def upBy(i: Int, l: Rank, r: Rank, lowRank: Rank) = if(lowRank == Rank.Ace && l == Rank.Ace) {
    i match {
      case 1 => r == Rank.Two
      case 2 => r == Rank.Three
      case 3 => r == Rank.Four
      case 4 => r == Rank.Five
    }
  } else {
    l.value == r.value - i
  }

  private[this] def downBy(i: Int, l: Rank, r: Rank, lowRank: Rank) = if(lowRank == Rank.Ace && l == Rank.Ace) {
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
    def check(l: Rank, r: Rank, lowRank: Rank) = false
  }
  case object Up extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = upBy(1, l, r, lowRank)
  }
  case object Down extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 1
  }
  case object Equal extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value
  }
  case object UpOrDown extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = upBy(1, l, r, lowRank) || Down.check(l, r, lowRank)
  }
  case object UpBy2 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = upBy(2, l, r, lowRank)
  }
  case object DownBy2 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 2
  }
  case object UpBy3 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = upBy(3, l, r, lowRank)
  }
  case object DownBy3 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 3
  }
  case object UpBy4 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = upBy(4, l, r, lowRank)
  }
  case object DownBy4 extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = l.value == r.value + 4
  }
  case object UpByPileIndex extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = throw new NotImplementedError("up-by-pile-index")
  }
  case object Any extends RankMatchRule {
    def check(l: Rank, r: Rank, lowRank: Rank) = true
  }
}
