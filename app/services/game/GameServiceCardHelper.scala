package services.game

import java.util.UUID

import models._

trait GameServiceCardHelper { this: GameService =>
  protected[this] def handleSelectCard(userId: UUID, cardId: UUID, pileId: String) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      log.warn("SelectCard for game [" + id + "]: Card [" + card.toString + "] is not part of the [" + pileId + "] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      sendToAll(messages)
    } else {
      log.warn("SelectCard called on [" + card + "], which cannot be selected.")
    }
    checkWinCondition()
    if (!gameWon) {
      handleGetPossibleMoves(userId)
    }
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String) {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.length > 0) {
      log.warn("SelectPile [" + pileId + "] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) {
      pile.onSelectPile(gameState)
    } else {
      log.warn("SelectPile called on [" + pile + "], which cannot be selected.")
      Nil
    }
    sendToAll(messages)

    checkWinCondition()
    if (!gameWon) {
      handleGetPossibleMoves(userId)
    }
  }

  protected[this] def handleMoveCards(userId: UUID, cardIds: Seq[UUID], source: String, target: String) {
    val cards = cardIds.map(gameState.cardsById)
    val sourcePile = gameState.pilesById(source)
    val targetPile = gameState.pilesById(target)

    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException("Card [" + c + "] is not a part of source pile [" + sourcePile.id + "].")
      }
    }

    if (sourcePile.canDragFrom(cards, gameState)) {
      if (targetPile.canDragTo(cards, gameState)) {
        val messages = targetPile.onDragTo(sourcePile, cards, gameState)

        sendToAll(messages)
        checkWinCondition()
        if (!gameWon) {
          handleGetPossibleMoves(userId)
        }
      } else {
        log.debug("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] to pile [" + targetPile.id + "].")
        sendToAll(CardMoveCancelled(cardIds, source))
      }
    } else {
      log.debug("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] from pile [" + sourcePile.id + "].")
      sendToAll(CardMoveCancelled(cardIds, source))
    }
  }

  private[this] def checkWinCondition() = if (!gameWon && gameRules.victoryCondition.check(gameState)) {
    gameWon = true
    setStatus("win")
    sendToAll(GameWon(id))
  }
}
