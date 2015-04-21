package models.game.rules

case class ReserveSet(
  name: String,
  numPiles: Int,
  initialCards: Int,
  cardsFaceDown: Int
)
