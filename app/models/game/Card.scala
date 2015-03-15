package models.game

import java.util.UUID

case class Card(id: UUID = UUID.randomUUID, r: Rank, s: Suit, var u: Boolean) extends Ordered[Card] {
  override def compare(that: Card) = {
    this.s.compare(that.s) + this.r.compare(that.r)
  }

  def compareAceLow(that: Card) = {
    this.s.compare(that.s) + this.r.compareAceLow(that.r)
  }

  override def toString = {
    val faceUp = if(u) { "+" } else { "-" }
    "" + this.r.toChar + this.s.toChar + faceUp + ": " + id.toString.substring(0, 8)
  }
}
