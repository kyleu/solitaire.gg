package models.rules.moves

import models.card.{Color, Rank, Suit}
import models.game.GameState
import models.rules.{FoundationInitialCardRestriction, FoundationLowRank, GameRules}

object FoundationInitialMoves {
  def performInitialMoves(rules: GameRules, gameState: GameState) = {
    rules.foundations.foreach { fr =>
      val prefix = if (fr.setNumber == 0) {
        "foundation-"
      } else {
        s"foundation${fr.setNumber + 1}-"
      }
      val randomRank = gameState.deck.cards.headOption match {
        case Some(c) => c.r
        case None => throw new IllegalStateException()
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

      val requiredRank = fr.lowRank match {
        case FoundationLowRank.AnyCard => None
        case FoundationLowRank.Ascending => Some(Rank.Unknown)
        case FoundationLowRank.SpecificRank(r) if r == Rank.Unknown => Some(randomRank)
        case FoundationLowRank.SpecificRank(r) => Some(r)
        case FoundationLowRank.DeckLowRank => Some(gameState.deck.lowRank)
        case FoundationLowRank.DeckHighRank => Some(gameState.deck.highRank)
      }

      (0 until fr.initialCards).foreach { i =>
        val col = i % fr.numPiles
        val rank = requiredRank.map {
          case Rank.Unknown => col match {
            case 0 => Rank.Ace
            case _ => Rank.allByValue(col + 1)
          }
          case x => x
        }
        val cards = gameState.deck.getCards(1, turnFaceUp = true, rank, Set.empty, requiredSuit, encounteredSuits, requiredColor, encounteredColors)
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
