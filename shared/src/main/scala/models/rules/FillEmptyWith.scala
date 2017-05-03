package models.rules

import enumeratum._

sealed trait FillEmptyWith extends EnumEntry

object FillEmptyWith extends Enum[FillEmptyWith] {
  case object Any extends FillEmptyWith
  case object None extends FillEmptyWith
  case object LowRank extends FillEmptyWith
  case object HighRank extends FillEmptyWith
  case object HighRankUntilStockEmpty extends FillEmptyWith
  case object HighRankOrLowRank extends FillEmptyWith
  case object Sevens extends FillEmptyWith

  override val values = findValues
}
