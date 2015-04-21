package utils.parser

import models.game.rules._

object ScalaReserveExporter {
  def exportReserves(rules: GameRules, ret: StringBuilder) = {
    def add(s: String) = ret ++= s + "\n"

    rules.reserves match {
      case Some(r) =>
        add("""  reserves = Some(""")
        add("""    ReserveSet(\n""")
        add(s"""      name = "${r.name.replaceAllLiterally("\"", "")}",\n""")
        add(s"""      numPiles = ${r.numPiles},\n""")
        add(s"""      initialCards = ${r.initialCards},\n""")
        add(s"""      cardsFaceDown = ${r.cardsFaceDown}\n""")
        add("""    )""")
        add("""  ),""")
      case None => add("  reserves = None,")
    }
  }
}
