package models.card

case class Card(id: Int, r: Rank, s: Suit, var u: Boolean = false) {
  override def toString = {
    val (startIndex, endIndex) = 0 -> 8
    val up = { if (u) { "+" } else { "-" } }
    s"$id:${this.r.toChar}${this.s.toChar}$up"
  }
}
