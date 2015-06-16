package models.game.rules

case class CellRules(
  name: String = "Cell",
  pluralName: String = "Cells",
  numPiles: Int = 4,
  mayMoveToFrom: Seq[String] = GameRules.allSources,
  initialCards: Int = 0,
  numEphemeral: Int = 0
)
