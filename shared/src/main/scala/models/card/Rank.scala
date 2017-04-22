package models.card

import enumeratum._

sealed trait Rank extends EnumEntry {
  def value: Int
  def name: String
  def toChar: Char
  lazy val previous = Rank.allByValue(value - 1)
  lazy val next = Rank.allByValue(value + 1)
  val locs: Seq[(Double, Double)] = Nil
}

object Rank extends Enum[Rank] {
  val all: Seq[Rank] = Seq(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val allByChar = all.map(r => r.toChar -> r).toMap
  val allByValue = all.map(r => r.value -> r).toMap + (1 -> Ace)

  case object Two extends Rank {
    override val value = 2
    override val name = "Two"
    override val toChar = '2'
    override lazy val previous = Ace
    override val locs = Seq((0.5, 0.3), (0.5, 0.7))
  }

  case object Three extends Rank {
    override val value = 3
    override val name = "Three"
    override val toChar = '3'
    override val locs = Seq((0.2, 0.7), (0.5, 0.5), (0.8, 0.3))
  }

  case object Four extends Rank {
    override val value = 4
    override val name = "Four"
    override val toChar = '4'
    override val locs = Seq((0.2, 0.5), (0.5, 0.3), (0.5, 0.7), (0.8, 0.5))
  }

  case object Five extends Rank {
    override val value = 5
    override val name = "Five"
    override val toChar = '5'
    override val locs = Seq((0.2, 0.3), (0.8, 0.3), (0.5, 0.5), (0.2, 0.7), (0.8, 0.7))
  }

  case object Six extends Rank {
    override val value = 6
    override val name = "Six"
    override val toChar = '6'
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.5, 0.3), (0.5, 0.7), (0.8, 0.4), (0.8, 0.6))
  }

  case object Seven extends Rank {
    override val value = 7
    override val name = "Seven"
    override val toChar = '7'
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.5, 0.3), (0.5, 0.5), (0.5, 0.7), (0.8, 0.4), (0.8, 0.6))
  }

  case object Eight extends Rank {
    override val value = 8
    override val name = "Eight"
    override val toChar = '8'
    override val locs = Seq((0.2, 0.3), (0.2, 0.5), (0.2, 0.7), (0.5, 0.4), (0.5, 0.6), (0.8, 0.3), (0.8, 0.5), (0.8, 0.7))
  }

  case object Nine extends Rank {
    override val value = 9
    override val name = "Nine"
    override val toChar = '9'
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.2, 0.8), (0.5, 0.3), (0.5, 0.5), (0.5, 0.7), (0.8, 0.2), (0.8, 0.4), (0.8, 0.6))
  }

  case object Ten extends Rank {
    override val value = 10
    override val name = "Ten"
    override val toChar = 'X'
    override val locs = Seq((0.2, 0.3), (0.2, 0.5), (0.2, 0.7), (0.5, 0.2), (0.5, 0.4), (0.5, 0.6), (0.5, 0.8), (0.8, 0.3), (0.8, 0.5), (0.8, 0.7))
  }

  case object Jack extends Rank {
    override val value = 11
    override val name = "Jack"
    override val toChar = 'J'
  }

  case object Queen extends Rank {
    override val value = 12
    override val name = "Queen"
    override val toChar = 'Q'
  }

  case object King extends Rank {
    override val value = 13
    override val name = "King"
    override val toChar = 'K'
  }

  case object Ace extends Rank {
    override val value = 14
    override val name = "Ace"
    override val toChar = 'A'
    override lazy val next = Two
  }

  case object Unknown extends Rank {
    override val value = 0
    override val name = "Unknown"
    override val toChar = '?'
    override lazy val previous = Unknown
    override lazy val next = Unknown
  }

  override val values = findValues
}
