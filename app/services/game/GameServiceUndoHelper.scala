package services.game

import java.util.UUID

import models._

trait GameServiceUndoHelper {
  this: GameService =>
  protected var undoCount = 0
  protected var redoCount = 0

  protected[this] val historyQueue = collection.mutable.Stack[ReversibleResponseMessage]()
  protected[this] val undoneQueue = collection.mutable.Stack[ReversibleResponseMessage]()

  protected[this] def registerResponse(message: ResponseMessage) = message match {
    case rrm: ReversibleResponseMessage =>
      historyQueue.push(rrm)
      undoneQueue.clear()
    case _ => // no-op
  }

  protected[this] def handleUndo(accountId: UUID) = {
    if (historyQueue.isEmpty) {
      log.info("Attempt to undo with no actions available.")
    } else {
      undoCount += 1
      val msg = historyQueue.pop()
      val reverse = getReverse(msg)
      undoneQueue.push(reverse)
      sendToAll(reverse, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

  protected[this] def handleRedo(accountId: UUID) = {
    if (undoneQueue.isEmpty) {
      log.info("Attempt to redo from empty undo stack.")
    } else {
      redoCount += 1
      val msg = undoneQueue.pop()
      val reverse = getReverse(msg)
      historyQueue.push(reverse)
      sendToAll(reverse, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

  private[this] def getReverse(rrm: ReversibleResponseMessage): ReversibleResponseMessage = rrm match {
    case cr: CardRevealed =>
      cr.card.u = false
      gameState.hideCardFromAll(cr.card).headOption.getOrElse(throw new IllegalStateException("No hide response."))
    case ch: CardHidden =>
      val card = gameState.getCard(ch.id)
      card.u = true
      gameState.revealCardToAll(card).headOption.getOrElse(throw new IllegalStateException("No reveal response."))
    case cm: CardMoved =>
      val src = gameState.pilesById(cm.source)
      val tgt = gameState.pilesById(cm.target)
      tgt.cards.find(_.id == cm.card) match {
        case Some(card) =>
          tgt.removeCard(card)
          src.addCard(card)
          CardMoved(cm.card, source = cm.target, target = cm.source, turn = cm.turn.map(!_))
        case None => throw new IllegalStateException("Can't find card [" + cm.card + "].")
      }
    case cm: CardsMoved =>
      val src = gameState.pilesById(cm.source)
      val tgt = gameState.pilesById(cm.target)
      val cards = cm.cards.map(x => tgt.cards.find(_.id == x).getOrElse(throw new IllegalStateException("Can't find card [" + x + "].")))
      cards.foreach { card =>
        tgt.removeCard(card)
        src.addCard(card)
      }
      CardsMoved(cm.cards, source = cm.target, target = cm.source, turn = cm.turn.map(!_))
    case ms: MessageSet =>
      MessageSet(ms.messages.reverse.flatMap {
        case rrm: ReversibleResponseMessage => Some(getReverse(rrm))
        case _ => None
      })
  }
}
