package models.game.pile

import models.game.{Card, Rank}

class Foundation(override val id: String) extends Pile(id, "foundation") {
  override def canDragFrom(cards: Seq[Card]) = cards.length == 1 && Some(cards(0)) == this.cards.lastOption
  override def canDragTo(cards: Seq[Card]) = if(cards.length == 1) {
    if(this.cards.isEmpty && cards(0).r == Rank.Ace) {
      true
    } else if(this.cards.last.s == cards(0).s && this.cards.last.r == Rank.Ace && cards(0).r == Rank.Two) {
      true
    } else if(this.cards.last.s == cards(0).s && this.cards.last.r.value + 1 == cards(0).r.value) {
      true
    } else {
      false
    }
  } else {
    false
  }
}
