package models.game.rules

sealed trait FillEmptyWith

object FillEmptyWith {
  case object Any extends FillEmptyWith
  case object None extends FillEmptyWith
  case object Aces extends FillEmptyWith
  case object Kings extends FillEmptyWith
  case object KingsUntilStockEmpty extends FillEmptyWith
  case object KingsOrAces extends FillEmptyWith
  case object Sevens extends FillEmptyWith
}
