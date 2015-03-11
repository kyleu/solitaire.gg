package models.game.variants

import models.game._
import models.game.pile.{Foundation, Tableau}

object FreeCell extends GameVariant.Description {
  override val id = "freecell"
  override val name = "FreeCell"
  override val body = "Move all the cards to the home cells, using the free cells as placeholders. To win, make four stacks of cards on the home cells, one for each suit, stacked in order of rank, from lowest (ace) to highest (king)."
}

case class FreeCell(override val id: String, override val seed: Int) extends GameVariant(id, seed) {
  override def description = FreeCell

  val piles = List(
    new Foundation("foundation-1"),
    new Foundation("foundation-2"),
    new Foundation("foundation-3"),
    new Foundation("foundation-4"),
    new Foundation("foundation-5"),
    new Foundation("foundation-6"),
    new Foundation("foundation-7"),
    new Foundation("foundation-8"),

    new Tableau("tableau-1"),
    new Tableau("tableau-2"),
    new Tableau("tableau-3"),
    new Tableau("tableau-4"),
    new Tableau("tableau-5"),
    new Tableau("tableau-6"),
    new Tableau("tableau-7"),
    new Tableau("tableau-8")
  )

  val deck = Deck.shuffled(rng)

  val layouts = Seq(
    Layout(
      width = 8.9,
      height = 5.0,
      piles = List(
        PileLocation("foundation-1", 0.1, 0.2),
        PileLocation("foundation-2", 1.2, 0.2),
        PileLocation("foundation-3", 2.3, 0.2),
        PileLocation("foundation-4", 3.4, 0.2),
        PileLocation("foundation-5", 4.5, 0.2),
        PileLocation("foundation-6", 5.6, 0.2),
        PileLocation("foundation-7", 6.7, 0.2),
        PileLocation("foundation-8", 7.8, 0.2),

        PileLocation("tableau-1", 0.1, 1.3),
        PileLocation("tableau-2", 1.2, 1.3),
        PileLocation("tableau-3", 2.3, 1.3),
        PileLocation("tableau-4", 3.4, 1.3),
        PileLocation("tableau-5", 4.5, 1.3),
        PileLocation("tableau-6", 5.6, 1.3),
        PileLocation("tableau-7", 6.7, 1.3),
        PileLocation("tableau-8", 7.8, 1.3)
      )
    )
  )

  lazy val gameState = GameState(id, description.id, seed, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(7, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-7", reveal = true)
    gameState.addCards(deck.getCards(6, turnFaceUp = true), "tableau-8", reveal = true)
  }
}
