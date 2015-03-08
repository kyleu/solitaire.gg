package models.game.logic

import models.game._
import models.game.pile.Pile

object DragConstraints {
  sealed trait DragConstraint {
    def check(cards: List[Card], src: Pile, tgt: Pile): Boolean
  }

  trait SingleCardDragConstraint extends DragConstraint {
    def checkSingleCard(c: Card, src: Pile, tgt: Pile): Boolean
    def check(cards: List[Card], src: Pile, tgt: Pile) = {
      if(cards.size == 1) { checkSingleCard(cards.head, src, tgt) } else { false }
    }
  }

  trait EmptyDeckConstraint extends DragConstraint {
    def checkEmptyDeck(cards: List[Card], src: Pile, tgt: Pile): Boolean
    def check(cards: List[Card], src: Pile, tgt: Pile) = {
      if(cards.size == 1) { checkEmptyDeck(cards, src, tgt) } else { false }
    }
  }

  object DragTrue extends DragConstraint {
    override def check(cards: List[Card], src: Pile, tgt: Pile) = true
  }

  object DragFalse extends DragConstraint {
    override def check(cards: List[Card], src: Pile, tgt: Pile) = false
  }

  object SameSuit extends SingleCardDragConstraint {
    override def checkSingleCard(c: Card, src: Pile, tgt: Pile) = tgt.cards.lastOption match {
      case Some(tgtCard) => c.s == tgtCard.s
      case None => false
    }
  }

  object DifferentSuit extends SingleCardDragConstraint {
    override def checkSingleCard(c: Card, src: Pile, tgt: Pile) = tgt.cards.lastOption match {
      case Some(tgtCard) => c.s != tgtCard.s
      case None => false
    }
  }

  class SpecificRank(r: Rank) extends DragConstraint {
    override def check(cards: List[Card], src: Pile, tgt: Pile) = tgt.cards.lastOption match {
      case Some(tgtCard) => tgtCard.r == r
      case None => false
    }
  }

  class SpecificSuit(s: Suit) extends DragConstraint {
    override def check(cards: List[Card], src: Pile, tgt: Pile) = tgt.cards.lastOption match {
      case Some(tgtCard) => tgtCard.s == s
      case None => false
    }
  }

  class SpecificColor(color: Color) extends DragConstraint {
    override def check(cards: List[Card], src: Pile, tgt: Pile) = tgt.cards.lastOption match {
      case Some(tgtCard) => tgtCard.s.color == color
      case None => false
    }
  }
}
