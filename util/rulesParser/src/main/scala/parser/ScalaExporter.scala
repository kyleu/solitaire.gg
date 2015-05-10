package parser

import java.nio.file.{ Files, Path, Paths }

import models.game.rules._
import models.game.rules.custom.CustomGameRules
import parser.politaire.PolitaireParser
import parser.politaire.lookup.PolitaireLookup

object ScalaExporter {
  def export(rulesSet: Seq[GameRules]) = {
    val srcDir = Paths.get(".", "shared", "src", "main", "scala", "models", "game", "rules", "generated")
    writeFile(srcDir.resolve("GeneratedGameRules.scala"), ScalaRuleSetExporter.exportRulesSet(rulesSet))

    val src = PolitaireParser.politaireList

    for(rules <- rulesSet) {
      val v = src.find(_.id == rules.id).getOrElse(throw new IllegalArgumentException)
      val modifiedRules = rules.copy(related = rules.related ++ CustomGameRules.all.filter(_.like == Some(rules.id)).map(_.id))
      val exported = exportRules(modifiedRules, v)
      writeFile(srcDir.resolve(getObjectName(rules) + ".scala"), exported)
    }
  }

  def exportRules(rules: GameRules, variant: PolitaireParser.Variant) = {
    val ret = new StringBuilder()
    def add(s: String): Unit = ret ++= s + "\n"

    add("// Generated rules for Solitaire.gg.")
    add("package models.game.rules.generated")
    add("")
    add("import models.game._")
    add("import models.game.rules._")
    add("")
    add("/**")
    add(" * Original Settings:")
    for(attr <- variant.attributes.toList.sortBy(_._1)) {
      if (!attr._2.defaultVal && attr._2.id != "title" && attr._2.id != "desc") {
        if(attr._2.value.toString.nonEmpty) {
          val translation = attr._2.value match {
            case i: Int => PolitaireLookup.getTranslation(attr._2.id).flatMap(_.get(i))
            case _ => None
          }
          val value = if(attr._2.value.toString.length > 80) { attr._2.value.toString.substring(0, 80) + "..." } else { attr._2.value.toString }
          add(" *   " + attr._2.title + " (" + attr._2.id + "): " + value + translation.map(" (" + _ + ")").getOrElse(""))
        }
      }
    }
    add(" */")
    add("object " + getObjectName(rules) + " extends GameRules(")
    add("  id = \"" + rules.id + "\",")
    add("  title = \"" + rules.title + "\",")
    rules.like.foreach { l =>
      add("  like = Some(\"" + l + "\"),")
    }
    ScalaLinkExporter.relatedGames(rules, add)
    ScalaLinkExporter.webLinks(rules.id, add)
    val desc = rules.description.replaceAllLiterally("\"", "\\\"").grouped(130)
    add("  description = \"" + desc.mkString("\" +\n    \"") + "\",")
    if (rules.victoryCondition != GameRules.default.victoryCondition) {
      add("  victoryCondition = VictoryCondition." + cls(rules.victoryCondition) + ",")
    }
    if (rules.cardRemovalMethod != GameRules.default.cardRemovalMethod) {
      add("  cardRemovalMethod = CardRemovalMethod." + cls(rules.cardRemovalMethod) + ",")
    }
    val opts = Seq(
      ScalaDeckOptionsExporter.exportDeckOptions(rules),
      ScalaStockExporter.exportStock(rules),
      ScalaWasteExporter.exportWaste(rules),
      ScalaFoundationExporter.exportFoundations(rules),
      ScalaTableauExporter.exportTableaus(rules),
      ScalaCellExporter.exportCells(rules),
      ScalaReserveExporter.exportReserves(rules),
      ScalaPyramidExporter.exportPyramids(rules),
      ScalaSpecialExporter.exportSpecial(rules)
    ).flatten
    add(opts.mkString(",\n"))
    add(")")
    ret.toString()
  }

  def cls(o: Any) = o match {
    case InitialCards.Count(i) => "Count(" + i + ")"
    case TableauFaceDownCards.Count(i) => "Count(" + i + ")"
    case PyramidFaceDownCards.Count(i) => "Count(" + i + ")"
    case specificRank: FoundationLowRank.SpecificRank => "SpecificRank(Rank." + specificRank.r + ")"
    case cn => cn.getClass.getSimpleName.replaceAllLiterally("$", "")
  }
  private def getObjectName(rules: GameRules) = rules.title.replaceAll("[-'\\(\\)]", "").split(" ").map(x => x.head.toUpper + x.tail).mkString
  private def writeFile(p: Path, s: String) = Files.write(p, s.getBytes)
}
