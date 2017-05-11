package models.rules

import enumeratum.values._

sealed abstract class WastePlayableCards(val value: String) extends StringEnumEntry
object WastePlayableCards extends StringEnum[WastePlayableCards] with StringUPickleEnum[WastePlayableCards] {
  case object TopCardOnly extends WastePlayableCards("top-card-only")
  case object All extends WastePlayableCards("all")
  override val values = findValues
}

case class WasteRules(
  name: String = "Waste",
  numPiles: Int = 1,
  cardsShown: Int = 3,
  maxCards: Option[Int] = None,
  playableCards: WastePlayableCards = WastePlayableCards.TopCardOnly
)
