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
) {
  def withNewValue(name: String, value: String) = name match {
    case "card-back" => copy(cards = cards.copy(back = value))
    case "card-layout" => copy(cards = cards.copy(layout = value))
    case "card-rank" => copy(cards = cards.copy(ranks = value))
    case "card-suit" => copy(cards = cards.copy(suits = value))
    case "card-face" => copy(cards = cards.copy(faceCards = value))
    case "auto-flip" => copy(autoFlip = value.toBoolean)
    case "audio" => copy(audio = value.toBoolean)
    case "gamepad" => copy(gamepad = value.toBoolean)
    case "background-color" => copy(color = value)
    case _ => throw new IllegalArgumentException(s"Unhandled preference [$name] for with value [$value].")
  }
}
