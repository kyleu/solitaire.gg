package utils.parser

import java.nio.file.{ Files, Path, Paths }

import models.game.rules._

object PolitaireScalaExporter {
  def export(rulesSet: Seq[GameRules]) = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "generated")
    writeFile(srcDir.resolve("GameRulesSet.scala"), exportRulesSet(rulesSet))

    for(rules <- rulesSet) {
      writeFile(srcDir.resolve(getObjectName(rules) + ".scala"), exportRules(rules))
    }
  }

  def exportRulesSet(rulesSet: Seq[GameRules]) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.generated")
    add("")
    add("object GameRulesSet {")
    add("  val all = Seq(")
    //add(ruleset.groupBy(_.title.head).toList.sortBy(_._1).map(l => "    " + l._2.map( r => getObjectName(r)).mkString(", ")).mkString("\n"))
    add(rulesSet.map(r => "    " + getObjectName(r)).mkString(",\n"))
    add("  )")
    add("")
    add("  val allById = all.map(x => x.id -> x).toMap")
    add("}")
    add("")

    ret.toString()
  }

  def exportRules(rules: GameRules) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.generated")
    add("")
    add("import models.game._")
    add("import models.game.rules._")
    add("")
    add(s"object ${getObjectName(rules)} extends GameRules(")
    add(s"""  id = \"${rules.id}\",""")
    add(s"""  title = \"${rules.title}\",""")
    add(s"""  description = \"${rules.description.replaceAllLiterally("\"", "\\\"")}\",""")
    add(s"""  victoryCondition = VictoryCondition.${cls(rules.victoryCondition)},""")
    add(s"""  cardRemovalMethod = CardRemovalMethod.${cls(rules.cardRemovalMethod)},""")
    add(s"""  deckOptions = DeckOptions(""")
    add(s"""    numDecks = ${rules.deckOptions.numDecks},""")
    add(s"""    suits = Seq(${rules.deckOptions.suits.map(x => "Suit." + x).mkString(", ")}),""")
    add(s"""    ranks = Seq(${rules.deckOptions.ranks.map(x => "Rank." + x).mkString(", ")}),""")
    add(s"""    lowRank = ${rules.deckOptions.lowRank.map("Rank." + _)}""")
    add(s"""  ),""")
    if(rules.foundations.isEmpty) {
      add(s"""  foundations = Nil,""")
    } else {
      add(s"""  foundations = Seq(""")
      add(rules.foundations.map { f =>
        s"""    FoundationSet(\n""" +
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
        s"""    )"""
      }.mkString(",\n"))
      add(s"""  ),""")
    }
    if(rules.tableaus.isEmpty) {
      add( s"""  tableaus = Nil""")
    } else {
      add(s"""  tableaus = Seq(""")
      add(rules.tableaus.map { t =>
        s"""    TableauSet(\n""" +
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
        s"""    )"""
      }.mkString(",\n"))
      add(s"""  )""")
    }
    add(")")
    add("")

    ret.toString()
  }

  private def cls(o: Any) = o match {
    case count: InitialCards.Count => "Count(" + count.n + ")"
    case count: TableauFaceDownCards.Count => "Count(" + count.n + ")"
    case specificRank: FoundationLowRank.SpecificRank => "SpecificRank(Rank." + specificRank.r + ")"
    case cn => cn.getClass.getSimpleName.replaceAllLiterally("$", "")
  }
  private def getObjectName(rules: GameRules) = rules.title.replaceAll("[-'\\(\\)]", "").split(" ").map(x => x.head.toUpper + x.tail).mkString
  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
