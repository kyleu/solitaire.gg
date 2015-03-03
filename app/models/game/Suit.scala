package models.game

sealed trait Color

case object Red extends Color
case object Black extends Color
case object UnknownColor extends Color

sealed trait Suit {
  def color: Color
  def toChar: Char
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
    override def color = Red
    override def toChar = 'H'
  }

  case object Spades extends Suit {
    override def color = Black
    override def toChar = 'S'
  }

  case object Diamonds extends Suit {
    override def color = Red
    override def toChar = 'D'
  }

  case object Clubs extends Suit {
    override def color = Black
    override def toChar = 'C'
  }

  case object Unknown extends Suit {
    override def color = UnknownColor
    override def toChar = '?'
  }
}


