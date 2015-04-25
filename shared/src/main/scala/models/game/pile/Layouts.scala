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
 */
object Layouts {
  private [this] val layouts = Map(
    "canfield" -> "sw:f,:r:t",
    "freecell" -> "f:c,.t,",
    "golf" -> "t,f:s",
    "klondike" -> "sw:f,t,",
    "nestor" -> "t,..r"
  )

  def forVariant(v: String) = {
    layouts.getOrElse(v, throw new NotImplementedError())
  }
}
