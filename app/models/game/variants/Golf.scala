package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{ PileOptions, PileOptionsHelper, Pile }
import models.game.pile.actions.SelectCardActions
import models.game.pile.constraints._

object Golf extends GameVariant.Description {
  override val key = "golf"
  override val name = "Golf"
  override val body = """
    Build the bottom pile up or down regardless of suit. Ranking of cards is not continuous: an Ace may be built only on a 2, a King only on a Queen.
  """
  override val layouts = Seq(
    Layout(
      width = 7.8,
      height = 3.2,
      piles = List(
        PileLocation("tableau-1", 0.6, 0.7),
        PileLocation("tableau-2", 1.7, 0.7),
        PileLocation("tableau-3", 2.8, 0.7),
        PileLocation("tableau-4", 3.9, 0.7),
        PileLocation("tableau-5", 5.0, 0.7),
        PileLocation("tableau-6", 6.1, 0.7),
        PileLocation("tableau-7", 7.2, 0.7),
        PileLocation("foundation", 0.6, 2.5),
        PileLocation("stock", 2.7, 2.5)
      )
    )
  )
}

case class Golf(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Golf

  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(
    selectCardConstraint = Some(Constraints.alternatingRankToFoundation),
    dragFromConstraint = Some(Constraints.topCardOnly),
    selectCardAction = Some(SelectCardActions.drawToPile(1, "foundation"))
  ))

  private[this] val piles = List(
    Pile("tableau-1", "tableau", tableauOptions),
    Pile("tableau-2", "tableau", tableauOptions),
    Pile("tableau-3", "tableau", tableauOptions),
    Pile("tableau-4", "tableau", tableauOptions),
    Pile("tableau-5", "tableau", tableauOptions),
    Pile("tableau-6", "tableau", tableauOptions),
    Pile("tableau-7", "tableau", tableauOptions),

    Pile("foundation", "foundation", PileOptionsHelper.foundation.combine(PileOptions(
      cardsShown = Some(4), direction = Some("r"), dragToConstraint = Some(Constraints.alternatingRank)
    ))),
    Pile("stock", "stock", PileOptionsHelper.stock(1, "foundation", None).combine(PileOptions(
      cardsShown = Some(16),
      direction = Some("r"),
      selectPileConstraint = Some(Constraints.never)
    )))
  )

  private[this] val deck = newShuffledDecks()

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-1", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-2", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-3", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-4", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-5", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-6", reveal = true)
    gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-7", reveal = true)

    gameState.addCards(deck.getCards(1, turnFaceUp = true), "foundation", reveal = true)
    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.pilesById("foundation").cards.length == 52
}
