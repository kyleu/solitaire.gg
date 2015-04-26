package parser

import models.game.rules._

object ScalaFoundationExporter {
  def exportFoundations(rules: GameRules, ret: StringBuilder): Unit = {
    def add(s: String) = ret ++= s + "\n"
    def cls(o: Any) = ScalaExporter.cls(o)

    if (rules.foundations.nonEmpty) {
      add("  foundations = Seq(")
      add(rules.foundations.map { f =>
        "    FoundationRules(\n" +
          s"""      name = "${f.name.replaceAllLiterally("\"", "")}",\n""" +
          s"""      numPiles = ${f.numPiles},\n""" +
          s"""      lowRank = FoundationLowRank.${cls(f.lowRank)},\n""" +
          s"""      initialCards = InitialCards.${cls(f.initialCards)},\n""" +
          s"""      suitMatchRule = SuitMatchRule.${cls(f.suitMatchRule)},\n""" +
          s"""      rankMatchRule = RankMatchRule.${cls(f.rankMatchRule)},\n""" +
          s"""      wrapFromKingToAce = ${f.wrapFromKingToAce},\n""" +
          s"""      moveCompleteSequencesOnly = ${f.moveCompleteSequencesOnly},\n""" +
          s"""      maxCards = ${f.maxCards},\n""" +
          s"""      canMoveFrom = FoundationCanMoveFrom.${cls(f.canMoveFrom)},\n""" +
          s"""      mayMoveToFrom = Seq(${f.mayMoveToFrom.map(x => "\"" + x + "\"").mkString(", ")}),\n""" +
          s"""      offscreen = ${f.offscreen},\n""" +
          s"""      autoMoveCards = ${f.autoMoveCards},\n""" +
          s"""      autoMoveFrom = Seq(${f.autoMoveFrom.map(x => "\"" + x + "\"").mkString(", ")})\n""" +
          "    )"
      }.mkString(",\n"))
      add("  ),")
    }
  }
}
