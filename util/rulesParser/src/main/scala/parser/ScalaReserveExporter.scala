package parser

import models.game.rules._

object ScalaReserveExporter {
  def exportReserves(rules: GameRules) = {
    rules.reserves match {
      case Some(r) =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        props += "      name = \"" + r.name.replaceAllLiterally("\"", "") + "\""
        props += "      numPiles = " + r.numPiles
        props += "      initialCards = " + r.initialCards
        props += "      cardsFaceDown = " + r.cardsFaceDown

        Some(if(props.isEmpty) {
          "  reserves = Some(ReserveRules())"
        } else {
          "  reserves = Some(\n" +
          "    ReserveRules(\n" +
          props.mkString(",\n") +
          "\n" +
          "    )\n" +
          "  )"
        })
      case None => None
    }
  }
}
