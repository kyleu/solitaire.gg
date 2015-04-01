package services.game

import java.util.UUID

import models.ResponseMessage

case class UndoService() {
  private val undoQueue = collection.mutable.ArrayBuffer.empty[ResponseMessage]

  def registerResponse(player: Option[UUID], rm: ResponseMessage) = {
    undoQueue += rm
  }

  def undo(player: UUID) = {

  }
}
