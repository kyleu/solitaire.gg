package models.rules

import enumeratum.values._

sealed abstract class FillEmptyWith(val value: String) extends StringEnumEntry

object FillEmptyWith extends StringEnum[FillEmptyWith] with StringUPickleEnum[FillEmptyWith] {
  case object Any extends FillEmptyWith("any")
  case object None extends FillEmptyWith("none")
  case object LowRank extends FillEmptyWith("low-rank")
  case object HighRank extends FillEmptyWith("high-rank")
  case object HighRankUntilStockEmpty extends FillEmptyWith("high-rank-until-stock-empty")
  case object HighRankOrLowRank extends FillEmptyWith("high-rank-or-low-rank")
  case object Sevens extends FillEmptyWith("sevens")

  override val values = findValues
}
