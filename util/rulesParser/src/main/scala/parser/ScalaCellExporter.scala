package parser

import models.game.rules._

object ScalaCellExporter {
  def exportCells(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.cells match {
      case Some(c) =>
        val defaults = CellRules()

        add("  cells = Some(")
        add("    CellRules(\n")
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(c.name != defaults.name) {
          props += "      name = \"" + c.name.replaceAllLiterally("\"", "") + "\""
        }
        if(c.pluralName != defaults.pluralName) {
          props += "      pluralName = \"" + c.pluralName + "\""
        }
        if(c.numPiles != defaults.numPiles) {
          props += "      numPiles = " + c.numPiles
        }
        if(c.canMoveFrom != defaults.canMoveFrom) {
          props += "      canMoveFrom = Seq(" + c.canMoveFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }
        if(c.initialCards != defaults.initialCards) {
          props += "      initialCards = " + c.initialCards
        }
        if(c.numEphemeral != defaults.numEphemeral) {
          props += "      numEphemeral = " + c.numEphemeral
        }
        add(props.mkString(",\n"))
        add("    )")
        add("  ),")
      case None =>  // no op
    }
  }
}
