package models.card

import enumeratum.values._

sealed abstract class Suit(val value: Char) extends CharEnumEntry with Ordered[Suit] {
  def color: Color
  def index: Int

  override def compare(that: Suit) = -(that.index * 13 - this.index * 13)
  override def toString = value.toString
}

object Suit extends CharEnum[Suit] with CharCirceEnum[Suit] {
  val standard: Seq[Suit] = Seq(Hearts, Spades, Diamonds, Clubs)
  val all = Seq(Suit.Hearts, Suit.Spades, Suit.Diamonds, Suit.Clubs, Suit.Horseshoes, Suit.Stars, Suit.Tridents, Suit.Moons)

  case object Hearts extends Suit('H') {
    override val color = Color.Red
    override val index = 0
  }
  case object Spades extends Suit('S') {
    override val color = Color.Black
    override val index = 1
  }
  case object Diamonds extends Suit('D') {
    override val color = Color.Red
    override val index = 2
  }
  case object Clubs extends Suit('C') {
    override val color = Color.Black
    override val index = 3
  }

  case object Horseshoes extends Suit('O') {
    override val color = Color.Black
    override val index = 4
  }
  case object Stars extends Suit('R') {
    override val color = Color.Red
    override val index = 5
  }
  case object Tridents extends Suit('T') {
    override val color = Color.Green
    override val index = 6
  }
  case object Moons extends Suit('M') {
    override val color = Color.Blue
    override val index = 7
  }

  case object Suitless extends Suit('X') {
    override val color = Color.Colorless
    override val index = 8
  }

  case object Unknown extends Suit('?') {
    override val color = Color.UnknownColor
    override val index = -1
  }

  override val values = findValues
}
