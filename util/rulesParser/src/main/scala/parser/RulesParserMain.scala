package parser

import models.rules.GameRulesSet
import parser.politaire.PolitaireParser

object RulesParserMain extends App {
  val task = args match {
    case x if x.isEmpty => "export"
    case _ => args(0)
  }
  val startMs = System.currentTimeMillis
  println(s"Performing task [$task]...")
  task match {
    case "parse" => PolitaireParser.politaireList
    case "import" => PolitaireParser.gameRules
    case "view" => GameRulesSet.all
    case "export" => ScalaExporter.export(PolitaireParser.gameRules)
  }
  println(s"Task [$task] complete in [${System.currentTimeMillis - startMs}ms].")
}
