package parser

import models.game.rules._

object ScalaCellExporter {
  private[this] val defaults = CellRules()

  def exportCells(rules: GameRules) = {
    rules.cells match {
      case Some(c) =>
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
        if(!c.canMoveFrom.sameElements(defaults.canMoveFrom)) {
          props += "      canMoveFrom = Seq(" + c.canMoveFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }
        if(c.initialCards != defaults.initialCards) {
          props += "      initialCards = " + c.initialCards
        }
        if(c.numEphemeral != defaults.numEphemeral) {
          props += "      numEphemeral = " + c.numEphemeral
        }

        Some(if(props.isEmpty) {
          "  cells = Some(CellRules())"
        } else {
          "  cells = Some(\n" +
          "    CellRules(\n" +
          props.mkString(",\n") +
          "\n" +
          "    )\n" +
          "  )"
        })
      case None => None
    }
  }
}
