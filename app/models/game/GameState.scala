package models.game

case class GameState(
  id: String,
  seed: Int,
  deck: Deck,
  piles: Seq[Pile],
  layouts: Seq[Layout]
) {
  val cardsById = collection.mutable.HashMap[String, Card]()
  val pilesById = piles.map(p => p.id -> p).toMap
  val playerKnownCardIds = collection.mutable.HashMap.empty[String, collection.mutable.HashSet[String]]

  def addPlayer(p: String) = {
    playerKnownCardIds(p) = collection.mutable.HashSet.empty
  }

  def addCard(card: Card, pile: String, reveal: Boolean = false) {
    this.cardsById(card.id) = card
    this.pilesById(pile).addCard(card)
    if(reveal) {
      revealCard(card, None)
    }
  }

  def addCards(cards: Seq[Card], pile: String, reveal: Boolean = false) = cards.foreach { c =>
    addCard(c, pile, reveal)
  }

  def revealCard(card: Card, player: Option[String]) = player match {
    case Some(p) => playerKnownCardIds(p) += card.id
    case None => playerKnownCardIds.foreach(_._2 += card.id)
  }

  def view(session: String) = {
    val knownCards = playerKnownCardIds(session)
    this.copy(
      deck = deck.copy(cards = deck.cards.map( c => if(knownCards.contains(c.id)) { c } else { c.copy( r = Rank.Unknown, s = Suit.Unknown ) })),
      piles = piles.map( p => p.copy( cards = p.cards.map( c => if(knownCards.contains(c.id)) { c } else { c.copy( r = Rank.Unknown, s = Suit.Unknown ) })) )
    )
  }
}
