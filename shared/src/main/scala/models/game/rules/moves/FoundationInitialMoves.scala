package models.game.rules.moves

import models.game.{ Rank, GameState }
import models.game.rules.{ FoundationLowRank, GameRules }

object FoundationInitialMoves {
  def performInitialMoves(rules: GameRules, gameState: GameState) = {
    rules.foundations.foreach { fr =>
      val prefix = if (fr.setNumber == 0) {
        "foundation-"
      } else {
        "foundation" + (fr.setNumber + 1) + "-"
      }
      val randomRank = gameState.deck.cards.headOption match {
        case Some(c) => c.r
        case None => throw new IllegalStateException()
      }
      (0 until fr.initialCards).foreach { i =>
        val col = (i % fr.numPiles) + 1
        val card = fr.lowRank match {
          case FoundationLowRank.AnyCard => gameState.deck.getCards(1, turnFaceUp = true)
          case FoundationLowRank.Ascending => throw new NotImplementedError()
          case FoundationLowRank.SpecificRank(r) if r == Rank.Unknown => gameState.deck.getCards(1, turnFaceUp = true, rank = Some(randomRank))
          case FoundationLowRank.SpecificRank(r) => gameState.deck.getCards(1, turnFaceUp = true, rank = Some(r))
          case FoundationLowRank.DeckLowRank => gameState.deck.getCards(1, turnFaceUp = true, rank = Some(gameState.deck.lowRank))
          case FoundationLowRank.DeckHighRank => gameState.deck.getCards(1, turnFaceUp = true, rank = Some(gameState.deck.highRank))
        }
        gameState.addCards(card, prefix + col, reveal = true)
      }
    }
  }
}
