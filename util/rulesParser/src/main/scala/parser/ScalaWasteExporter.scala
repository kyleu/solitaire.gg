package parser

import models.game.rules._

object ScalaWasteExporter {
  private[this] val defaults = WasteRules()

  def exportWaste(rules: GameRules) = {
    rules.waste match {
      case Some(w) =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(w.name != defaults.name) {
          props += "      name = \"" + w.name.replaceAllLiterally("\"", "") + "\""
        }
        if(w.numPiles != defaults.numPiles) {
          props += "      numPiles = " + w.numPiles
        }
        if(w.cardsShown != defaults.cardsShown) {
          props += "      cardsShown = " + w.cardsShown
        }
        if(w.playableCards != defaults.playableCards) {
          props += "      playableCards = WastePlayableCards." + w.playableCards
        }

        Some(if(props.isEmpty) {
          "  waste = Some(WasteRules())"
        } else {
          "  waste = Some(\n" +
          "    WasteRules(\n" +
          props.mkString(",\n") +
          "\n" +
          "    )\n" +
          "  )"
        })
      case None => None
    }
  }
}
