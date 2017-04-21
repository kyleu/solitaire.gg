package models.card

case class Card(idx: Int, r: Rank, s: Suit, var u: Boolean = false) {
  override def toString = {
    val up = { if (u) { "+" } else { "-" } }
    s"$idx:${this.r.toChar}${this.s.toChar}$up"
  }
}
