package models.rules.moves

import models.game.GameState
import models.rules.GameRules

object InitialMoves {
  def performInitialMoves(rules: GameRules, state: GameState) = performDefault(rules, state)

  private[this] def performDefault(rules: GameRules, gameState: GameState) = {
    FoundationInitialMoves.performInitialMoves(rules, gameState)

    rules.reserves.foreach { rr =>
      (0 until rr.initialCards).foreach { i =>
        (1 to rr.numPiles).foreach { p =>
          val reveal = rr.cardsFaceDown match {
            case -1 => i == (rr.initialCards - 1)
            case x => i >= x
          }
          gameState.addCardsFromDeck(1, s"reserve-$p", reveal = reveal)
        }
      }
    }

    rules.cells.foreach { cr =>
      (0 until cr.initialCards).foreach { i =>
        val col = (i % cr.numPiles) + 1
        gameState.addCardsFromDeck(1, s"cell-$col", reveal = true)
      }
    }

    rules.pyramids.foreach { pr =>
      val prefix = if (pr.setNumber == 0) {
        "pyramid-"
      } else {
        s"pyramid${pr.setNumber + 1}-"
      }
      (1 to pr.height).foreach { i =>
        (1 to i).foreach { j =>
          gameState.addCardsFromDeck(1, s"$prefix$i-$j", reveal = true)
        }
      }
    }

    TableauInitialMoves.performInitialMoves(rules, gameState)

    rules.stock.foreach(_ => gameState.addCards(gameState.deck.getCards().reverse, "stock"))

    rules.waste.foreach { w =>
      val cardCount = if (w.maxCards.exists(x => x < gameState.deck.cards.length)) {
        w.maxCards.getOrElse(0)
      } else {
        gameState.deck.cards.length
      }
      gameState.addCards(gameState.deck.getCards(numCards = cardCount, turnFaceUp = true), "waste-1", reveal = true)
    }

    if(gameState.deck.cards.nonEmpty) {
      throw new IllegalStateException(s"[${gameState.deck.cards.size}] remaining in deck after initial moves.")
    }
  }
}
