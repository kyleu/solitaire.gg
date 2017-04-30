package models.card

case class Card(id: Int, r: Rank, s: Suit, var u: Boolean = false) {
  override def toString = s"$id:${this.r.toChar}${this.s.toChar}${ if (u) { "+" } else { "-" } }"
}
