package models.card

case class Card(id: Int, r: Rank, s: Suit, var u: Boolean = false) {
  override def toString = s"$id:${this.r.value}${this.s.value}${ if (u) { "+" } else { "-" } }"
}
