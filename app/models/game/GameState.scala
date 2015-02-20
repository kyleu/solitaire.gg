package models.game

object GameState {
  def klondike(deck: Deck) = {
    val piles = Pile.klondike
    val layout = Layout.klondike

    deck.dealCardsTo(piles.find(_.id == "tableau-1").get, 1)
    deck.dealCardsTo(piles.find(_.id == "tableau-2").get, 2)
    deck.dealCardsTo(piles.find(_.id == "tableau-3").get, 3)
    deck.dealCardsTo(piles.find(_.id == "tableau-4").get, 4)
    deck.dealCardsTo(piles.find(_.id == "tableau-5").get, 5)
    deck.dealCardsTo(piles.find(_.id == "tableau-6").get, 6)
    deck.dealCardsTo(piles.find(_.id == "tableau-7").get, 7)

    GameState("klondike", 0, piles, layout)
  }

  def sandbox(deck: Deck) = {
    val piles = Pile.sandbox
    val layout = Layout.sandbox

    deck.dealCardsTo(piles.find(_.id == "sandbox-1").get)

    GameState("sandbox", 0, piles, layout)
  }
}

case class GameState(
  id: String,
  seed: Int,
  piles: List[Pile],
  layout: Layout
)
