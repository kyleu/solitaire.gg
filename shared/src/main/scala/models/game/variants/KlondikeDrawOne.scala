package models.game.variants

import java.util.UUID

object KlondikeDrawOne extends GameVariant.Description {
  override val key = "klondike-draw-one"
  override val name = "Klondike (Draw 1)"
  override val body = "The standard Solitaire game, drawing one card at a time."
  override val layouts = KlondikeBase.layouts
}

case class KlondikeDrawOne(override val gameId: UUID, override val seed: Int) extends KlondikeBase(gameId, seed, 1) {
  override val description = KlondikeDrawOne
}
