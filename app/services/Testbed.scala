package services

import utils.parser.ScalaExporter
import utils.parser.politaire.PolitaireParser

import scala.util.Random

object Testbed {
  def go() = {
    val rulesSet = PolitaireParser.gameRules
    //val rules = rulesSet(Random.nextInt(rulesSet.size))
    //PolitaireScalaExporter.exportRules(rules)
    //PolitaireScalaExporter.exportRuleSet(rulesSet)
    ScalaExporter.export(rulesSet)
    "OK!"
  }
}
