package models.game.pile.constraints

import models.game.{Rank, Card}
import models.game.pile.Pile

object DragToConstraints {
  def never(pile: Pile, cards: Seq[Card]) = false

  def foundationDefault(pile: Pile, cards: Seq[Card]) = {
    if(cards.length == 1) {
      if(pile.cards.isEmpty && cards(0).r == Rank.Ace) {
        true
      } else if(pile.cards.last.s == cards(0).s && pile.cards.last.r == Rank.Ace && cards(0).r == Rank.Two) {
        true
      } else if(pile.cards.last.s == cards(0).s && pile.cards.last.r.value + 1 == cards(0).r.value) {
        true
      } else {
        false
      }
    } else {
      false
    }
  }

  def klondike(pile: Pile, cards: Seq[Card]) = {
    if(pile.cards.isEmpty) {
      cards.head.r ==  Rank.King
    } else {
      val topCard = pile.cards.last
      val firstDraggedCard = cards.head
      if(!topCard.u) {
        false
      } else {
        if(topCard.s.color == firstDraggedCard.s.color) {
          false
        } else if(topCard.r == Rank.Ace || firstDraggedCard.r == Rank.King) {
          false
        } else {
          topCard.r.value == firstDraggedCard.r.value + 1
        }
      }
    }
  }

  def alternatingRank(pile: Pile, cards: Seq[Card]) = {
    val topCard = pile.cards.last
    val firstDraggedCard = cards.head
    topCard.r.value == firstDraggedCard.r.value + 1 || topCard.r.value == firstDraggedCard.r.value - 1
  }

  def apply(key: Option[String]) = key match {
    case Some(k) => k match {
      case "foundation" => foundationDefault _
      case "klondike" => klondike _
      case "alternating-rank" => alternatingRank _
      case _ => throw new IllegalArgumentException("Invalid select card constraint [" + k + "].")
    }
    case None => never _
  }
}
