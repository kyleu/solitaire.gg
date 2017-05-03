package models.rules

import enumeratum._

sealed trait WastePlayableCards extends EnumEntry
object WastePlayableCards extends Enum[WastePlayableCards] {
  case object TopCardOnly extends WastePlayableCards
  case object All extends WastePlayableCards
  override val values = findValues
}

case class WasteRules(
  name: String = "Waste",
  numPiles: Int = 1,
  cardsShown: Int = 3,
  maxCards: Option[Int] = None,
  playableCards: WastePlayableCards = WastePlayableCards.TopCardOnly
)
