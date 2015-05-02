package models.game.rules.moves

import models.game.GameState
import models.game.rules.GameRules

object InitialMoves {
  def performInitialMoves(rules: GameRules, state: GameState) = {
    val f = rules.id match {
      case _ => performDefault(rules, state)
    }
  }

  private[this] def performDefault(rules: GameRules, gameState: GameState) = {
    FoundationInitialMoves.performInitialMoves(rules, gameState)

    rules.reserves.foreach { rr =>
      (0 until rr.initialCards).foreach { i =>
        val col = (i % rr.numPiles) + 1
        gameState.addCardsFromDeck(1, "reserve-" + col, reveal = true)
      }
    }

    rules.cells.foreach { cr =>
      (0 until cr.initialCards).foreach { i =>
        val col = (i % cr.numPiles) + 1
        gameState.addCardsFromDeck(1, "cell-" + col, reveal = true)
      }
    }

    rules.pyramids.foreach { pr =>
      val prefix = if (pr.setNumber == 0) {
        "pyramid-"
      } else {
        "pyramid" + (pr.setNumber + 1) + "-"
      }
      (1 to pr.height).foreach { i =>
        (1 to i).foreach { j =>
          gameState.addCardsFromDeck(1, prefix + i + "-" + j, reveal = true)
        }
      }
    }

    TableauInitialMoves.performInitialMoves(rules, gameState)

    rules.stock.foreach { s =>
      gameState.addCards(gameState.deck.getCards().reverse, "stock")
    }

    rules.waste.foreach { w =>
      gameState.addCards(gameState.deck.getCards(turnFaceUp = true), "waste-1", reveal = true)
    }
  }
}
