package models.game.variants

import java.util.UUID

import models.game._
import models.game.pile.{Waste, Tableau, Stock, Foundation}

abstract class KlondikeBase(override val gameId: UUID, override val seed: Int, players: Seq[GamePlayer], cardsToDraw: Int) extends GameVariant(gameId, seed) {
  private val piles = List(
    new Stock("stock", Stock.options(cardsToDraw, "waste", Some("waste"))),
    new Waste("waste"),

    new Foundation("foundation-1"),
    new Foundation("foundation-2"),
    new Foundation("foundation-3"),
    new Foundation("foundation-4"),

    new Tableau("tableau-1"),
    new Tableau("tableau-2"),
    new Tableau("tableau-3"),
    new Tableau("tableau-4"),
    new Tableau("tableau-5"),
    new Tableau("tableau-6"),
    new Tableau("tableau-7")
  )

  private val deck = Deck.shuffled(rng)

  private val layouts = List(
    Layout(
      width = 7.8,
      height = 5.0,
      piles = List(
        PileLocation("stock", 0.6, 0.7),
        PileLocation("waste", 1.7, 0.7),

        PileLocation("foundation-1", 3.9, 0.7),
        PileLocation("foundation-2", 5.0, 0.7),
        PileLocation("foundation-3", 6.1, 0.7),
        PileLocation("foundation-4", 7.2, 0.7),

        PileLocation("tableau-1", 0.6, 1.8),
        PileLocation("tableau-2", 1.7, 1.8),
        PileLocation("tableau-3", 2.8, 1.8),
        PileLocation("tableau-4", 3.9, 1.8),
        PileLocation("tableau-5", 5.0, 1.8),
        PileLocation("tableau-6", 6.1, 1.8),
        PileLocation("tableau-7", 7.2, 1.8)
      )
    )
  )

  lazy val gameState = GameState(gameId, description.key, description.maxPlayers, seed, players, deck, piles, layouts)

  override def initialMoves() = {
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-1", reveal = true)

    gameState.addCards(deck.getCards(1), "tableau-2")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-2", reveal = true)

    gameState.addCards(deck.getCards(2), "tableau-3")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-3", reveal = true)

    gameState.addCards(deck.getCards(3), "tableau-4")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-4", reveal = true)

    gameState.addCards(deck.getCards(4), "tableau-5")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-5", reveal = true)

    gameState.addCards(deck.getCards(5), "tableau-6")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-6", reveal = true)

    gameState.addCards(deck.getCards(6), "tableau-7")
    gameState.addCards(deck.getCards(1, turnFaceUp = true), "tableau-7", reveal = true)

    gameState.addCards(deck.getCards(), "stock")
  }

  override def isWin: Boolean = gameState.piles.count(x => x.behavior == "foundation" && x.cards.size == 13) == 4
}
