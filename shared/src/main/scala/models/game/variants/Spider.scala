package models.game.variants

import java.util.UUID

import models.game._

object Spider extends GameVariant.Description {
  override val key = "spider"
  override val name = "Spider"
  override val body = "..."

  def initialMoves(gameState: GameState, deck: Deck) = {
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
}

class Spider(override val gameId: UUID, override val seed: Int) extends GameVariant("spider", Spider, gameId, seed, Spider.initialMoves) {
  //  private[this] val drawPiles = (1 to 10).map("tableau-" + _).toSeq
  //
  //  private[this] val stockOptions = new PileOptions(
  //    cardsShown = Some(1),
  //    selectCardConstraint = Some(Constraints.pilesNonEmpty((1 to 10).map(i => "tableau-" + i): _*)),
  //    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
  //  )
  //
  //  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
  //    dragToConstraint = Some(Constraints.lowerRank),
  //    dragFromConstraint = Some(Constraints.descendingSequenceSameSuit)
  //  )
  //
  //  private[this] val piles = {
  //    List(new Pile("stock", "stock", stockOptions)) ++
  //      (1 to 8).map(i => Pile("foundation-" + i, "foundation", PileOptionsHelper.foundation)) ++
  //      (1 to 10).map(i => Pile("tableau-" + i, "tableau", tableauOptions))
  //  }
  //
  //  private[this] val deck = newShuffledDecks(2)
}
