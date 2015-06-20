package models.game

import java.util.UUID

case class Card(id: UUID = UUID.randomUUID, r: Rank, s: Suit, var u: Boolean = false) {
  override def toString = {
    val (startIndex, endIndex) = 0 -> 8
    val up = { if (u) { "+" } else { "-" } }
    s"${this.r.toChar}${this.s.toChar}$up: ${id.toString.substring(startIndex, endIndex)}"
  }
}
