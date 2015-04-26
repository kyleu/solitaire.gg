package parser

import models.game.rules._

object ScalaWasteExporter {
  def exportWaste(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.waste match {
      case Some(w) =>
        add("  waste = Some(")
        add("    WasteRules(")
        add(s"""      name = "${w.name.replaceAllLiterally("\"", "")}",""")
        add(s"""      numPiles = ${w.numPiles},""")
        add(s"""      playableCards = WastePlayableCards.${w.playableCards}""")
        add("    )")
        add("  ),")
      case None => //no op
    }
  }
}
