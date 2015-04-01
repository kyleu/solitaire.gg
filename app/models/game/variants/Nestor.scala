package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile._
import models.game.pile.actions.DragToActions
import models.game.pile.constraints.Constraints

object Nestor extends GameVariant.Description {
  override val key = "nestor"
  override val name = "Nestor"
  override val body = "Discard any pair of cards of the same rank, regardless of suit (for example, two Aces, two Fives, etc.). Only the top cards are available for play. Spaces can't be filled."
}

case class Nestor(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Nestor

  private val options = PileOptionsHelper.tableau.combine(
    selectCardConstraint = Some(Constraints.never),
    dragFromConstraint = Some(Constraints.topCardOnly),
    dragToConstraint = Some(Constraints.sameRank),
    dragToAction = Some(DragToActions.remove)
  )

  private val piles = List(
    new Pile("tableau-1", "tableau", options),
    new Pile("tableau-2", "tableau", options),
    new Pile("tableau-3", "tableau", options),
    new Pile("tableau-4", "tableau", options),
    new Pile("tableau-5", "tableau", options),
    new Pile("tableau-6", "tableau", options),
    new Pile("tableau-7", "tableau", options),
    new Pile("tableau-8", "tableau", options),

    new Pile("reserve-1", "reserve", options),
    new Pile("reserve-2", "reserve", options),
    new Pile("reserve-3", "reserve", options),
    new Pile("reserve-4", "reserve", options)
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = Seq(
    Layout(
      width = 8.9,
      height = 3.1,
      piles = List(
        PileLocation("tableau-1", 0.6, 0.7),
        PileLocation("tableau-2", 1.7, 0.7),
        PileLocation("tableau-3", 2.8, 0.7),
        PileLocation("tableau-4", 3.9, 0.7),
        PileLocation("tableau-5", 5.0, 0.7),
        PileLocation("tableau-6", 6.1, 0.7),
        PileLocation("tableau-7", 7.2, 0.7),
        PileLocation("tableau-8", 8.3, 0.7),

        PileLocation("reserve-1", 2.8, 2.5),
        PileLocation("reserve-2", 3.9, 2.5),
        PileLocation("reserve-3", 5.0, 2.5),
        PileLocation("reserve-4", 6.1, 2.5)
      )
    )
  )

  val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, layouts)

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

  override def isWin: Boolean = !gameState.piles.exists(_.cards.nonEmpty)
}
