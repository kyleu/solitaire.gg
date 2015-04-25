package models.game.pile

/**
 * Layout key:
 *   s: Stock
 *   w: Waste
 *   f: Foundation
 *   c: Cell
 *
 *   +: Full-size spacer
 */
object Layouts {
  private [this] val layouts = Map(
    "klondike" -> Seq("sw+f", "t"),
    "freecell" -> Seq("fc", "t")
  )

  def forVariant(v: String) = {
    layouts.getOrElse(v, throw new NotImplementedError())
  }
}
