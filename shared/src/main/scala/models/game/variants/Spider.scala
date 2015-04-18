package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints.Constraints
import models.game.pile._

object Spider extends GameVariant.Description {
  override val key = "spider"
  override val name = "Spider"
  override val body = "..."
  override val layouts = Seq(
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
}

case class Spider(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Spider

  private[this] val drawPiles = (1 to 10).map("tableau-" + _).toSeq

  private[this] val stockOptions = new PileOptions(
    cardsShown = Some(1),
    selectCardConstraint = Some(Constraints.pilesNonEmpty((1 to 10).map(i => "tableau-" + i): _*)),
    selectCardAction = Some(SelectCardActions.drawToPiles(1, drawPiles, turn = Some(true)))
  )

  private[this] val tableauOptions = PileOptionsHelper.tableau.copy(
    dragToConstraint = Some(Constraints.lowerRank),
    dragFromConstraint = Some(Constraints.descendingSequenceSameSuit)
  )

  private[this] val piles = {
    List(new Pile("stock", "stock", stockOptions)) ++
      (1 to 8).map(i => Pile("foundation-" + i, "foundation", PileOptionsHelper.foundation)) ++
      (1 to 10).map(i => Pile("tableau-" + i, "tableau", tableauOptions))
  }

  private[this] val deck = newShuffledDecks(2)

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

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
