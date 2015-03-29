package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.pile.{Stock, PileOptions, Foundation, Tableau}

object Spider extends GameVariant.Description {
  override val key = "spider"
  override val name = "Spider"
  override val body = "..."
  override val inProgress = true
}

case class Spider(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Spider

  private val drawPiles = Seq("tableau-1", "tableau-2", "tableau-3", "tableau-4", "tableau-5", "tableau-6", "tableau-7", "tableau-8", "tableau-9", "tableau-10")

  val stockOptions = new PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Constraints.notEmpty,
    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, true))
  )

  val tableauOptions = Tableau.options.copy(
    dragToConstraint = Constraints.lowerRank,
    dragFromConstraint = Constraints.descendingSequenceSameSuit
  )

  private val piles = List(
    new Stock("stock", stockOptions),

    new Foundation("foundation-1"),
    new Foundation("foundation-2"),
    new Foundation("foundation-3"),
    new Foundation("foundation-4"),
    new Foundation("foundation-5"),
    new Foundation("foundation-6"),
    new Foundation("foundation-7"),
    new Foundation("foundation-8"),

    new Tableau("tableau-1", tableauOptions),
    new Tableau("tableau-2", tableauOptions),
    new Tableau("tableau-3", tableauOptions),
    new Tableau("tableau-4", tableauOptions),
    new Tableau("tableau-5", tableauOptions),
    new Tableau("tableau-6", tableauOptions),
    new Tableau("tableau-7", tableauOptions),
    new Tableau("tableau-8", tableauOptions),
    new Tableau("tableau-9", tableauOptions),
    new Tableau("tableau-10", tableauOptions)
  )

  private val deck = Deck.shuffled(rng, 2)

  private val layouts = Seq(
    Layout(
      width = 11.1,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),

        PileLocation("foundation-1", 2.8, 0.7),
        PileLocation("foundation-2", 3.9, 0.7),
        PileLocation("foundation-3", 5.0, 0.7),
        PileLocation("foundation-4", 6.1, 0.7),
        PileLocation("foundation-5", 7.2, 0.7),
        PileLocation("foundation-6", 8.3, 0.7),
        PileLocation("foundation-7", 9.4, 0.7),
        PileLocation("foundation-8", 10.5, 0.7),

        PileLocation("tableau-1", 0.6, 1.8),
        PileLocation("tableau-2", 1.7, 1.8),
        PileLocation("tableau-3", 2.8, 1.8),
        PileLocation("tableau-4", 3.9, 1.8),
        PileLocation("tableau-5", 5.0, 1.8),
        PileLocation("tableau-6", 6.1, 1.8),
        PileLocation("tableau-7", 7.2, 1.8),
        PileLocation("tableau-8", 8.3, 1.8),
        PileLocation("tableau-9", 9.4, 1.8),
        PileLocation("tableau-10", 10.5, 1.8)
      )
    )
  )

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(5), "tableau-1")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(5), "tableau-2")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(5), "tableau-3")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(5), "tableau-4")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-5")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-6")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-7")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-7", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-8")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-8", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-9")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-9", reveal = true)
    gameState.addCards(deck.getCards(4), "tableau-10")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-10", reveal = true)

    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
