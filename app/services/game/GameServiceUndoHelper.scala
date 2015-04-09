package services.game

import java.util.UUID

import models._

trait GameServiceUndoHelper { this: GameService =>
  protected var undosByPlayer = collection.mutable.HashMap.empty[UUID, Int]

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
      undosByPlayer(accountId) = undosByPlayer.getOrElseUpdate(accountId, 0) + 1
      val msg = historyQueue.pop()
      val reverse = getReverse(msg)
      undoQueue.push(reverse)
      log.info("Undoing message [" + msg.toString + "] with message [" + reverse + "] (" + historyQueue.length + " other messages in history queue).")
      sendToAll(reverse, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
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
