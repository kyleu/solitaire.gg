package models.game.pile

/**
 * Layout key:
 *   s - Stock
 *   w - Waste
 *   f - Foundation
 *   c - Cell
 *
 *   : - Full-size spacer
 *   . - Half-size spacer
 */
object Layouts {
  private [this] val layouts = Map(
    "canfield" -> Seq("sw:f", ":r:t"),
    "freecell" -> Seq("f:c", ".t"),
    "klondike" -> Seq("sw:f", "t")
  )

  def forVariant(v: String) = {
    layouts.getOrElse(v, throw new NotImplementedError())
  }
}
