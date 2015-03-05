package models.game

import scala.collection.mutable.ArrayBuffer

sealed case class Pile(id: String, behavior: String, cards: collection.mutable.ArrayBuffer[Card] = ArrayBuffer.empty[Card]) {
  def addCards(cs: Seq[Card]) = cs.foreach(addCard)

  def addCard(c: Card) {
    cards += c
  }

  def removeCard(c: Card) {
    cards.find(_.id == c.id) match {
      case Some(_) => cards -= c
      case None => throw new IllegalArgumentException("Provided card [" + c + "] is not included in pile [" + id + "].")
    }
  }

  def canDragFrom(cards: Seq[Card]) = false
  def canDragTo(cards: Seq[Card]) = false
  def canSelectCard(card: Card) = false
  def canSelectPile = false

  override def toString: String = id + ": " + cards.map(_.toString).mkString(", ")
}

class Stock(override val id: String) extends Pile(id, "stock", ArrayBuffer.empty[Card]) {
  override def canSelectCard(card: Card) = card == this.cards.last
  override def canSelectPile = this.cards.isEmpty
}

class Waste(override val id: String) extends Pile(id, "waste", ArrayBuffer.empty[Card]) {
  override def canDragFrom(cards: Seq[Card]) = cards.length == 1 && cards(0) == this.cards.last
}

class Foundation(override val id: String) extends Pile(id, "foundation", ArrayBuffer.empty[Card]) {
  override def canDragFrom(cards: Seq[Card]) = cards.length == 1 && cards(0) == this.cards.last
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

class Tableau(override val id: String) extends Pile(id, "tableau", ArrayBuffer.empty[Card]) {
  override def canDragFrom(cards: Seq[Card]) = {
    if(cards.exists(!_.u)) {
      false
    } else {
      var valid = true
      var lastCard: Option[Card] = None

      for(c <- cards) {
        if(lastCard.isDefined) {
          if(c.s.color == lastCard.get.s.color) {
            valid = false
          }
          if(c.r == Rank.Ace || c.r.value != (lastCard.get.r.value - 1)) {
            valid = false
          }
        }
        lastCard = Some(c)
      }
      valid
    }
  }

  override def canDragTo(cards: Seq[Card]) = {
    if(this.cards.isEmpty) {
      cards.head.r ==  Rank.King
    } else {
      val topCard = this.cards.last
      val firstDraggedCard = cards.head
      if(!topCard.u) {
        false
      } else if(topCard.s.color == firstDraggedCard.s.color) {
        false
      } else if(topCard.r == Rank.Ace || firstDraggedCard.r == Rank.King) {
        false
      } else {
        topCard.r.value == firstDraggedCard.r.value + 1
      }
    }
  }
}
