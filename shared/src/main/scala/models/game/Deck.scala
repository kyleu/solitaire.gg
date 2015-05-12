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
  def getCards(
    numCards: Int = this.cards.size,
    turnFaceUp: Boolean = false,
    rank: Option[Rank] = None,
    forbiddenRanks: Set[Rank] = Set.empty,
    suit: Option[Suit] = None,
    forbiddenSuits: Set[Suit] = Set.empty,
    color: Option[Color] = None,
    forbiddenColors: Set[Color] = Set.empty
  ) = {
    val ret = if (rank.isEmpty && forbiddenRanks.isEmpty && suit.isEmpty && forbiddenSuits.isEmpty && color.isEmpty && forbiddenColors.isEmpty) {
      this.cards.take(numCards)
    } else {
      var cards = Seq.empty[Card]
      (0 until numCards).map { i =>
        val c = this.cards.find { c =>
          (!cards.contains(c)) && (!forbiddenRanks.contains(c.r)) && (!forbiddenSuits.contains(c.s)) &&
            rank.map(r => c.r == r).getOrElse(true) && suit.map(s => c.s == s).getOrElse(true) && color.map(clr => c.s.color == clr).getOrElse(true)
        }.getOrElse(throw new IllegalStateException("Cannot find card matching options."))
        cards = c +: cards
        c
      }
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
    this.cards = this.cards.filterNot(card => ret.contains(card))
    ret
  }
}
