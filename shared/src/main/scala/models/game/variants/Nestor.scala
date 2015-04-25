package models.game.variants

import java.util.UUID

import models.game._

object Nestor extends GameVariant.Description {
  override val key = "nestor"
  override val name = "Nestor"
  override val body = """
    Discard any pair of cards of the same rank, regardless of suit (for example, two Aces, two Fives, etc.).
    Only the top cards are available for play. Spaces can't be filled.
  """

  def initialMoves(gameState: GameState, deck: Deck) = {
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
}

class Nestor(override val gameId: UUID, override val seed: Int) extends GameVariant("nestor", Nestor, gameId, seed, Nestor.initialMoves) {
//  private[this] val options = PileOptionsHelper.tableau.combine(PileOptions(
//    selectCardConstraint = Some(Constraints.never),
//    dragFromConstraint = Some(Constraints.topCardOnly),
//    dragToConstraint = Some(Constraints.sameRank),
//    dragToAction = Some(DragToActions.remove())
//  ))
}
