package models.game.rules

sealed trait FillEmptyWith {
  def toWords: String
}
object FillEmptyWith {
  def fromInt(i: Int) = i match {
    case 0 => Any
    case 1 => Kings
    case 2 => KingsUntilStockEmpty
    case 4 => None
    case 5 => None
    case 7 => Aces
    case 8 => Sevens
    case 9 => KingsOrAces
  }

  case object Any extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any card."
  }
  case object None extends FillEmptyWith {
    override def toWords = "An empty pile may not be filled."
  }
  case object Aces extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any Ace."
  }
  case object Kings extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any King."
  }
  case object KingsUntilStockEmpty extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any King until the stock is empty."
  }
  case object KingsOrAces extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any King or Ace."
  }
  case object Sevens extends FillEmptyWith {
    override def toWords = "An empty pile may be filled with any Seven."
  }
}
