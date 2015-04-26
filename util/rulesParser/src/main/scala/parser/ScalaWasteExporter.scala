package parser

import models.game.rules._

object ScalaWasteExporter {
  private[this] val defaults = WasteRules()

  def exportWaste(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"

    rules.waste match {
      case Some(w) =>
        add("  waste = Some(")
        add("    WasteRules(")
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(w.name != defaults.name) {
          props += "      name = \"" + w.name.replaceAllLiterally("\"", "") + "\""
        }
        if(w.numPiles != defaults.numPiles) {
          props += "      numPiles = " + w.numPiles
        }
        if(w.playableCards != defaults.playableCards) {
          props += "      playableCards = WastePlayableCards." + w.playableCards
        }
        add(props.mkString(",\n"))
        add("    )")
        add("  ),")
      case None => //no op
    }
  }
}
