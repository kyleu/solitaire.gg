package models.game.rules

sealed trait FillEmptyWith

object FillEmptyWith {
  case object Any extends FillEmptyWith
  case object None extends FillEmptyWith
  case object LowRank extends FillEmptyWith
  case object HighRank extends FillEmptyWith
  case object HighRankUntilStockEmpty extends FillEmptyWith
  case object HighRankOrLowRank extends FillEmptyWith
  case object Sevens extends FillEmptyWith
}
