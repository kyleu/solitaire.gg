package models.rules

import models.pile.set.PileSet

case class CellRules(
  name: String = "Cell",
  pluralName: String = "Cells",
  numPiles: Int = 4,
  mayMoveToFrom: Seq[PileSet.Behavior] = PileSet.Behavior.values,
  initialCards: Int = 0,
  numEphemeral: Int = 0
)
