package models.game.variants

import java.util.UUID

import models.game.GamePlayer

object KlondikeDrawOne extends GameVariant.Description {
  override val key = "klondike-draw-one"
  override val name = "Klondike (Draw 1)"
  override val body = "The standard Solitaire game, drawing one card at a time."
}

case class KlondikeDrawOne(override val gameId: UUID, override val seed: Int, players: Seq[GamePlayer]) extends KlondikeBase(gameId, seed, players, 1) {
  override val description = KlondikeDrawOne
}
