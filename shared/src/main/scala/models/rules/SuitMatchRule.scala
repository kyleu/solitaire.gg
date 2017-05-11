package models.rules

import enumeratum.values._
import models.card.Suit

sealed abstract class SuitMatchRule(val value: String) extends StringEnumEntry {
  def check(l: Suit, r: Suit): Boolean
}

object SuitMatchRule extends StringEnum[SuitMatchRule] with StringUPickleEnum[SuitMatchRule] {
  case object None extends SuitMatchRule("none") {
    override def check(l: Suit, r: Suit) = false
  }
  case object SameSuit extends SuitMatchRule("same-suit") {
    override def check(l: Suit, r: Suit) = l.index == r.index
  }
  case object DifferentSuits extends SuitMatchRule("different-suits") {
    override def check(l: Suit, r: Suit) = l.index != r.index
  }
  case object SameColor extends SuitMatchRule("same-color") {
    override def check(l: Suit, r: Suit) = l.color == r.color
  }
  case object AlternatingColors extends SuitMatchRule("alternating-colors") {
    override def check(l: Suit, r: Suit) = l.color != r.color
  }
  case object Any extends SuitMatchRule("any") {
    override def check(l: Suit, r: Suit) = true
  }
  override val values = findValues
}
