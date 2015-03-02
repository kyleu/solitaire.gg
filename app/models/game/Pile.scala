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
  override def canDragFrom(cards: Seq[Card]): Boolean = cards.length == 1 && cards(0) == this.cards.last
}

class Tableau(override val id: String) extends Pile(id, "tableau", ArrayBuffer.empty[Card]) {
  override def canDragFrom(cards: Seq[Card]): Boolean = true
  override def canDragTo(cards: Seq[Card]): Boolean = true
}

class Foundation(override val id: String) extends Pile(id, "foundation", ArrayBuffer.empty[Card]) {
  override def canDragFrom(cards: Seq[Card]): Boolean = cards.length == 1 && cards(0) == this.cards.last
  override def canDragTo(cards: Seq[Card]): Boolean = if(cards.length == 1) {
    this.cards.isEmpty && cards(0).r == Ace || (this.cards.last.s == cards(0).s && this.cards.last.r.value + 1 == cards(0).r.value)
  } else {
    false
  }
}
