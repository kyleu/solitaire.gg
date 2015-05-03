package models.game.rules

import models.game.Suit

sealed trait SuitMatchRule {
  def check(l: Suit, r: Suit): Boolean
  def toWords: String
}

object SuitMatchRule {
  case object None extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = false
    override def toWords = "never"
  }
  case object SameSuit extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.value == r.value
    override def toWords = "the same suit"
  }
  case object DifferentSuits extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.value != r.value
    override def toWords = "different suits"
  }
  case object SameColor extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.color == r.color
    override def toWords = "the same color"
  }
  case object AlternatingColors extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = l.color != r.color
    override def toWords = "a different color"
  }
  case object Any extends SuitMatchRule {
    override def check(l: Suit, r: Suit) = true
    override def toWords = "any suit"
  }
}
