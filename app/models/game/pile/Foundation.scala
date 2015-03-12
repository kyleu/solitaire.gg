package models.game.pile

import models.game.{Card, Rank}

class Foundation(
  id: String,
  cardsShown: Option[Int] = Some(1),
  direction: Option[String] = None,
  options: Map[String, String] = Map.empty
) extends Pile(id, "foundation", cardsShown = cardsShown, direction = direction, options = options) {
  override def canDragFrom(cards: Seq[Card]) = options.getOrElse("drag-from", "top") == "top" && cards.length == 1 && Some(cards(0)) == this.cards.lastOption
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
