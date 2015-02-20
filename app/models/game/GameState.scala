package models.game

object GameState {
  def default(deck: Deck, piles: List[Pile]) = {
    val layout = Layout.default

    val topCard = deck.cards.head
    deck.cards = deck.cards.tail

    piles.head.cards = topCard :: piles.head.cards

    GameState("scalataire", 0, piles, layout)
  }
}

case class GameState(
  id: String,
  seed: Int,
  piles: List[Pile],
  layout: Layout
)
