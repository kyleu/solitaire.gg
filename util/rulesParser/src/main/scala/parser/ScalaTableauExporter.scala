package parser

import models.game.rules._

object ScalaTableauExporter {
  private[this] val defaults = TableauRules()

  def exportTableaus(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"
    def cls(o: Any) = ScalaExporter.cls(o)

    if (rules.tableaus.nonEmpty) {
      add("  tableaus = Seq(")
      add(rules.tableaus.map { t =>
        "    TableauRules(\n" + {
          val props = collection.mutable.ArrayBuffer.empty[String]
          if(t.name != defaults.name) {
            props += "      name = \"" + t.name.replaceAllLiterally("\"", "") + "\""
          }
          if(t.numPiles != defaults.numPiles) {
            props += "      numPiles = " + t.numPiles
          }
          if(t.initialCards != defaults.initialCards) {
            props += "      initialCards = InitialCards." + cls(t.initialCards)
          }
          if(t.cardsFaceDown != defaults.cardsFaceDown) {
            props += "      cardsFaceDown = TableauFaceDownCards." + cls(t.cardsFaceDown)
          }
          if(t.suitMatchRuleForBuilding != defaults.suitMatchRuleForBuilding) {
            props += "      suitMatchRuleForBuilding = SuitMatchRule." + cls(t.suitMatchRuleForBuilding)
          }
          if(t.rankMatchRuleForBuilding != defaults.rankMatchRuleForBuilding) {
            props += "      rankMatchRuleForBuilding = RankMatchRule." + cls(t.rankMatchRuleForBuilding)
          }
          if(t.wrapFromKingToAce != defaults.wrapFromKingToAce) {
            props += "      wrapFromKingToAce = " + t.wrapFromKingToAce
          }
          if(t.suitMatchRuleForMovingStacks != defaults.suitMatchRuleForMovingStacks) {
            props += "      suitMatchRuleForMovingStacks = SuitMatchRule." + cls(t.suitMatchRuleForMovingStacks)
          }
          if(t.rankMatchRuleForMovingStacks != defaults.rankMatchRuleForMovingStacks) {
            props += "      rankMatchRuleForMovingStacks = RankMatchRule." + cls(t.rankMatchRuleForMovingStacks)
          }
          if(t.autoFillEmptyFrom != defaults.autoFillEmptyFrom) {
            props += "      autoFillEmptyFrom = TableauAutoFillEmptyFrom." + cls(t.autoFillEmptyFrom)
          }
          if(t.emptyFilledWith != defaults.emptyFilledWith) {
            props += "      emptyFilledWith = TableauFillEmptyWith." + cls(t.emptyFilledWith)
          }
          if(!t.mayMoveToNonEmptyFrom.sameElements(defaults.mayMoveToNonEmptyFrom)) {
            props += "      mayMoveToNonEmptyFrom = Seq(" + t.mayMoveToNonEmptyFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
          }
          if(!t.mayMoveToEmptyFrom.sameElements(defaults.mayMoveToEmptyFrom)) {
            props += "      mayMoveToEmptyFrom = Seq(" + t.mayMoveToEmptyFrom.map(x => "\"" + x + "\"").mkString(", ") + ")"
          }
          if(t.maxCards != defaults.maxCards) {
            props += "      maxCards = " + t.maxCards
          }
          if(t.actionDuringDeal != defaults.actionDuringDeal) {
            props += "      actionDuringDeal = PileAction." + cls(t.actionDuringDeal)
          }
          if(t.actionAfterDeal != defaults.actionAfterDeal) {
            props += "      actionAfterDeal = PileAction." + cls(t.actionAfterDeal)
          }
          if(t.pilesWithLowCardsAtBottom != defaults.pilesWithLowCardsAtBottom) {
            props += "      pilesWithLowCardsAtBottom = " + t.pilesWithLowCardsAtBottom
          }
          props.mkString(",\n")
        } + "\n    )"
      }.mkString(",\n"))
      add("  ),")
    }
  }
}
