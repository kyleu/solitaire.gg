package models.game.pile

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
    "trustytwelve" -> "s|t|t",
    "yukon" -> ":::f|t"
  )

  def forRules(v: String) = {
    layouts.getOrElse(v, "s")
  }
}
