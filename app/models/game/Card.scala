package models.game

import java.util.UUID

case class Card(id: String = UUID.randomUUID.toString, r: Rank, s: Suit, var u: Boolean) {
  override def toString = {
    val faceUp = if(u) { "+" } else { "-" }
    "" + this.r.toChar + this.s.toChar + faceUp + ": " + id.substring(0, 8)
  }
}
