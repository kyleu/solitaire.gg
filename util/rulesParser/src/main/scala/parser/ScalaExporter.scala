package parser

import java.nio.file.{ Files, Path, Paths }

import models.game.rules._
import parser.politaire.PolitaireParser
import parser.politaire.lookup.PolitaireLookup

object ScalaExporter {
  def export(rulesSet: Seq[GameRules]) = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "rules", "generated")
    writeFile(srcDir.resolve("GeneratedGameRules.scala"), ScalaRuleSetExporter.exportRulesSet(rulesSet))

    val src = PolitaireParser.politaireList

    for(rules <- rulesSet) {
      val v = src.find(_.id == rules.id).getOrElse(throw new IllegalArgumentException)
      writeFile(srcDir.resolve(getObjectName(rules) + ".scala"), exportRules(rules, v))
    }
  }

  def exportRules(rules: GameRules, variant: PolitaireParser.Variant) = {
    val ret = new StringBuilder()
    def add(s: String) = ret ++= s + "\n"

    val defaults = GameRules("default", "default", "default")

    add("// Generated rules for Scalataire.")
    add("package models.game.rules.generated")
    add("")
    add("import models.game._")
    add("import models.game.rules._")
    add("")
    add("/**")
    add(" * Original Settings:")
    for(attr <- variant.attributes.toList.sortBy(_._1)) {
      if (!attr._2.defaultVal && attr._2.id != "title" && attr._2.id != "desc") {
        val translation = attr._2.value match {
          case i: Int => PolitaireLookup.getTranslation(attr._2.id).flatMap(_.get(i))
          case _ => None
        }
        add(" *   " + attr._2.title + " (" + attr._2.id + "): " + attr._2.value + translation.map(" (" + _ + ")").getOrElse(""))
      }
    }
    add(" */")
    add("object " + getObjectName(rules) + " extends GameRules(")
    add("  id = \"" + rules.id + "\",")
    add("  title = \"" + rules.title + "\",")
    rules.like.foreach { l =>
      add("  like = Some(\"" + l + "\"),")
    }
    if (rules.related.nonEmpty) {
      val rel = rules.related.grouped(8)
      if (rel.size == 1 && rel.nonEmpty) {
        add("  related = Seq(" + rel.map("\"" + _ + "\"").mkString(", ") + "),")
      } else if (rel.size > 1) {
        add("  related = Seq(")
        for(relSet <- rel.zipWithIndex) {
          add(relSet._1.map("\"" + _ + "\"").mkString(", ") + (if(relSet._2 < ret.size) { "," } else { "" }))
        }
        add("  ),")
      }
    }
    val desc = rules.description.replaceAllLiterally("\"", "\\\"").grouped(130)
    add("  description = \"" + desc.mkString("\" +\n  \"") + "\",")
    if (rules.victoryCondition != defaults.victoryCondition) {
      add("  victoryCondition = VictoryCondition." + cls(rules.victoryCondition) + ",")
    }
    if (rules.cardRemovalMethod != defaults.cardRemovalMethod) {
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
    ScalaSpecialExporter.exportSpecial(rules, ret)
    add("  complete = " + rules.complete)
    add(")")
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
