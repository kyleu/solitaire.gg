package services.wiki.template

import models.rules.GameRules

object WikiLayout {
  def layout(rules: GameRules): Seq[(String, Seq[String])] = {
    var foundationsProcessed = 0
    var tableausProcessed = 0
    var pyramidsProcessed = 0

    rules.layout.flatMap {
      case 's' => rules.stock.map(WikiStock.stock)
      case 'w' => rules.waste.map(WikiWaste.waste)
      case 'r' => rules.reserves.map(WikiReserve.reserve)
      case 'c' => rules.cells.map(WikiCell.cell)
      case 'f' =>
        val fr = rules.foundations(foundationsProcessed)
        foundationsProcessed += 1
        if (fr.visible) {
          Some(WikiFoundation.foundation(fr, rules.deckOptions))
        } else {
          None
        }
      case 't' =>
        val tr = rules.tableaus(tableausProcessed)
        tableausProcessed += 1
        Some(WikiTableau.tableau(tr, rules.deckOptions))
      case 'p' =>
        val pr = rules.pyramids(pyramidsProcessed)
        pyramidsProcessed += 1
        Some(WikiPyramid.pyramid(pr, rules.deckOptions))
      case x if x == '2' || x == '3' || x == '4' || x == '5' || x == '6' || x == '7' || x == '8' =>
        None
      case x if x == '|' || x == ':' || x == '.' =>
        None
    }
  }
}
