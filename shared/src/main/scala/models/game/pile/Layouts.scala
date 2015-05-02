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
  private[this] val layouts = Map(
    "accordion" -> "t",
    "bakersdozen" -> ":.f|t|.t",
    "canfield" -> "sw:f|:r:t",
    "congress" -> "sw|f|t",
    "flowergarden" -> ":f|t|w",
    "freecell" -> "f:c|.t|",
    "fourteenout" -> "tf",
    "fortyandeight" -> "sw|f|t",
    "golf" -> "t|f:s",
    "gypsy" -> "sf|:t",
    "klondike" -> "sw:f|t|",
    "klondike1card" -> "sw:f|t|",
    "missmilligan" -> "sf|:t",
    "nestor" -> "t|::rf",
    "pyramid" -> "p|::.swf",
    "sandbox" -> "s:f",
    "sandboxb" -> "s",
    "spider" -> "s:f|t",
    "trustytwelve" -> "s|t|t",
    "yukon" -> ":::f|t"
  )

  def forRules(v: String) = {
    layouts.getOrElse(v, "s")
  }
}
