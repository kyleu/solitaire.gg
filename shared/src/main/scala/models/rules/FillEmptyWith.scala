package models.rules

import enumeratum.values._

sealed abstract class FillEmptyWith(val value: Int) extends IntEnumEntry

object FillEmptyWith extends IntEnum[FillEmptyWith] with IntCirceEnum[FillEmptyWith] {
  case object Any extends FillEmptyWith(1)
  case object None extends FillEmptyWith(2)
  case object LowRank extends FillEmptyWith(3)
  case object HighRank extends FillEmptyWith(4)
  case object HighRankUntilStockEmpty extends FillEmptyWith(5)
  case object HighRankOrLowRank extends FillEmptyWith(6)
  case object Sevens extends FillEmptyWith(7)

  override val values = findValues
}
