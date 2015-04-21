package utils.parser

import models.game.rules._

object ScalaWasteExporter {
  def exportWaste(rules: GameRules, ret: StringBuilder) = {
    def add(s: String) = ret ++= s + "\n"

    rules.waste match {
      case Some(w) =>
        add("""  waste = Some(""")
        add("""    WasteSet(\n""")
        add(s"""      name = "${w.name.replaceAllLiterally("\"", "")}",""")
        add(s"""      numPiles = ${w.numPiles},""")
        add(s"""      playableCards = WastePlayableCards.${w.playableCards}""")
        add("""    )""")
        add("""  ),""")
      case None => add("  waste = None,")
    }
  }
}
