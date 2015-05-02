package models.game

sealed trait Rank {
  def value: Int
  def toChar: Char
}

object Rank {
  val all: Seq[Rank] = Seq(Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace)
  val allByChar = all.map(r => r.toChar -> r).toMap
  val allByValue = all.map(r => r.value -> r).toMap

  case object Two extends Rank {
    override def value = 2
    override def toChar = '2'
  }

  case object Three extends Rank {
    override def value = 3
    override def toChar = '3'
  }

  case object Four extends Rank {
    override def value = 4
    override def toChar = '4'
  }

  case object Five extends Rank {
    override def value = 5
    override def toChar = '5'
  }

  case object Six extends Rank {
    override def value = 6
    override def toChar = '6'
  }

  case object Seven extends Rank {
    override def value = 7
    override def toChar = '7'
  }

  case object Eight extends Rank {
    override def value = 8
    override def toChar = '8'
  }

  case object Nine extends Rank {
    override def value = 9
    override def toChar = '9'
  }

  case object Ten extends Rank {
    override def value = 10
    override def toChar = 'X'
  }

  case object Jack extends Rank {
    override def value = 11
    override def toChar = 'J'
  }

  case object Queen extends Rank {
    override def value = 12
    override def toChar = 'Q'
  }

  case object King extends Rank {
    override def value = 13
    override def toChar = 'K'
  }

  case object Ace extends Rank {
    override def value = 14
    override def toChar = 'A'
  }

  case object Unknown extends Rank {
    override def value = 0
    override def toChar = '?'
  }
}
