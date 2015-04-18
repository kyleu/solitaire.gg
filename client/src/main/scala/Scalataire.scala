import java.util.UUID

import models.game.GameState
import models.game.variants.GameVariant
import models._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Scalataire extends js.JSApp with ScalataireHelper {
  protected[this] var gameId: UUID = _

  private[this] var sendCallback: js.Function1[String, Unit] = _

  private[this] val accountId = UUID.randomUUID
  private[this] val rng = new Random()

  protected[this] var gameVariant: GameVariant = _
  protected[this] var gameState: GameState = _

  protected[this] var undoHelper = new UndoHelper()

  def main() = {
    Unit
  }

  @JSExport
  def register(callback: js.Function1[String, Unit]) = sendCallback = callback

  @JSExport
  def receive(c: String, v: js.Dynamic) = {
    c match {
      case "GetVersion" => send(VersionResponse("0.0"))
      case "Ping" => send(Pong(JsonUtils.getLong(v.timestamp)))
      case "StartGame" => handleStartGame(v.variant.toString, JsonUtils.getIntOption(v.seed))
      case "SelectCard" => handleSelectCard(accountId, UUID.fromString(v.card.toString), v.pile.toString)
      case "SelectPile" => handleSelectPile(accountId, v.pile.toString)
      case "MoveCards" => handleMoveCards(accountId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString)
      case "Undo" => handleUndo()
      case "Redo" => handleRedo()
      case _ => throw new IllegalStateException("Invalid message [" + c + "].")
    }
  }

  protected[this] def send(rm: ResponseMessage, registerUndoResponse: Boolean = true) = {
    if(registerUndoResponse) {
      undoHelper.registerResponse(rm)
    }
    val json = JsonSerializers.write(rm)
    sendCallback(JsonSerializers.write(json))
  }

  private[this] def handleStartGame(variant: String, seed: Option[Int]) = {
    gameId = UUID.randomUUID
    gameVariant = GameVariant(variant, gameId, seed.getOrElse(Math.abs(rng.nextInt())))
    gameState = gameVariant.gameState
    gameState.addPlayer(accountId, "Offline Player")
    gameVariant.initialMoves()

    send(GameJoined(gameId, gameState.view(accountId), possibleMoves()))
  }

  private[this] def handleUndo() = {
    if (undoHelper.historyQueue.isEmpty) {
      // no op
    } else {
      val undone = undoHelper.undo(gameState)
      send(undone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }

  private[this] def handleRedo() = {
    if (undoHelper.undoneQueue.isEmpty) {
      // no op
    } else {
      val redone = undoHelper.redo(gameState)
      send(redone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }
}
