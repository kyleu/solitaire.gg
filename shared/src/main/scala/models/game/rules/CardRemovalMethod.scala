package models.game.rules

import models.game.{ Rank, Card }

sealed trait CardRemovalMethod {
  def canRemove(l: Card, r: Card): Boolean = false
}

object CardRemovalMethod {
  case object BuildSequencesOnFoundation extends CardRemovalMethod

  case object RemovePairsOfSameRank extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = l.r == r.r
  }
  case object RemovePairsOfSameRankAndColor extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = l.r == r.r && l.s.color == r.s.color
  }
  case object RemovePairsOfSameSuit extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = l.s == r.s
  }
  case object RemoveNinesOrPairsAddingToNineOr10JQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(9, l.r, r.r)
  }
  case object RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r)
  }
  case object RemovePairsAddingToTenOr10JQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r)
  }
  case object RemovePairsAddingToTenOrFour10JQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r)
  }
  case object RemovePairsAddingToElevenOrJPairOrQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r)
  }
  case object RemovePairsAddingToElevenOrJPairOrQPairOrKPair extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r)
  }
  case object RemovePairsAddingToElevenOrJQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r)
  }
  case object RemoveSameSuitPairsAddingToElevenOrJQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r)
  }
  case object RemovePairsAddingToTwelveOrQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(12, l.r, r.r)
  }
  case object RemovePairsAddingToThirteenOrK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(13, l.r, r.r)
  }
  case object RemovePairsAddingToFourteen extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(14, l.r, r.r)
  }
  case object RemoveSetsAddingToFifteenOr10JQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r)
  }
  case object RemoveSetsAddingToFifteenOrFour10JQK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r)
  }
  case object RemovePairsAddingToFifteenOrAPair extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r)
  }
  case object RemovePairsAddingToSeventeenOrA23 extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(17, l.r, r.r)
  }
  case object RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = checkTotal(18, l.r, r.r)
  }
  case object RemoveConsecutiveRankPairs extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = l.r.value == (r.r.value + 1) || l.r.value == (r.r.value - 1)
  }
  case object RemoveConsecutiveRankPairsOrAK extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = {
      l.r.value == (r.r.value + 1) || l.r.value == (r.r.value - 1) || (l.r == Rank.King && r.r == Rank.Ace) || (l.r == Rank.Ace && r.r == Rank.King)
    }
  }
  case object RemoveConsecutiveOrEqualRankPairs extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = false
  }

  case object StackSameRankOrSuitInWaste extends CardRemovalMethod {
    override def canRemove(l: Card, r: Card) = false
  }

  def checkTotal(i: Int, l: Rank, r: Rank, aceHigh: Boolean = false) = {
    val lValue = if (l == Rank.Ace && !aceHigh) { 1 } else { l.value }
    val rValue = if (r == Rank.Ace && !aceHigh) { 1 } else { r.value }
    val totalValue = lValue + rValue
    totalValue == i
  }
}
