package models.game

object Deck {
  def freshCards(ranks: Seq[Rank], suits: Seq[Suit]) = {
    val cards = for {
      suit <- suits
      rank <- ranks.reverse
    } yield Card(r = rank, s = suit, u = false)
    cards.toList
  }
}

case class Deck(var cards: Seq[Card], lowRank: Rank, highRank: Rank) {
  def getCards(numCards: Int = this.cards.size, turnFaceUp: Boolean = false, rank: Option[Rank] = None) = {
    val ret = rank match {
      case Some(r) => (0 until numCards).map { i =>
        this.cards.find(_.r == r).getOrElse(throw new IllegalStateException("Cannot find card with rank [" + r + "]."))
      }
      case None => this.cards.take(numCards)
    }
    if (turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    this.cards = this.cards.filterNot(card => ret.contains(card))
    ret
  }

  def getCardsUniqueRanks(ranks: Seq[Rank], numCards: Int, turnFaceUp: Boolean = false) = {
    var ret = Seq.empty[Card]
    var enough = false
    while (!enough) {
      val c = cards.headOption.getOrElse(throw new IllegalStateException())
      if (ret.exists(_.r == c.r) || ranks.contains(c.r)) {
        cards = cards.tail :+ c
      } else {
        cards = cards.tail
        ret = ret :+ c
      }
      enough = ret.length == numCards
    }
    if (turnFaceUp) {
      ret.foreach { c =>
        c.u = true
      }
    }
    ret
  }
}
