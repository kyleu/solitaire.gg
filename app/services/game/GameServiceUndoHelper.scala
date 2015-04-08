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
      log.info("Undoing message [" + msg.toString + "] (" + historyQueue.length + " other messages in history queue).")
      undoQueue.push(msg)
    }
  }

  protected[this] def handleRedo(accountId: UUID) = {
    if (undoQueue.isEmpty) {
      log.info("Attempt to redo from empty undo stack.")
    } else {
      val msg = undoQueue.pop()
      log.info("Performing redo of [" + msg.toString + "] (" + undoQueue.length + " other messages in undo queue).")
      msg match {
        case cr: CardRevealed => handleCardRevealed(accountId, cr)
        case cr: CardRemoved => handleCardRemoved(accountId, cr)
        case cm: CardMoved => handleCardMoved(accountId, cm)
        case ms: MessageSet => handleMessageSet(accountId, ms)
      }
      historyQueue.push(msg)
    }
  }

  private[this] def handleCardRevealed(uuid: UUID, revealed: CardRevealed) = ???
  private[this] def handleCardRemoved(uuid: UUID, removed: CardRemoved) = ???
  private[this] def handleCardMoved(uuid: UUID, moved: CardMoved) = ???
  private[this] def handleMessageSet(uuid: UUID, set: MessageSet) = ???

}
