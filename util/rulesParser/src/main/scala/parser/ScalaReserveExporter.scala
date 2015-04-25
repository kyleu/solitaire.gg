package parser

import models.game.rules._

object ScalaReserveExporter {
  def exportReserves(rules: GameRules, ret: StringBuilder) = {
    def add(s: String) = ret ++= s + "\n"

    rules.reserves match {
      case Some(r) =>
        add("  reserves = Some(")
        add("    ReserveRules(")
        add(s"""      name = "${r.name.replaceAllLiterally("\"", "")}",""")
        add(s"""      numPiles = ${r.numPiles},""")
        add(s"""      initialCards = ${r.initialCards},""")
        add(s"""      cardsFaceDown = ${r.cardsFaceDown}""")
        add("    )")
        add("  ),")
      case None => add("  reserves = None,")
    }
  }
}
