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
  val layouts = Map(
    "accordion" -> "wf",
    "acesandkings" -> "swff|::r:t",
    "alexanderthegreat" -> "f::f|2t",
    "alternative" -> ":.f:f|2t",
    "bakersdozen" -> ":.f|t|.t",
    "beehive" -> "2f|.t|::rsw",
    "canfield" -> "swf|:r:t",
    "congress" -> "sw|f|t",
    "cruel" -> "::f|2t",
    "diavolo" -> "sw|f.f.f|t",
    "doubleklondike" -> "swf|:t",
    "flowergarden" -> ":f|t|w",
    "freecell" -> "f:c|.t|",
    "fourteenout" -> "2tf",
    "fortyandeight" -> "sw|f|t",
    "golf" -> "t|f:s",
    "goodmeasure" -> ".f|2t",
    "gypsy" -> "sf|:t",
    "kiev" -> "s::f|t",
    "missmilligan" -> "sf|:t",
    "nestor" -> "t|::rf",
    "penguin" -> ":.f|c|t",
    "pyramid" -> "p|::.swf",
    "sandbox" -> "s:f",
    "sandboxb" -> "s",
    "spider" -> "s:f|t",
    "tournament" -> "sff|:c|::t",
    "tripleklondike" -> "swf|:t",
    "trustytwelve" -> "s|2t",
    "yukon" -> ":::f|t"
  ) ++ Seq(
      "aceofhearts"
    ).map(id => id -> defaultLayout(id))

  def forRules(id: String) = {
    layouts.getOrElse(id, defaultLayout(id))
  }

  private[this] def defaultLayout(id: String) = {
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
