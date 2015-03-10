package services.game.variants

import services.game.GameVariant

object KlondikeDrawThree extends GameVariant.Description {
  override val id = "klondike"
  override val name = "Klondike"
  override val body = "..."
}

case class KlondikeDrawThree(override val id: String, override val seed: Int) extends KlondikeBase(id, seed, 3) {
  override val description = KlondikeDrawThree
}
