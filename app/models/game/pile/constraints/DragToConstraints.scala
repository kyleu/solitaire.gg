package models.game.pile.constraints

import models.game.Rank._
import models.game.{Rank, Card}
import models.game.pile.{PileOption, Pile}

case class DragToConstraint(override val id: String, f: (Pile, Seq[Card]) => Boolean) extends PileOption(id)

object DragToConstraints {
  val never = DragToConstraint("never", (pile, cards) => false)

  val empty = DragToConstraint("empty", (pile, cards) => pile.cards.isEmpty)

  val foundationDefault = DragToConstraint("foundation", (pile, cards) => if(cards.length == 1) {
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
  })

  val klondike = DragToConstraint("klondike", (pile, cards) => if(pile.cards.isEmpty) {
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
  })

  val sameRank = DragToConstraint("same-rank", (pile, cards) => {
    val topCardRank = pile.cards.last.r
    val firstDraggedCardRank = cards.head.r
    topCardRank == firstDraggedCardRank
  })

  val lowerRank = DragToConstraint("lower-rank", (pile, cards) => {
    val firstDraggedCardRank = cards.head.r
    pile.cards.lastOption match {
      case Some(c) => c.r match {
        case Two => firstDraggedCardRank == Ace
        case _ => c.r.value == firstDraggedCardRank.value + 1
      }
      case None =>  false
    }
  })

  val alternatingRank = DragToConstraint("alternating-rank", (pile, cards) => {
    val topCardRank = pile.cards.last.r
    val firstDraggedCardRank = cards.head.r
    topCardRank match {
      case King => firstDraggedCardRank == Queen
      case Ace => firstDraggedCardRank == Two
      case Two => firstDraggedCardRank == Ace || firstDraggedCardRank == Three
      case _ => topCardRank.value == firstDraggedCardRank.value + 1 || topCardRank.value == firstDraggedCardRank.value - 1
    }
  })
}
