package models.rules

case class ReserveRules(
  name: String = "Reserve",
  numPiles: Int = 1,
  initialCards: Int = 1,
  cardsFaceDown: Int = 0
)
