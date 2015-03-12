package models.game.variants

import models.game._
import models.game.pile.{Foundation, Stock, Tableau}

object TrustyTwelve extends GameVariant.Description {
  override val id = "trusty-twelve"
  override val name = "Trusty Twelve"
  override val body = "Build the decks down, regardless of suit. Clicking the face down pile will deal cards to any empty spaces. Once you've dealt all the face down cards, you win."
}

case class TrustyTwelve(override val id: String, override val seed: Int) extends GameVariant(id, seed) {
  override def description = TrustyTwelve

  val piles = List(
    new Stock("stock", cardsShown = Some(19), direction = Some("r")),

    new Tableau("tableau-1", cardsShown = Some(2)),
    new Tableau("tableau-2", cardsShown = Some(2)),
    new Tableau("tableau-3", cardsShown = Some(2)),
    new Tableau("tableau-4", cardsShown = Some(2)),
    new Tableau("tableau-5", cardsShown = Some(2)),
    new Tableau("tableau-6", cardsShown = Some(2)),

    new Tableau("tableau-7", cardsShown = Some(2)),
    new Tableau("tableau-8", cardsShown = Some(2)),
    new Tableau("tableau-9", cardsShown = Some(2)),
    new Tableau("tableau-10", cardsShown = Some(2)),
    new Tableau("tableau-11", cardsShown = Some(2)),
    new Tableau("tableau-12", cardsShown = Some(2))
  )

  val deck = Deck.shuffled(rng)

  val layouts = Seq(
    Layout(
      width = 6.7,
      height = 3.1,
      piles = List(
        PileLocation("stock", 0.1, 0.2),

        PileLocation("tableau-1", 0.1, 1.3),
        PileLocation("tableau-2", 1.2, 1.3),
        PileLocation("tableau-3", 2.3, 1.3),
        PileLocation("tableau-4", 3.4, 1.3),
        PileLocation("tableau-5", 4.5, 1.3),
        PileLocation("tableau-6", 5.6, 1.3),

        PileLocation("tableau-7", 0.1, 2.5),
        PileLocation("tableau-8", 1.2, 2.5),
        PileLocation("tableau-9", 2.3, 2.5),
        PileLocation("tableau-10", 3.4, 2.5),
        PileLocation("tableau-11", 4.5, 2.5),
        PileLocation("tableau-12", 5.6, 2.5)
      )
    )
  )

  lazy val gameState = GameState(id, description.id, seed, deck, piles, layouts)

  override def initialMoves() = {
    for(i <- 1 to 12) {
      gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
    gameState.addCards(deck.getCards(), "stock")
  }
}
