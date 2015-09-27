import models._
import models.game.GameState

trait SolitaireUndoHelper {
  protected def undoHelper: UndoHelper
  protected def gameState: GameState
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit
  protected def possibleMoves(): Seq[PossibleMove]

  protected[this] def handleUndo(): Unit = {
    if (undoHelper.historyQueue.nonEmpty) {
      val undone = undoHelper.undo(gameState)
      send(undone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }

  protected[this] def handleRedo(): Unit = {
    if (undoHelper.undoneQueue.nonEmpty) {
      val redone = undoHelper.redo(gameState)
      send(redone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }
}
