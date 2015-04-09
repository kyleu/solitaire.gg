package services.game

import java.util.UUID

import models._

trait GameServiceUndoHelper { this: GameService =>
  private[this] val historyQueue = collection.mutable.Stack[ReversibleResponseMessage]()
  private[this] val undoQueue = collection.mutable.Stack[ReversibleResponseMessage]()

  protected[this] def registerResponse(message: ResponseMessage) = message match {
    case rrm: ReversibleResponseMessage => historyQueue.push(rrm)
    case _ => // no-op
  }

  protected[this] def handleUndo(accountId: UUID) = {
    if (historyQueue.isEmpty) {
      log.info("Attempt to undo with no actions available.")
    } else {
      val msg = historyQueue.pop()
      val reverse = getReverse(msg)
      undoQueue.push(reverse)
      log.info("Undoing message [" + msg.toString + "] with message [" + reverse + "] (" + historyQueue.length + " other messages in history queue).")
      sendToAll(reverse, registerUndoResponse = false)
    }
  }

  protected[this] def handleRedo(accountId: UUID) = {
    if (undoQueue.isEmpty) {
      log.info("Attempt to redo from empty undo stack.")
    } else {
      val msg = undoQueue.pop()
      val reverse = getReverse(msg)
      historyQueue.push(reverse)
      log.info("Performing redo of [" + msg.toString + "] with message [" + reverse + "] (" + undoQueue.length + " other messages in undo queue).")
      sendToAll(msg)
    }
  }

  private[this] def getReverse(rrm: ReversibleResponseMessage): ReversibleResponseMessage = rrm match {
    case cr: CardRevealed => cr

    case cm: CardMoved =>
      val src = gameState.pilesById(cm.source)
      val tgt = gameState.pilesById(cm.target)
      val card = tgt.cards(cm.targetIndex.getOrElse(tgt.cards.size - 1))
      tgt.removeCard(card)
      src.addCard(card)

      cm.copy(
        source = cm.target,
        target = cm.source,
        turnFaceUp = cm.turnFaceDown,
        turnFaceDown = cm.turnFaceUp
      )

    case ms: MessageSet =>
      MessageSet(ms.messages.flatMap {
        case rrm: ReversibleResponseMessage => Some(rrm)
        case _ => None
      }.reverseMap(getReverse))
  }
}
