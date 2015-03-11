package models.game.variants

import models.game.GameVariant

object KlondikeDrawThree extends GameVariant.Description {
  override val id = "klondike"
  override val name = "Klondike"
  override val body = "Your OS came with this game. If you don't know how to play, use those help files."
}

case class KlondikeDrawThree(override val id: String, override val seed: Int) extends KlondikeBase(id, seed, 3) {
  override val description = KlondikeDrawThree
}
