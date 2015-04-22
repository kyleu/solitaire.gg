package utils.parser

import models.game.rules._

object ScalaCellExporter {
  def exportCells(rules: GameRules, ret: StringBuilder) = {
    def add(s: String) = ret ++= s + "\n"

    rules.cells match {
      case Some(c) =>
        add("  cells = Some(")
        add("    CellSet(\n")
        add(s"""      name = "${c.name.replaceAllLiterally("\"", "")}",\n""")
        add(s"""      pluralName = "${c.pluralName}",\n""")
        add(s"""      numPiles = ${c.numPiles},\n""")
        add(s"""      canMoveFrom = Seq(${c.canMoveFrom.map(x => "\"" + x + "\"").mkString(", ")}),\n""")
        add(s"""      initialCards = ${c.initialCards},\n""")
        add(s"""      numEphemeral = ${c.numEphemeral}\n""")
        add("    )")
        add("  ),")
      case None => add("  cells = None,")
    }
  }
}
