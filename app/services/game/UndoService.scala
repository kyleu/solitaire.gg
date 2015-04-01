package services.game

import java.util.UUID

import models.ResponseMessage
import utils.Logging

class UndoService() extends Logging {
  private val undoQueue = collection.mutable.ArrayBuffer.empty[(Option[UUID], ResponseMessage)]

  def registerResponse(player: Option[UUID], rm: ResponseMessage) = {
    undoQueue += (player -> rm)
  }

  def undo(player: UUID) = {
    log.info("Undoing move for player [" + player + "].")
  }
}
