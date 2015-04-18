package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{ PileOptionsHelper, PileOptions, Pile }
import models.game.pile.constraints.Constraints

object Yukon extends GameVariant.Description {
  override val key = "yukon"
  override val name = "Yukon"
  override val body = """
    ...
  """
  override val layouts = Seq(
    Layout(
      width = 10.1,
      height = 5.0,
      piles = List(
        PileLocation("tableau-1", 1.1, 0.7),
        PileLocation("tableau-2", 2.2, 0.7),
        PileLocation("tableau-3", 3.3, 0.7),
        PileLocation("tableau-4", 4.4, 0.7),
        PileLocation("tableau-5", 5.5, 0.7),
        PileLocation("tableau-6", 6.6, 0.7),
        PileLocation("tableau-7", 7.7, 0.7),

        PileLocation("foundation-1", 9.0, 0.7),
        PileLocation("foundation-2", 9.0, 1.8),
        PileLocation("foundation-3", 9.0, 2.9),
        PileLocation("foundation-4", 9.0, 4.0)
      )
    )
  )
}

case class Yukon(override val gameId: UUID, override val seed: Int) extends GameVariant(gameId, seed) {
  override def description = Yukon

  private[this] val tableauOptions = PileOptionsHelper.tableau.combine(PileOptions(dragFromConstraint = Some(Constraints.faceUp)))

  private[this] val piles = List(
    Pile("tableau-1", "tableau", tableauOptions),
    Pile("tableau-2", "tableau", tableauOptions),
    Pile("tableau-3", "tableau", tableauOptions),
    Pile("tableau-4", "tableau", tableauOptions),
    Pile("tableau-5", "tableau", tableauOptions),
    Pile("tableau-6", "tableau", tableauOptions),
    Pile("tableau-7", "tableau", tableauOptions),

    Pile("foundation-1", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-2", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-3", "foundation", PileOptionsHelper.foundation),
    Pile("foundation-4", "foundation", PileOptionsHelper.foundation)
  )

  private[this] val deck = newShuffledDecks()

  override val gameState = GameState(gameId, description.key, description.maxPlayers, seed, deck, piles, description.layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)

    (2 to 7).foreach { i =>
      gameState.addCards(deck.getCards(i - 1), "tableau-" + i)
      gameState.addCards(deck.getCards(5, turnFaceUp = true), "tableau-" + i, reveal = true)
    }
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
