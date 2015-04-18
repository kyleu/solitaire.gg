package services.game

import java.util.UUID

import models._

trait GameServiceUndoHelper { this: GameService =>
  protected[this] val undoHelper = new UndoHelper()

  protected[this] def handleUndo(accountId: UUID) = {
    if (undoHelper.historyQueue.isEmpty) {
      log.info("Attempt to undo with no actions available.")
    } else {
      val undone = undoHelper.undo(gameState)
      sendToAll(undone, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

  protected[this] def handleRedo(accountId: UUID) = {
    if (undoHelper.undoneQueue.isEmpty) {
      log.info("Attempt to redo from empty undo stack.")
    } else {
      val redone = undoHelper.redo(gameState)
      sendToAll(redone, registerUndoResponse = false)
      handleGetPossibleMoves(accountId)
    }
  }

}
