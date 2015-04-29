package models.game.pile

/**
 * Layout key:
 *   s - Stock
 *   w - Waste
 *   f - Foundation
 *   c - Cell
 *   r - reserve
 *
 *   : - Full-size spacer
 *   . - Half-size spacer
 *
 *   | - New line
 */
object Layouts {
  private[this] val layouts = Map(
    "canfield" -> "sw:f|:r:t",
    "freecell" -> "f:c|.t|",
    "golf" -> "t|f:s",
    "gypsy" -> "sf|:t",
    "klondike" -> "sw:f|t|",
    "nestor" -> "t|::r",
    "pyramid" -> "p|::.sw|f",
    "sandbox" -> "s:f",
    "sandboxb" -> "s",
    "spider" -> "s:f|t",
    "trustytwelve" -> "s|t",
    "yukon" -> ":::f|t"
  )

  def forRules(v: String) = {
    layouts.getOrElse(v, "s")
  }
}
