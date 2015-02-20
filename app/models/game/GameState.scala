package models.game

object GameState {
  def default(deck: Deck, piles: List[Pile]) = {
    val layout = Layout.default

    deck.dealCardsTo(piles.find(_.id == "tableau-1").get, 1)
    deck.dealCardsTo(piles.find(_.id == "tableau-2").get, 2)
    deck.dealCardsTo(piles.find(_.id == "tableau-3").get, 3)
    deck.dealCardsTo(piles.find(_.id == "tableau-4").get, 4)
    deck.dealCardsTo(piles.find(_.id == "tableau-5").get, 5)
    deck.dealCardsTo(piles.find(_.id == "tableau-6").get, 6)
    deck.dealCardsTo(piles.find(_.id == "tableau-7").get, 7)

    GameState("scalataire", 0, piles, layout)
  }
}

case class GameState(
  id: String,
  seed: Int,
  piles: List[Pile],
  layout: Layout
)
