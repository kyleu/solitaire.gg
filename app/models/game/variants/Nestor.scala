package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile._
import models.game.pile.constraints.Constraints

object Nestor extends GameVariant.Description {
  override val key = "nestor"
  override val name = "Nestor"
  override val body = "Discard any pair of cards of the same rank, regardless of suit (for example, two Aces, two Fives, etc.). Only the top cards are available for play. Spaces can't be filled."
}

case class Nestor(override val gameId: UUID, override val seed: Int, players: Seq[GamePlayer]) extends GameVariant(gameId, seed) {
  override def description = Nestor

  private val options = Tableau.options.combine(
    selectCardConstraint = Some(Constraints.never),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.sameRank)
  )

  private val piles = List(
    new Tableau("tableau-1", options),
    new Tableau("tableau-2", options),
    new Tableau("tableau-3", options),
    new Tableau("tableau-4", options),
    new Tableau("tableau-5", options),
    new Tableau("tableau-6", options),
    new Tableau("tableau-7", options),
    new Tableau("tableau-8", options),

    new Foundation("reserve-1", options),
    new Foundation("reserve-2", options),
    new Foundation("reserve-3", options),
    new Foundation("reserve-4", options)
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = Seq(
    Layout(
      width = 8.9,
      height = 3.1,
      piles = List(
        PileLocation("tableau-1", 0.1, 0.2),
        PileLocation("tableau-2", 1.2, 0.2),
        PileLocation("tableau-3", 2.3, 0.2),
        PileLocation("tableau-4", 3.4, 0.2),
        PileLocation("tableau-5", 4.5, 0.2),
        PileLocation("tableau-6", 5.6, 0.2),
        PileLocation("tableau-7", 6.7, 0.2),
        PileLocation("tableau-8", 7.8, 0.2),

        PileLocation("reserve-1", 2.3, 2.0),
        PileLocation("reserve-2", 3.4, 2.0),
        PileLocation("reserve-3", 4.5, 2.0),
        PileLocation("reserve-4", 5.6, 2.0)
      )
    )
  )

  val gameState = GameState(gameId, description.key, seed, players, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-7", reveal = true)
    gameState.addCards(deck.getCardsUniqueRanks(6, turnFaceUp = true), "tableau-8", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "reserve-1", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "reserve-2", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "reserve-3", reveal = true)
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "reserve-4", reveal = true)
  }

  override def isWin: Boolean = !gameState.piles.exists(_.cards.size != 0)
}
