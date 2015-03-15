package models.game.variants

import java.util.UUID

import models.game.GameVariant

object KlondikeDrawThree extends GameVariant.Description {
  override val key = "klondike"
  override val name = "Klondike"
  override val body = "Your OS came with this game. If you don't know how to play, use those help files."
}

case class KlondikeDrawThree(override val gameId: UUID, override val seed: Int) extends KlondikeBase(gameId, seed, 3) {
  override val description = KlondikeDrawThree
}
