package models.game.pile

import models.game.Card

class Waste(override val id: String) extends Pile(id, "waste") {
  override def canDragFrom(cards: Seq[Card]) = cards.length == 1 && Some(cards(0)) == this.cards.lastOption
}
