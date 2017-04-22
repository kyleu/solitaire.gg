package models.card

import enumeratum._

sealed trait Suit extends EnumEntry with Ordered[Suit] {
  def color: Color
  def toChar: Char
  def index: Int

  override def compare(that: Suit) = -(that.index * 13 - this.index * 13)
  override def toString = toChar.toString
}

object Suit extends Enum[Suit] {
  val standard: Seq[Suit] = Seq(Hearts, Spades, Diamonds, Clubs)

  def fromChar(c: Char) = c match {
    case 'H' => Hearts
    case 'S' => Spades
    case 'D' => Diamonds
    case 'B' => Diamonds
    case 'C' => Clubs
    case 'G' => Clubs
    case 'O' => Horseshoes
    case 'R' => Stars
    case 'T' => Tridents
    case 'M' => Moons
    case '?' => Unknown
  }

  case object Hearts extends Suit {
    override val color = Red
    override val toChar = 'H'
    override val index = 0
  }
  case object Spades extends Suit {
    override val color = Black
    override val toChar = 'S'
    override val index = 1
  }
  case object Diamonds extends Suit {
    override val color = Red
    override val toChar = 'D'
    override val index = 2
  }
  case object Clubs extends Suit {
    override val color = Black
    override val toChar = 'C'
    override val index = 3
  }

  case object Horseshoes extends Suit {
    override val color = Black
    override val toChar = 'O'
    override val index = 4
  }
  case object Stars extends Suit {
    override val color = Red
    override val toChar = 'R'
    override val index = 5
  }
  case object Tridents extends Suit {
    override val color = Green
    override val toChar = 'T'
    override val index = 6
  }
  case object Moons extends Suit {
    override val color = Blue
    override val toChar = 'M'
    override val index = 7
  }

  case object Suitless extends Suit {
    override val color = Colorless
    override val toChar = 'X'
    override val index = 8
  }

  case object Unknown extends Suit {
    override val color = UnknownColor
    override val toChar = '?'
    override val index = -1
  }

  override val values = findValues
}
