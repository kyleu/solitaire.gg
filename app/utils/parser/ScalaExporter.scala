package utils.parser

import java.nio.file.{ Files, Path, Paths }

import models.game.rules._

object ScalaExporter {
  def export(rulesSet: Seq[GameRules]) = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "generated")
    writeFile(srcDir.resolve("GameRulesSet.scala"), exportRulesSet(rulesSet))

    for (rules <- rulesSet) {
      writeFile(srcDir.resolve(getObjectName(rules) + ".scala"), exportRules(rules))
    }
  }

  def exportRulesSet(rulesSet: Seq[GameRules]) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("package models.game.generated")
    add("")
    add("// scalastyle:off")
    add("object GameRulesSet {")
    add("  val all = Seq(")
    //add(ruleset.groupBy(_.title.head).toList.sortBy(_._1).map(l => "    " + l._2.map( r => getObjectName(r)).mkString(", ")).mkString("\n"))
    add(rulesSet.map(r => "    " + getObjectName(r)).mkString(",\n"))
    add("  )")
    add("")
    add("  val allById = all.map(x => x.id -> x).toMap")
    add("}")
    add("// scalastyle:on")
    add("")

    ret.toString()
  }

  def exportRules(rules: GameRules) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    add("""package models.game.generated""")
    add("")
    add("""import models.game._""")
    add("""import models.game.rules._""")
    add("""""")
    add("// scalastyle:off")
    add(s"""object ${getObjectName(rules)} extends GameRules(""")
    add(s"""  id = \"${rules.id}\",""")
    add(s"""  title = \"${rules.title}\",""")
    add(s"""  description = \"${rules.description.replaceAllLiterally("\"", "\\\"")}\",""")
    add(s"""  victoryCondition = VictoryCondition.${cls(rules.victoryCondition)},""")
    add(s"""  cardRemovalMethod = CardRemovalMethod.${cls(rules.cardRemovalMethod)},""")
    add("""  deckOptions = DeckOptions(""")
    add(s"""    numDecks = ${rules.deckOptions.numDecks},""")
    add(s"""    suits = Seq(${rules.deckOptions.suits.map(x => "Suit." + x).mkString(", ")}),""")
    add(s"""    ranks = Seq(${rules.deckOptions.ranks.map(x => "Rank." + x).mkString(", ")}),""")
    add(s"""    lowRank = ${rules.deckOptions.lowRank.map("Rank." + _)}""")
    add("""  ),""")
    ScalaStockExporter.exportStock(rules, ret)
    ScalaWasteExporter.exportWaste(rules, ret)
    ScalaFoundationExporter.exportFoundations(rules, ret)
    ScalaTableauExporter.exportTableaus(rules, ret)
    ScalaCellExporter.exportCells(rules, ret)
    ScalaReserveExporter.exportReserves(rules, ret)
    ScalaPyramidExporter.exportPyramids(rules, ret)
    add(""")""")
    add("// scalastyle:on")
    add("")

    ret.toString()
  }

  def cls(o: Any) = o match {
    case count: InitialCards.Count => "Count(" + count.n + ")"
    case count: TableauFaceDownCards.Count => "Count(" + count.n + ")"
    case count: PyramidFaceDownCards.Count => "Count(" + count.n + ")"
    case specificRank: FoundationLowRank.SpecificRank => "SpecificRank(Rank." + specificRank.r + ")"
    case cn => cn.getClass.getSimpleName.replaceAllLiterally("$", "")
  }
  private def getObjectName(rules: GameRules) = rules.title.replaceAll("[-'\\(\\)]", "").split(" ").map(x => x.head.toUpper + x.tail).mkString
  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
