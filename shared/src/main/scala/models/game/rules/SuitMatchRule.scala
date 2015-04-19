package models.game.rules

sealed trait SuitMatchRule

object SuitMatchRule {
  case object SameSuit extends SuitMatchRule
  case object DifferentSuits extends SuitMatchRule
  case object SameColor extends SuitMatchRule
  case object AlternatingColors extends SuitMatchRule
  case object Any extends SuitMatchRule
}
