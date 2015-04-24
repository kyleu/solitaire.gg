package models.game.rules

sealed trait WastePlayableCards
object WastePlayableCards {
  case object TopCardOnly extends WastePlayableCards
  case object All extends WastePlayableCards
}

case class WasteRules(
  name: String,
  numPiles: Int,
  playableCards: WastePlayableCards
)
