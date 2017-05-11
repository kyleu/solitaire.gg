package models.rules

import enumeratum.values._
import models.card.{Card, Rank}

sealed abstract class CardRemovalMethod(val value: Int) extends IntEnumEntry {
  def canRemove(l: Card, r: Card): Boolean = false
  def canSelect(c: Card): Boolean = false
}

object CardRemovalMethod extends IntEnum[CardRemovalMethod] with IntUPickleEnum[CardRemovalMethod] {
  val default = BuildSequencesOnFoundation

  case object BuildSequencesOnFoundation extends CardRemovalMethod(1)

  case object RemovePairsOfSameRank extends CardRemovalMethod(2) {
    override def canRemove(l: Card, r: Card) = l.r == r.r
  }
  case object RemovePairsOfSameRankAndColor extends CardRemovalMethod(3) {
    override def canRemove(l: Card, r: Card) = l.r == r.r && l.s.color == r.s.color
  }
  case object RemovePairsOfSameSuit extends CardRemovalMethod(4) {
    override def canRemove(l: Card, r: Card) = l.s == r.s
  }
  case object RemoveNinesOrPairsAddingToNineOr10JQK extends CardRemovalMethod(5) {
    override def canRemove(l: Card, r: Card) = checkTotal(9, l.r, r.r) // TODO 10-J-Q-K
    override def canSelect(c: Card) = c.r == Rank.Nine
  }
  case object RemovePairsAddingToTenOr10PairOrJPairOrQPairOrKPair extends CardRemovalMethod(6) {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r) || {
      l.r == r.r && (l.r == Rank.Ten || l.r == Rank.Jack || l.r == Rank.Queen || l.r == Rank.King)
    }
  }
  case object RemovePairsAddingToTenOr10JQK extends CardRemovalMethod(7) {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r) // TODO 10-J-Q-K
  }
  case object RemovePairsAddingToTenOrFour10JQK extends CardRemovalMethod(8) {
    override def canRemove(l: Card, r: Card) = checkTotal(10, l.r, r.r) // TODO 10-J-Q-K
  }
  case object RemovePairsAddingToElevenOrJPairOrQK extends CardRemovalMethod(9) {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r) || (l.r == Rank.Jack && r.r == Rank.Jack) // TODO Q-K
  }
  case object RemovePairsAddingToElevenOrJPairOrQPairOrKPair extends CardRemovalMethod(10) {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r) || {
      l.r == r.r && (l.r == Rank.Jack || l.r == Rank.Queen || l.r == Rank.King)
    }
  }
  case object RemovePairsAddingToElevenOrJQK extends CardRemovalMethod(11) {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r) // TODO J-Q-K
  }
  case object RemoveSameSuitPairsAddingToElevenOrJQK extends CardRemovalMethod(12) {
    override def canRemove(l: Card, r: Card) = checkTotal(11, l.r, r.r) && l.s == r.s // TODO J-Q-K
  }
  case object RemovePairsAddingToTwelveOrQK extends CardRemovalMethod(13) {
    override def canRemove(l: Card, r: Card) = checkTotal(12, l.r, r.r) // TODO Q-K
  }
  case object RemovePairsAddingToThirteenOrK extends CardRemovalMethod(14) {
    override def canRemove(l: Card, r: Card) = checkTotal(13, l.r, r.r)
    override def canSelect(c: Card) = c.r == Rank.King
  }
  case object RemovePairsAddingToFourteen extends CardRemovalMethod(15) {
    override def canRemove(l: Card, r: Card) = checkTotal(14, l.r, r.r)
  }
  case object RemoveSetsAddingToFifteenOr10JQK extends CardRemovalMethod(17) {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r) // TODO 10-J-Q-K
  }
  case object RemoveSetsAddingToFifteenOrFour10JQK extends CardRemovalMethod(18) {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r) // TODO 10-J-Q-K
  }
  case object RemovePairsAddingToFifteenOrAPair extends CardRemovalMethod(19) {
    override def canRemove(l: Card, r: Card) = checkTotal(15, l.r, r.r) || (l.r == r.r)
  }
  case object RemovePairsAddingToSeventeenOrA23 extends CardRemovalMethod(20) {
    override def canRemove(l: Card, r: Card) = checkTotal(17, l.r, r.r) // TODO A-2-3
  }
  case object RemoveSetsOfOneFaceCardAnd3ThatAddToEighteen extends CardRemovalMethod(21) {
    override def canRemove(l: Card, r: Card) = checkTotal(18, l.r, r.r) // TODO Any three adding to 18
  }
  case object RemoveConsecutiveRankPairs extends CardRemovalMethod(22) {
    override def canRemove(l: Card, r: Card) = l.r.index == (r.r.index + 1) || l.r.index == (r.r.index - 1)
  }
  case object RemoveConsecutiveRankPairsOrAK extends CardRemovalMethod(23) {
    override def canRemove(l: Card, r: Card) = {
      l.r.index == (r.r.index + 1) || l.r.index == (r.r.index - 1) // TODO A-K
    }
  }
  case object RemoveConsecutiveOrEqualRankPairs extends CardRemovalMethod(24) {
    override def canRemove(l: Card, r: Card) = l.r == r.r || l.r.index == (r.r.index + 1) || l.r.index == (r.r.index - 1)
  }

  def checkTotal(i: Int, l: Rank, r: Rank, aceHigh: Boolean = false) = {
    val lValue = if (l == Rank.Ace && !aceHigh) { 1 } else { l.index }
    val rValue = if (r == Rank.Ace && !aceHigh) { 1 } else { r.index }
    val totalValue = lValue + rValue
    totalValue == i
  }

  override val values = findValues
}
