package services

import utils.parser.{ PolitaireParser, PolitaireScalaExporter }

import scala.util.Random

object Testbed {
  def go() = {
    val rulesSet = PolitaireParser.gameRules
    //val rules = rulesSet(Random.nextInt(rulesSet.size))
    //PolitaireScalaExporter.exportRules(rules)
    //PolitaireScalaExporter.exportRuleSet(rulesSet)
    PolitaireScalaExporter.export(rulesSet)
    "OK!"
  }
}
