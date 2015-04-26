package parser

import models.game.rules._

object ScalaReserveExporter {
  def exportReserves(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.reserves match {
      case Some(r) =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        props += "      name = \"" + r.name.replaceAllLiterally("\"", "") + "\""
        props += "      numPiles = " + r.numPiles
        props += "      initialCards = " + r.initialCards
        props += "      cardsFaceDown = " + r.cardsFaceDown

        if(props.isEmpty) {
          add("  reserves = Some(ReserveRules()),")
        } else {
          add("  reserves = Some(")
          add("    ReserveRules(")
          add(props.mkString(",\n"))
          add("    )")
          add("  ),")
        }
      case None =>  // no op
    }
  }
}
