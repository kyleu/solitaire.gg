package models.rules

import enumeratum._
import models.card.Suit

sealed trait SuitMatchRule extends EnumEntry {
  def check(l: Suit, r: Suit): Boolean
}

object SuitMatchRule extends Enum[SuitMatchRule] {
  case object None extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = false
  }
  case object SameSuit extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.index == r.index
  }
  case object DifferentSuits extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.index != r.index
  }
  case object SameColor extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.color == r.color
  }
  case object AlternatingColors extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.color != r.color
  }
  case object Any extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = true
  }
  override val values = findValues
}
