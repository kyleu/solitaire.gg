package utils.parser

import models.game.rules._

object ScalaTableauExporter {
  def exportTableaus(rules: GameRules, ret: StringBuilder) = {
    def add(s: String) = ret ++= s + "\n"
    def cls(o: Any) = ScalaExporter.cls(o)

    if (rules.tableaus.isEmpty) {
      add("  tableaus = Nil,")
    } else {
      add("  tableaus = Seq(")
      add(rules.tableaus.map { t =>
        "    TableauSet(\n" +
          s"""      name = "${t.name.replaceAllLiterally("\"", "")}",\n""" +
          s"""      numPiles = ${t.numPiles},\n""" +
          s"""      initialCards = InitialCards.${cls(t.initialCards)},\n""" +
          s"""      cardsFaceDown = TableauFaceDownCards.${cls(t.cardsFaceDown)},\n""" +
          s"""      suitMatchRuleForBuilding = SuitMatchRule.${cls(t.suitMatchRuleForBuilding)},\n""" +
          s"""      rankMatchRuleForBuilding = RankMatchRule.${cls(t.rankMatchRuleForBuilding)},\n""" +
          s"""      wrapFromKingToAce = ${t.wrapFromKingToAce},\n""" +
          s"""      suitMatchRuleForMovingStacks = SuitMatchRule.${cls(t.suitMatchRuleForMovingStacks)},\n""" +
          s"""      rankMatchRuleForMovingStacks = RankMatchRule.${cls(t.rankMatchRuleForMovingStacks)},\n""" +
          s"""      autoFillEmptyFrom = TableauAutoFillEmptyFrom.${cls(t.autoFillEmptyFrom)},\n""" +
          s"""      emptyFilledWith = TableauFillEmptyWith.${cls(t.emptyFilledWith)},\n""" +
          s"""      mayMoveToNonEmptyFrom = Seq(${t.mayMoveToNonEmptyFrom.map(x => "\"" + x + "\"").mkString(", ")}),\n""" +
          s"""      mayMoveToEmptyFrom = Seq(${t.mayMoveToEmptyFrom.map(x => "\"" + x + "\"").mkString(", ")}),\n""" +
          s"""      maxCards = ${t.maxCards},\n""" +
          s"""      actionDuringDeal = PileAction.${cls(t.actionDuringDeal)},\n""" +
          s"""      actionAfterDeal = PileAction.${cls(t.actionAfterDeal)},\n""" +
          s"""      pilesWithLowCardsAtBottom = ${t.pilesWithLowCardsAtBottom}\n""" +
          "    )"
      }.mkString(",\n"))
      add("  ),")
    }
  }
}
