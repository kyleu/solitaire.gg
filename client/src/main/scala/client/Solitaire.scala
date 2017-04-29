package client

import client.game.{SolitaireUndoHelper, StartGameHelper}
import client.json.{BaseSerializers, JsonUtils, ResponseMessageSerializers}
import client.user.{DataHelper, PreferenceHelper}
import models._
import models.game.UndoHelper

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

object Solitaire extends js.JSApp with StartGameHelper with SolitaireUndoHelper with PreferenceHelper {
  override def main(): Unit = {}

  override protected val undoHelper = new UndoHelper()
  private[this] var sendCallback: js.Function1[String, Unit] = _

  @JSExport
  def register(callback: js.Function1[String, Unit]) = {
    sendCallback = callback
  }

  @JSExport
  def jsError(e: js.Dynamic) = {
    onError(e, new java.util.Date().getTime)
  }

  @JSExport
  def receive(c: String, v: js.Dynamic): Unit = c match {
    case "GetVersion" => send(VersionResponse("0.0"))
    case "Ping" => send(Pong(JsonUtils.getLong(v.timestamp)))
    case "StartGame" => handleStartGame(v.rules.toString, JsonUtils.getIntOption(v.seed), preferences)
    case "SelectCard" => handleSelectCard(DataHelper.deviceId, v.card.toString.toInt, v.pile.toString, v.auto.toString == "true")
    case "SelectPile" => handleSelectPile(DataHelper.deviceId, v.pile.toString, v.auto.toString == "true")
    case "MoveCards" => handleMoveCards(DataHelper.deviceId, JsonUtils.getIntSeq(v.cards), v.src.toString, v.tgt.toString, v.auto.toString == "true")
    case "Undo" => handleUndo()
    case "Redo" => handleRedo()
    case "SetPreference" => handleSetPreference(v.name.toString, v.value.toString, gs)
    case "DebugInfo" => handleDebugInfo(v.data.toString)
    case _ => throw new IllegalStateException(s"Invalid message [$c].")
  }

  protected[this] def getResult = GameResult(
    moves = moveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    score = gs.calculateScore(moveCount, undoHelper.undoCount, elapsed),
    durationSeconds = elapsed,
    leaderboardRanking = 0
  )

  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true) = {
    if (registerUndoResponse) {
      undoHelper.registerResponse(rm)
    }
    val json = ResponseMessageSerializers.write(rm)
    val s = BaseSerializers.write(json)
    sendJson(s)
  }

  protected def sendJson(json: String) = {
    sendCallback(json)
  }

  private[this] def handleDebugInfo(data: String) = data match {
    case "cheat win" => send(GameWon(
      id = gameId.getOrElse(throw new IllegalStateException()),
      rules = gameState.map(_.rules).getOrElse("?"),
      firstForRules = false,
      firstForSeed = false,
      getResult,
      readStatistics()
    ))
    case _ => throw new IllegalStateException("Debug: " + data)
  }
}
