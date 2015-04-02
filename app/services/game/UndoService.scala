package services.game

import models.ResponseMessage
import utils.Logging

class UndoService() extends Logging {
  private val undoQueue = collection.mutable.ArrayBuffer.empty[ResponseMessage]

  def registerResponse(rm: ResponseMessage) = {
    undoQueue += rm
  }

  def undo() = {
    log.info("Undoing move.")
  }
}
