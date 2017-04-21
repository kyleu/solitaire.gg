package models.card

import enumeratum.values.{IntEnum, IntEnumEntry, IntUPickleEnum}

sealed trait Rank extends IntEnumEntry {
  def value: Int
  def toChar: Char
  override def toString = toChar.toString
  lazy val previous = Rank.allByValue(value - 1)
  lazy val next = Rank.allByValue(value + 1)
}

object Rank extends IntEnum[Rank] with IntUPickleEnum[Rank] {
  val all: Seq[Rank] = Seq(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val allByChar = all.map(r => r.toChar -> r).toMap
  val allByValue = all.map(r => r.value -> r).toMap + (1 -> Ace)

  case object Two extends Rank {
    override val value = 2
    override val toChar = '2'
    override lazy val previous = Ace
  }

  case object Three extends Rank {
    override val value = 3
    override val toChar = '3'
  }

  case object Four extends Rank {
    override val value = 4
    override val toChar = '4'
  }

  case object Five extends Rank {
    override val value = 5
    override val toChar = '5'
  }

  case object Six extends Rank {
    override val value = 6
    override val toChar = '6'
  }

  case object Seven extends Rank {
    override val value = 7
    override val toChar = '7'
  }

  case object Eight extends Rank {
    override val value = 8
    override val toChar = '8'
  }

  case object Nine extends Rank {
    override val value = 9
    override val toChar = '9'
  }

  case object Ten extends Rank {
    override val value = 10
    override val toChar = 'X'
  }

  case object Jack extends Rank {
    override val value = 11
    override val toChar = 'J'
  }

  case object Queen extends Rank {
    override val value = 12
    override val toChar = 'Q'
  }

  case object King extends Rank {
    override val value = 13
    override val toChar = 'K'
  }

  case object Ace extends Rank {
    override val value = 14
    override val toChar = 'A'
    override lazy val next = Two
  }

  case object Unknown extends Rank {
    override val value = 0
    override val toChar = '?'
    override lazy val previous = Unknown
    override lazy val next = Unknown
  }

  override val values = findValues
}
