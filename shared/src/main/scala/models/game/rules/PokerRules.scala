package models.game.rules

import models.game.{ Suit, Rank, Card }

object PokerRules {
  sealed abstract class Hand(rank: Int)
  case object RoyalFlush extends Hand(1)
  case object StraightFlush extends Hand(2)
  case object FourOfKind extends Hand(3)
  case object FullHouse extends Hand(4)
  case object Flush extends Hand(5)
  case object Straight extends Hand(6)
  case object ThreeOfKind extends Hand(7)
  case object TwoPair extends Hand(8)
  case object OnePair extends Hand(9)
  case object HighCard extends Hand(10)

  def checkHand(hand: Seq[Card]) = check(hand.map(c => c.r -> c.s))

  private[this] def check(hand: Seq[(Rank, Suit)]) = {
    if (hand.length != 5) {
      throw new NotImplementedError("PokerRules.OddSizeHand")
    }

    val ranks = hand.map(_._1).groupBy(x => x).map(x => (x._1, x._2.size)).toIndexedSeq.sortBy(-_._2)
    val firstRank = ranks.headOption.getOrElse(throw new IllegalStateException())
    val secondRank = ranks(1)

    val suits = hand.map(_._2).groupBy(x => x).map(x => (x._1, x._2.size)).toIndexedSeq.sortBy(-_._2)

    val isFlush = suits.size == 1
    val straight = if (ranks.length == 5) {
      val ranksSorted = ranks.map(_._1).sortBy(-_.value)
      val first = ranksSorted.headOption.getOrElse(throw new IllegalStateException())
      val second = ranksSorted(1)
      val last = ranksSorted.lastOption.getOrElse(throw new IllegalStateException())

      if (first.value == (last.value + 4)) {
        Some(first)
      } else if (first == Rank.Ace && second == Rank.Five) {
        Some(Rank.Five)
      } else {
        None
      }
    } else {
      None
    }

    true match {
      case _ if isFlush && straight.contains(Rank.Ace) => RoyalFlush
      case _ if isFlush && straight.isDefined => StraightFlush
      case _ if firstRank._2 == 4 => FourOfKind
      case _ if firstRank._2 == 3 && secondRank._2 == 2 => FullHouse
      case _ if isFlush => Flush
      case _ if straight.isDefined => Straight
      case _ if firstRank._2 == 3 => ThreeOfKind
      case _ if ranks.length == 3 => TwoPair
      case _ if ranks.length == 4 => OnePair
      case _ => HighCard
    }
  }
}
