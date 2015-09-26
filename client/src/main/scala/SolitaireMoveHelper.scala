import java.util.UUID

import models._

trait SolitaireMoveHelper extends SolitaireVictoryHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean): Unit

  protected def send(rms: Seq[ResponseMessage], registerUndoResponse: Boolean): Unit = if(rms.size == 1){
    val msg = rms.headOption.getOrElse(throw new IllegalStateException())
    send(msg, registerUndoResponse)
  } else {
    send(MessageSet(rms), registerUndoResponse)
  }

  protected def getResult: GameResult

  protected[this] def handleSelectCard(userId: UUID, cardId: UUID, pileId: String) = {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException(s"SelectCard for game [$gameId]: Card [${card.toString}] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      send(messages, registerUndoResponse = true)
      if(messages.size != 1 || messages.headOption.map {
        case _: CardRevealed => false
        case _ => true
      }.getOrElse(true)) {
        registerMove()
      }
    }
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String) = {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      throw new IllegalStateException(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) { pile.onSelectPile(gameState) } else { Nil }
    send(messages, registerUndoResponse = true)
    registerMove()
  }

  protected[this] def handleMoveCards(userId: UUID, cardIds: Seq[UUID], source: String, target: String) = {
    val cards = cardIds.map(gameState.cardsById)
    val sourcePile = gameState.pilesById(source)
    val targetPile = gameState.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException(s"Card [$c] is not a part of source pile [${sourcePile.id}].")
      }
    }
    if (sourcePile.canDragFrom(cards, gameState)) {
      if (targetPile.canDragTo(sourcePile, cards, gameState)) {
        val messages = targetPile.onDragTo(sourcePile, cards, gameState)
        send(messages, registerUndoResponse = true)
        registerMove()
      } else {
        send(CardMoveCancelled(cardIds, source))
      }
    } else {
      send(CardMoveCancelled(cardIds, source))
    }
  }
}
