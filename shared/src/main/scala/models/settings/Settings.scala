package models.settings

object Settings {
  val default = Settings()
}

case class Settings(
  backgroundColor: String = "#356d88",
  backgroundPattern: Option[String] = Some("black-felt"),
  cardSet: CardSet = CardSet.Default,
  cardBack: CardBack = CardBack.A,
  cardBlank: CardBlank = CardBlank.A,
  cardFaces: CardFaces = CardFaces.A,
  cardLayout: CardLayout = CardLayout.A,
  cardRanks: CardRanks = CardRanks.A,
  cardSuits: CardSuits = CardSuits.A,
  menuPosition: MenuPosition = MenuPosition.Top,
  tilt: Boolean = true,
  autoFlip: Boolean = true,
  audio: Boolean = false,
  language: Language = Language.English
)
