package models.game.rules.moves

import models.game.GameState
import models.game.rules.{ GameRules, InitialCards }

object InitialMoves {
  def performInitialMoves(rules: GameRules, state: GameState) = {
    val f = rules.id match {
      case _ => performDefault(rules, state)
    }
  }

  private[this] def performDefault(rules: GameRules, gameState: GameState) = {
    TableauInitialMoves.performInitialMoves(rules, gameState)

    rules.reserves.foreach { rr =>
      (1 to rr.initialCards).foreach { row =>
        (1 to rr.numPiles).foreach { col =>
          gameState.addCardsFromDeck(1, "reserve-" + col, reveal = true)
        }
      }
    }

    rules.pyramids.foreach { pr =>
      (1 to pr.height).foreach { i =>
        (1 to i).foreach { j =>
          gameState.addCardsFromDeck(1, "pyramid-" + i + "-" + j, reveal = true)
        }
      }
    }

    rules.foundations.foreach { fr =>
      fr.initialCards match {
        case InitialCards.Count(i) => (0 until i).foreach { idx =>
          gameState.addCardsFromDeck(1, "foundation-" + ((idx % fr.numPiles) + 1), reveal = true)
        }
        case InitialCards.PileIndex => throw new NotImplementedError()
        case InitialCards.RestOfDeck => throw new NotImplementedError()
        case InitialCards.Custom => throw new NotImplementedError()
      }
    }

    rules.stock.foreach { s =>
      gameState.addCards(gameState.deck.getCards().reverse, "stock")
    }
  }
}
