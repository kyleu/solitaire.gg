package models.game.pile

import models.game.rules.{ GameRules, GameRulesSet }

/**
 * Layout key:
 *   s - Stock
 *   w - Waste
 *   f - Foundation
 *   c - Cell
 *   r - Reserve
 *   p - Pyramid
 *
 *   : - Full-size spacer
 *   . - Half-size spacer
 *
 *   | - New line
 */
object Layouts {
  def defaultLayout(id: String) = {
    var ret = ""
    val rules = GameRulesSet.allById.getOrElse(id, GameRules.default)
    if (rules.stock.isDefined) { ret += "s" }
    if (rules.waste.isDefined) { ret += "w" }
    for (f <- rules.foundations) { ret += "f" }
    if (rules.reserves.isDefined) { ret += "|r" }
    if (rules.cells.isDefined) { ret += "|c" }
    if (rules.tableaus.nonEmpty) { ret += "|" }
    for (t <- rules.tableaus) { ret += "t" }
    if (rules.pyramids.nonEmpty) { ret += "|" }
    for (p <- rules.pyramids) { ret += "p" }
    ret
  }
}
