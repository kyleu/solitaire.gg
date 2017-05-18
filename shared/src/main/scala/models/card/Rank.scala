package models.card

import enumeratum.values._

sealed abstract class Rank(val value: Char) extends CharEnumEntry {
  def index: Int
  def name: String
  lazy val previous = Rank.allByValue(index - 1)
  lazy val next = Rank.allByValue(index + 1)
  val locs: Seq[(Double, Double)] = Nil
}

object Rank extends CharEnum[Rank] with CharCirceEnum[Rank] {
  case object Two extends Rank('2') {
    override val index = 2
    override val name = "Two"
    override lazy val previous = Ace
    override val locs = Seq((0.5, 0.3), (0.5, 0.7))
  }

  case object Three extends Rank('3') {
    override val index = 3
    override val name = "Three"
    override val locs = Seq((0.2, 0.7), (0.5, 0.5), (0.8, 0.3))
  }

  case object Four extends Rank('4') {
    override val index = 4
    override val name = "Four"
    override val locs = Seq((0.2, 0.5), (0.5, 0.3), (0.5, 0.7), (0.8, 0.5))
  }

  case object Five extends Rank('5') {
    override val index = 5
    override val name = "Five"
    override val locs = Seq((0.2, 0.3), (0.8, 0.3), (0.5, 0.5), (0.2, 0.7), (0.8, 0.7))
  }

  case object Six extends Rank('6') {
    override val index = 6
    override val name = "Six"
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.5, 0.3), (0.5, 0.7), (0.8, 0.4), (0.8, 0.6))
  }

  case object Seven extends Rank('7') {
    override val index = 7
    override val name = "Seven"
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.5, 0.3), (0.5, 0.5), (0.5, 0.7), (0.8, 0.4), (0.8, 0.6))
  }

  case object Eight extends Rank('8') {
    override val index = 8
    override val name = "Eight"
    override val locs = Seq((0.2, 0.3), (0.2, 0.5), (0.2, 0.7), (0.5, 0.4), (0.5, 0.6), (0.8, 0.3), (0.8, 0.5), (0.8, 0.7))
  }

  case object Nine extends Rank('9') {
    override val index = 9
    override val name = "Nine"
    override val locs = Seq((0.2, 0.4), (0.2, 0.6), (0.2, 0.8), (0.5, 0.3), (0.5, 0.5), (0.5, 0.7), (0.8, 0.2), (0.8, 0.4), (0.8, 0.6))
  }

  case object Ten extends Rank('X') {
    override val index = 10
    override val name = "Ten"
    override val locs = Seq((0.2, 0.3), (0.2, 0.5), (0.2, 0.7), (0.5, 0.2), (0.5, 0.4), (0.5, 0.6), (0.5, 0.8), (0.8, 0.3), (0.8, 0.5), (0.8, 0.7))
  }

  case object Jack extends Rank('J') {
    override val index = 11
    override val name = "Jack"
  }

  case object Queen extends Rank('Q') {
    override val index = 12
    override val name = "Queen"
  }

  case object King extends Rank('K') {
    override val index = 13
    override val name = "King"
  }

  case object Ace extends Rank('A') {
    override val index = 14
    override val name = "Ace"
    override lazy val next = Two
  }

  case object Unknown extends Rank('?') {
    override val index = 0
    override val name = "Unknown"
    override lazy val previous = Unknown
    override lazy val next = Unknown
  }

  override val values = findValues
  val all = values.filterNot(_ == Unknown)
  val allByValue = all.map(r => r.index -> r).toMap + (1 -> Ace)
}
