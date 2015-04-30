package parser

import models.game.rules._

object ScalaWasteExporter {
  private[this] val defaults = WasteRules()

  def exportWaste(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

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

        if(props.isEmpty) {
          add("  waste = Some(WasteRules()),")
        } else {
          add("  waste = Some(")
          add("    WasteRules(")
          add(props.mkString(",\n"))
          add("    )")
          add("  ),")
        }
      case None => //no op
    }
  }
}
