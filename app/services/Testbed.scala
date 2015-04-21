package services

import utils.parser.ScalaExporter
import utils.parser.politaire.PolitaireParser

object Testbed {
  def go() = {
    val rulesSet = PolitaireParser.gameRules
    ScalaExporter.export(rulesSet)
    "OK!"
  }
}
