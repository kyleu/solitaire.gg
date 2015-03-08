package services.game

import models.{CardMoveCancelled, MessageSet, CardMoved}

trait GameServiceCardHelper { this: GameService =>
  protected def handleSelectCard(cardId: String, pileId: String, pileIndex: Int) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if(!pile.cards.contains(card)) {
      log.warn("SelectCard for game [" + id + "]: Card [" + card.toString + "] is not part of the [" + pileId + "] pile.")
    }
    val messages = pile.onSelectCard(card, gameState)
    sendToAll(messages)
  }

  protected def handleSelectPile(pileId: String) {
    val pile = gameState.pilesById(pileId)
    if(pile.cards.length > 0) {
      log.warn("SelectPile [" + pileId + "] called on a non-empty deck.")
    }
    val messages = pile.onSelectPile(gameState)
    sendToAll(messages)
  }

  protected def handleMoveCards(cardIds: Seq[String], sourceId: String, targetId: String) {
    val cards = cardIds.map(gameState.cardsById)
    val source = gameState.pilesById(sourceId)
    val target = gameState.pilesById(targetId)

    for(c <- cards) {
      if(!source.cards.contains(c)) {
        throw new IllegalArgumentException("Card [" + c + "] is not a part of source pile [" + source.id + "].")
      }
    }

    if(source.canDragFrom(cards)) {
      if(target.canDragTo(cards)) {
        val messages = cards.map { card =>
          source.removeCard(card)
          target.addCard(card)
          CardMoved(card.id, sourceId, targetId)
        }

        if(messages.size == 1) {
          sendToAll(messages(0))
        } else {
          sendToAll(MessageSet(messages))
        }
      } else {
        log.warn("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] to pile [" + target.id + "].")
        sendToAll(CardMoveCancelled(cardIds, sourceId))
      }
    } else {
      log.warn("Cannot drag cards [" + cards.map(_.toString).mkString(", ") + "] from pile [" + source.id + "].")
      sendToAll(CardMoveCancelled(cardIds, sourceId))
    }
  }
}
