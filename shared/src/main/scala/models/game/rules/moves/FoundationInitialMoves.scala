package models.game.rules.moves

import models.game.{ Color, Suit, Rank, GameState }
import models.game.rules.{ FoundationInitialCardRestriction, FoundationLowRank, GameRules }

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

      val requiredRank = fr.lowRank match {
        case FoundationLowRank.AnyCard => None
        case FoundationLowRank.Ascending => throw new NotImplementedError()
        case FoundationLowRank.SpecificRank(r) if r == Rank.Unknown => Some(randomRank)
        case FoundationLowRank.SpecificRank(r) => Some(r)
        case FoundationLowRank.DeckLowRank => Some(gameState.deck.lowRank)
        case FoundationLowRank.DeckHighRank => Some(gameState.deck.highRank)
      }

      val (uniqueSuits, requiredSuit, uniqueColors, requiredColor) = fr.initialCardRestriction match {
        case Some(FoundationInitialCardRestriction.UniqueSuits) => (true, None, false, None)
        case Some(FoundationInitialCardRestriction.UniqueColors) => (false, None, true, None)
        case Some(FoundationInitialCardRestriction.SpecificColorUniqueSuits(c)) => (true, None, false, Some(c))
        case Some(FoundationInitialCardRestriction.SpecificSuit(s)) => (false, Some(s), false, None)
        case _ => (false, None, false, None)
      }

      var encounteredSuits = Set.empty[Suit]
      var encounteredColors = Set.empty[Color]

      (0 until fr.initialCards).foreach { i =>
        val col = i % fr.numPiles
        val cards = gameState.deck.getCards(1, turnFaceUp = true, requiredRank, Set.empty, requiredSuit, encounteredSuits, requiredColor, encounteredColors)
        if (uniqueSuits) {
          encounteredSuits = encounteredSuits ++ cards.map(_.s)
        }
        if (uniqueColors) {
          encounteredColors = encounteredColors ++ cards.map(_.s.color)
        }
        gameState.addCards(cards, prefix + (col + 1), reveal = true)
      }
    }
  }
}
