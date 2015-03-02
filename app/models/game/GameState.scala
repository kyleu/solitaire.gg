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
  val playerKnownCardIds = Map.empty[String, collection.mutable.HashSet[String]]

  def addCard(card: Card, pile: String) {
    this.cardsById(card.id) = card
    this.pilesById(pile).addCard(card)
  }

  def addCards(cards: Seq[Card], pile: String) = cards.foreach(addCard(_, pile))
}
