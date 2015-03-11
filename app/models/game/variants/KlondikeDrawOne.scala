package models.game.variants

import models.game.GameVariant

object KlondikeDrawOne extends GameVariant.Description {
  override val id = "klondike-draw-one"
  override val name = "Klondike (Draw 1)"
  override val body = "The standard Solitaire game, drawing one card at a time."
}

case class KlondikeDrawOne(override val id: String, override val seed: Int) extends KlondikeBase(id, seed, 1) {
  override val description = KlondikeDrawOne
}
