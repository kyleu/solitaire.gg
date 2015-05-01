package models.game.rules

import models.game.Rank

sealed trait RankMatchRule {
  def check(l: Rank, r: Rank): Boolean
}

object RankMatchRule {
  case object None extends RankMatchRule {
    def check(l: Rank, r: Rank) = false
  }
  case object Up extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value - 1
  }
  case object Down extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value + 1
  }
  case object Equal extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value
  }
  case object UpOrDown extends RankMatchRule {
    def check(l: Rank, r: Rank) = Up.check(l, r) || Down.check(l, r)
  }
  case object UpBy2 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value - 2
  }
  case object DownBy2 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value + 2
  }
  case object UpBy3 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value - 3
  }
  case object DownBy3 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value + 3
  }
  case object UpBy4 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value - 4
  }
  case object DownBy4 extends RankMatchRule {
    def check(l: Rank, r: Rank) = l.value == r.value + 4
  }
  case object UpByPileIndex extends RankMatchRule {
    def check(l: Rank, r: Rank) = throw new NotImplementedError("up-by-pile-index")
  }
  case object Any extends RankMatchRule {
    def check(l: Rank, r: Rank) = true
  }
}
