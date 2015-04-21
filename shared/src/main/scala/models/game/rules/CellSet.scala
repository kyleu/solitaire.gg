package models.game.rules

case class CellSet(
  name: String,
  pluralName: String,
  numPiles: Int,
  canMoveFrom: Seq[String],
  initialCards: Int,
  numEphemeral: Int
)
