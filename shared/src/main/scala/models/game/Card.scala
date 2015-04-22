package models.game

import java.util.UUID

case class Card(id: UUID = UUID.randomUUID, r: Rank, s: Suit, var u: Boolean) {
  override def toString = {
    val (startIndex, endIndex) = 0 -> 8
    "" + this.r.toChar + this.s.toChar + { if (u) { "+" } else { "-" } } + ": " + id.toString.substring(startIndex, endIndex)
  }
}
