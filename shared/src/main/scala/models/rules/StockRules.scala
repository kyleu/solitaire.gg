package models.rules

import enumeratum.values._

sealed abstract class StockDealTo(val value: String) extends StringEnumEntry
object StockDealTo extends StringEnum[StockDealTo] with StringCirceEnum[StockDealTo] {
  case object Waste extends StockDealTo("waste")
  case object WasteOrPairManually extends StockDealTo("waste-or-pair-manually")
  case object Tableau extends StockDealTo("tableau")
  case object TableauFirstSet extends StockDealTo("tableau-first-set")
  case object TableauIfNoneEmpty extends StockDealTo("tableau-if-none-empty")
  case object TableauEmpty extends StockDealTo("tableau-empty")
  case object TableauNonEmpty extends StockDealTo("tableau-non-empty")
  case object Foundation extends StockDealTo("foundation")
  case object Reserve extends StockDealTo("reserve")
  case object Manually extends StockDealTo("manually")
  case object Never extends StockDealTo("never")
  override val values = findValues
}

sealed abstract class StockCardsDealt(val value: String) extends StringEnumEntry
object StockCardsDealt extends StringEnum[StockCardsDealt] with StringCirceEnum[StockCardsDealt] {
  case class Count(n: Int) extends StockCardsDealt("count")
  case object FewerEachTime extends StockCardsDealt("fewer-each-time")
  override val values = findValues
}

case class StockRules(
  name: String = "Stock",
  cardsShown: Int = 1,
  dealTo: StockDealTo = StockDealTo.Waste,
  maximumDeals: Option[Int] = None,
  cardsDealt: StockCardsDealt = StockCardsDealt.Count(1),
  stopAfterPartialDeal: Boolean = true,
  createPocketWhenEmpty: Boolean = false,
  galleryMode: Boolean = false
)
