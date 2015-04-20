package models.game.rules

sealed trait StockDealTo
object StockDealTo {
  case object Waste extends StockDealTo
  case object WasteOrPairManually extends StockDealTo
  case object Tableau extends StockDealTo
  case object TableauFirstSet extends StockDealTo
  case object TableauIfNoneEmpty extends StockDealTo
  case object TableauNonEmpty extends StockDealTo
  case object Foundation extends StockDealTo
  case object Reserve extends StockDealTo
  case object Manually extends StockDealTo
  case object Never extends StockDealTo
}

sealed trait StockCardsDealt
object StockCardsDealt {
  case class Count(n: Int) extends StockCardsDealt
  case object FewerEachTime extends StockCardsDealt
}

case class Stock(
  name: String,
  dealTo: StockDealTo,
  maximumDeals: Option[Int],
  cardsDealt: StockCardsDealt,
  stopAfterPartialDeal: Boolean,
  createPocketWhenEmpty: Boolean,
  galleryMode: Boolean
)
