package models.game.pile

import models.game.rules.GameRulesSet

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
    "alternative" -> ":.f:f|t|t",
    "bakersdozen" -> ":.f|t|.t",
    "canfield" -> "sw:f|:r:t",
    "congress" -> "sw|f|t",
    "cruel" -> "::f|t|t",
    "doubleklondike" -> "swf|:t",
    "flowergarden" -> ":f|t|w",
    "freecell" -> "f:c|.t|",
    "fourteenout" -> "t|tf",
    "fortyandeight" -> "sw|f|t",
    "golf" -> "t|f:s",
    "gypsy" -> "sf|:t",
    "klondike" -> "swf|t",
    "klondike1card" -> "swf|t",
    "missmilligan" -> "sf|:t",
    "nestor" -> "t|::rf",
    "penguin" -> ":.f|c|t",
    "pyramid" -> "p|::.swf",
    "sandbox" -> "s:f",
    "sandboxb" -> "s",
    "spider" -> "s:f|t",
    "tournament" -> "sff|:c|::t",
    "tripleklondike" -> "swf|:t",
    "trustytwelve" -> "s|t|t",
    "yukon" -> ":::f|t"
  ) ++ Seq(
    "aceofhearts"
  ).map( id => id -> defaultLayout(id))

  def forRules(id: String) = {
    layouts.getOrElse(id, defaultLayout(id))
  }

  private[this] def defaultLayout(id: String) = {
    var ret = ""
    val rules = GameRulesSet.allById(id)

    if(rules.stock.isDefined) {
      ret += "s"
    }
    if(rules.waste.isDefined) {
      ret += "w"
    }
    for(f <- rules.foundations) {
      ret += "f"
    }
    if(rules.reserves.isDefined) {
      ret += "|r"
    }
    if(rules.cells.isDefined) {
      ret += "|c"
    }
    if(rules.tableaus.nonEmpty) {
      ret += "|"
    }
    for(t <- rules.tableaus) {
      ret += "t"
    }
    if(rules.pyramids.nonEmpty) {
      ret += "|"
    }
    for(p <- rules.pyramids) {
      ret += "p"
    }

    ret
  }
}
