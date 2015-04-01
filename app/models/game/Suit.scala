package models.game

sealed trait Color

case object Red extends Color
case object Black extends Color
case object UnknownColor extends Color

sealed trait Suit extends Ordered[Suit] {
  val color: Color
  val toChar: Char
  val value: Int

  override def compare(that: Suit) = this.value * 13 - that.value * 13
}

object Suit {
  val all = Seq(Hearts, Spades, Diamonds, Clubs)

  def fromChar(c: Char) = c match {
    case 'H' => Hearts
    case 'S' => Spades
    case 'D' => Diamonds
    case 'C' => Clubs
    case '?' => Unknown
  }

  case object Hearts extends Suit {
    override val color = Red
    override val toChar = 'H'
    override val value = 3
  }

  case object Spades extends Suit {
    override val color = Black
    override val toChar = 'S'
    override val value = 2
  }

  case object Diamonds extends Suit {
    override val color = Red
    override val toChar = 'D'
    override val value = 1
  }

  case object Clubs extends Suit {
    override val color = Black
    override val toChar = 'C'
    override val value = 0
  }

  case object Unknown extends Suit {
    override val color = UnknownColor
    override val toChar = '?'
    override val value = -1
  }
}

