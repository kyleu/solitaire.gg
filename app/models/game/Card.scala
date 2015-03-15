package models.game

import java.util.UUID

case class Card(id: UUID = UUID.randomUUID, r: Rank, s: Suit, var u: Boolean) {
  override def toString = {
    val faceUp = if(u) { "+" } else { "-" }
    "" + this.r.toChar + this.s.toChar + faceUp + ": " + id.toString.substring(0, 8)
  }
}
