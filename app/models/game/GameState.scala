package models.game

object GameState {
  def klondike(deck: Deck) = {
    val piles = Pile.klondike
    val layout = Layout.klondike

    deck.dealCardsTo(piles.find(_.id == "tableau-1").get, turnFaceUp = true, 1)
    deck.dealCardsTo(piles.find(_.id == "tableau-2").get, turnFaceUp = true, 2)
    deck.dealCardsTo(piles.find(_.id == "tableau-3").get, turnFaceUp = true, 3)
    deck.dealCardsTo(piles.find(_.id == "tableau-4").get, turnFaceUp = true, 4)
    deck.dealCardsTo(piles.find(_.id == "tableau-5").get, turnFaceUp = true, 5)
    deck.dealCardsTo(piles.find(_.id == "tableau-6").get, turnFaceUp = true, 6)
    deck.dealCardsTo(piles.find(_.id == "tableau-7").get, turnFaceUp = true, 7)

    deck.dealCardsTo(piles.find(_.id == "stock").get, turnFaceUp = false)

    GameState("klondike", 0, piles, layout)
  }

  def sandbox(deck: Deck) = {
    val piles = Pile.sandbox
    val layout = Layout.sandbox

    val p = piles.find(_.id == "sandbox-1").get
    deck.dealCardsTo(p, turnFaceUp = false)

    GameState("sandbox", 0, piles, layout)
  }
}

case class GameState(
  id: String,
  seed: Int,
  piles: List[Pile],
  layout: Layout
) {
  val playerKnownCardIds = Map.empty[String, collection.mutable.HashSet[String]]
}
