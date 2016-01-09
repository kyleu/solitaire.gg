import models._
import models.game.GameState

trait SolitaireUndoHelper extends MoveHelper {
  protected def undoHelper: UndoHelper
  protected def gameState: Option[GameState]
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit
  protected def possibleMoves(): Seq[PossibleMove]

  protected[this] def handleUndo(): Unit = {
    if (undoHelper.historyQueue.nonEmpty) {
      registerRequest("undo")
      val undone = undoHelper.undo(gameState.getOrElse(throw new IllegalStateException()))
      send(undone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }

  protected[this] def handleRedo(): Unit = {
    if (undoHelper.undoneQueue.nonEmpty) {
      registerRequest("redo")
      val redone = undoHelper.redo(gameState.getOrElse(throw new IllegalStateException()))
      send(redone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }
}
