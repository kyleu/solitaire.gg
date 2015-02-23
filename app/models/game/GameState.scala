package models.game

object GameState {
  def klondike(deck: Deck) = {
    val piles = Pile.klondike
    val layout = Layout.klondike
    val ret = GameState("klondike", 0, deck, piles, layout)

    ret.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1")
    ret.addCards(deck.getCards(2, turnFaceUp = true), "tableau-2")
    ret.addCards(deck.getCards(3, turnFaceUp = true), "tableau-3")
    ret.addCards(deck.getCards(4, turnFaceUp = true), "tableau-4")
    ret.addCards(deck.getCards(5, turnFaceUp = true), "tableau-5")
    ret.addCards(deck.getCards(6, turnFaceUp = true), "tableau-6")
    ret.addCards(deck.getCards(7, turnFaceUp = true), "tableau-7")

    ret.addCards(deck.getCards(), "stock")

    ret
  }

  def sandbox(deck: Deck) = {
    val piles = Pile.sandbox
    val layout = Layout.sandbox
    val ret = GameState("sandbox", 0, deck, piles, layout)

    ret.addCards(deck.getCards(), "sandbox-1")

    ret
  }
}

case class GameState(
  id: String,
  seed: Int,
  deck: Deck,
  piles: List[Pile],
  layout: Layout
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
