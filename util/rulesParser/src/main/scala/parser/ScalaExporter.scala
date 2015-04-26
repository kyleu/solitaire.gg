package parser

import java.nio.file.{ Files, Path, Paths }

import models.game.rules._
import org.joda.time.LocalDateTime

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
    add("import models.game.rules.custom.CustomRulesSet")
    add("")
    add("// scalastyle:off")
    add("object GameRulesSet {")
    add("  val all = Seq(")
    add(rulesSet.map(r => "    " + getObjectName(r)).mkString(",\n"))
    add("  ) ++ CustomRulesSet.all")
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

    val defaults = GameRules("default", "default", "default")

    add("// Generated " + new LocalDateTime().toString("yyyy-MM-dd") + " for Scalataire.")
    add("package models.game.generated")
    add("")
    add("import models.game._")
    add("import models.game.rules._")
    add("")
    add("// scalastyle:off")
    add("object " + getObjectName(rules) + " extends GameRules(")
    add("  id = \"" + rules.id + "\",")
    add("  title = \"" + rules.title + "\",")
    add("  description = \"" + rules.description.replaceAllLiterally("\"", "\\\"") + "\",")
    if(rules.victoryCondition != defaults.victoryCondition) {
      add("  victoryCondition = VictoryCondition." + cls(rules.victoryCondition) + ",")
    }
    if(rules.cardRemovalMethod != defaults.cardRemovalMethod) {
      add("  cardRemovalMethod = CardRemovalMethod." + cls(rules.cardRemovalMethod) + ",")
    }
    ScalaDeckOptionsExporter.exportDeckOptions(rules, ret)
    ScalaStockExporter.exportStock(rules, ret)
    ScalaWasteExporter.exportWaste(rules, ret)
    ScalaFoundationExporter.exportFoundations(rules, ret)
    ScalaTableauExporter.exportTableaus(rules, ret)
    ScalaCellExporter.exportCells(rules, ret)
    ScalaReserveExporter.exportReserves(rules, ret)
    ScalaPyramidExporter.exportPyramids(rules, ret)
    add("  complete = " + rules.complete)
    add(")")
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
