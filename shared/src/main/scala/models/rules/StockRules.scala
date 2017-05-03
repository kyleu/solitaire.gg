package models.rules

import enumeratum._

sealed trait StockDealTo extends EnumEntry
object StockDealTo extends Enum[StockDealTo] {
  case object Waste extends StockDealTo
  case object WasteOrPairManually extends StockDealTo
  case object Tableau extends StockDealTo
  case object TableauFirstSet extends StockDealTo
  case object TableauIfNoneEmpty extends StockDealTo
  case object TableauEmpty extends StockDealTo
  case object TableauNonEmpty extends StockDealTo
  case object Foundation extends StockDealTo
  case object Reserve extends StockDealTo
  case object Manually extends StockDealTo
  case object Never extends StockDealTo
  override val values = findValues
}

sealed trait StockCardsDealt extends EnumEntry
object StockCardsDealt extends Enum[StockCardsDealt] {
  case class Count(n: Int) extends StockCardsDealt
  case object FewerEachTime extends StockCardsDealt
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
