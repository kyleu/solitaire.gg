package parser

import models.game.rules._

object ScalaPyramidExporter {
  private[this] val defaults = PyramidRules()

  def exportPyramids(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"
    def cls(o: Any) = ScalaExporter.cls(o)

    if (rules.pyramids.nonEmpty) {
      add("  pyramids = Seq(")
      add(rules.pyramids.map { p =>
        val props = collection.mutable.ArrayBuffer.empty[String]
        if(p.name != defaults.name) {
          props += "      name = \"" + p.name.replaceAllLiterally("\"", "") + "\""
        }
        if(p.pyramidType != defaults.pyramidType) {
          props += "      pyramidType = PyramidType." + p.pyramidType
        }
        if(p.height != defaults.height) {
          props += "      height = " + p.height
        }
        if(p.cardsFaceDown != defaults.cardsFaceDown) {
          props += "      cardsFaceDown = PyramidFaceDownCards." + cls(p.cardsFaceDown)
        }
        if(p.suitMatchRuleForBuilding != defaults.suitMatchRuleForBuilding) {
          props += "      suitMatchRuleForBuilding = SuitMatchRule." + cls(p.suitMatchRuleForBuilding)
        }
        if(p.rankMatchRuleForBuilding != defaults.rankMatchRuleForBuilding) {
          props += "      rankMatchRuleForBuilding = RankMatchRule." + cls(p.rankMatchRuleForBuilding)
        }
        if(p.wrapFromKingToAce != defaults.wrapFromKingToAce) {
          props += "      wrapFromKingToAce = " + p.wrapFromKingToAce
        }
        if(p.suitMatchRuleForMovingStacks != defaults.suitMatchRuleForMovingStacks) {
          props += "      suitMatchRuleForMovingStacks = SuitMatchRule." + cls(p.suitMatchRuleForMovingStacks)
        }
        if(p.rankMatchRuleForMovingStacks != defaults.rankMatchRuleForMovingStacks) {
          props += "      rankMatchRuleForMovingStacks = RankMatchRule." + cls(p.rankMatchRuleForMovingStacks)
        }
        if(!p.mayMoveToNonEmptyFrom.sameElements(defaults.mayMoveToNonEmptyFrom)) {
          props += "      mayMoveToNonEmptyFrom = Seq(" + p.mayMoveToNonEmptyFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }
        if(!p.mayMoveToEmptyFrom.sameElements(defaults.mayMoveToEmptyFrom)) {
          props += "      mayMoveToEmptyFrom = Seq(" + p.mayMoveToEmptyFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
        }
        if(p.emptyFilledWith != defaults.emptyFilledWith) {
          props += "      emptyFilledWith = PyramidFillEmptyWith." + cls(p.emptyFilledWith)
        }

        if(props.isEmpty) {
          "    PyramidRules()"
        } else {
          "    PyramidRules(\n" + props.mkString(",\n") + "\n    )"
        }
      }.mkString(",\n"))
      add("  ),")
    }
  }
}
