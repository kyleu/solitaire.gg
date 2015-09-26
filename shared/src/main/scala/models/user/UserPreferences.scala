package models.user

case class CardPreferences(
  back: String = "a",
  layout: String = "a",
  ranks: String = "a",
  suits: String = "a",
  faceCards: String = "a"
)

case class UserPreferences(
  avatar: String = "guest",
  color: String = "greyblue",
  cards: CardPreferences = CardPreferences(),
  autoFlip: Boolean = true,
  audio: Boolean = false,
  gamepad: Boolean = false
)
