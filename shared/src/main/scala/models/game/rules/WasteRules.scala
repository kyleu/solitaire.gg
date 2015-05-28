package models.game.rules

sealed trait WastePlayableCards
object WastePlayableCards {
  case object TopCardOnly extends WastePlayableCards
  case object All extends WastePlayableCards
}

case class WasteRules(
  name: String = "Waste",
  numPiles: Int = 1,
  cardsShown: Int = 3,
  maxCards: Option[Int] = None,
  playableCards: WastePlayableCards = WastePlayableCards.TopCardOnly
)
