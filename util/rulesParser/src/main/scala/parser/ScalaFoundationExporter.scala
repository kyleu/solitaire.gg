package parser

import models.game.rules._

object ScalaFoundationExporter {
  private[this] val defaults = FoundationRules()

  def exportFoundations(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"
    def cls(o: Any) = ScalaExporter.cls(o)

    if (rules.foundations.nonEmpty) {
      add("  foundations = Seq(")
      add(rules.foundations.map { f =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(f.name != defaults.name) {
          props += "      name = \"" + f.name.replaceAllLiterally("\"", "") + "\""
        }
        if(f.numPiles != defaults.numPiles) {
          props += "      numPiles = " + f.numPiles
        }
        if(f.lowRank != defaults.lowRank) {
          props += "      lowRank = FoundationLowRank." + cls(f.lowRank)
        }
        if(f.initialCards != defaults.initialCards) {
          props += "      initialCards = InitialCards." + cls(f.initialCards)
        }
        if(f.suitMatchRule != defaults.suitMatchRule) {
          props += "      suitMatchRule = SuitMatchRule." + cls(f.suitMatchRule)
        }
        if(f.rankMatchRule != defaults.rankMatchRule) {
          props += "      rankMatchRule = RankMatchRule." + cls(f.rankMatchRule)
        }
        if(f.wrapFromKingToAce != defaults.wrapFromKingToAce) {
          props += "      wrapFromKingToAce = " + f.wrapFromKingToAce
        }
        if(f.moveCompleteSequencesOnly != defaults.moveCompleteSequencesOnly) {
          props += "      moveCompleteSequencesOnly = " + f.moveCompleteSequencesOnly
        }
        if(f.maxCards != defaults.maxCards) {
          props += "      maxCards = " + f.maxCards
        }
        if(f.canMoveFrom != defaults.canMoveFrom) {
          props += "      canMoveFrom = FoundationCanMoveFrom." + cls(f.canMoveFrom)
        }
        if(!f.mayMoveToFrom.sameElements(defaults.mayMoveToFrom)) {
          props += "      mayMoveToFrom = Seq(" + f.mayMoveToFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }
        if(f.offscreen != defaults.offscreen) {
          props += "      offscreen = " + f.offscreen
        }
        if(f.autoMoveCards != defaults.autoMoveCards) {
          props += "      autoMoveCards = " + f.autoMoveCards
        }
        if(!f.autoMoveFrom.sameElements(defaults.autoMoveFrom)) {
          props += "      autoMoveFrom = Seq(" + f.autoMoveFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }

        if(props.isEmpty) {
          "    FoundationRules()"
        } else {
          "    FoundationRules(\n" + props.mkString(",\n") + "\n    )"
        }
      }.mkString(",\n"))
      add("  ),")
    }
  }
}
