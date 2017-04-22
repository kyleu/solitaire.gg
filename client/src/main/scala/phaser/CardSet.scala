package phaser

case object CardSet {
  val default = CardSet(cardWidth = 400, cardHeight = 600, cardHorizontalOffset = 80, cardVerticalOffset = 120)
}

case class CardSet(cardWidth: Int, cardHeight: Int, cardHorizontalOffset: Int, cardVerticalOffset: Int)
