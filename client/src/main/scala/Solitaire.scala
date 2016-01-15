import java.util.UUID

import json.{ BaseSerializers, ResponseMessageSerializers, JsonUtils }
import models.rules.GameRulesSet
import models._
import models.rules.moves.InitialMoves

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Solitaire extends js.JSApp with SolitaireUndoHelper with PreferenceHelper {
  override def main(): Unit = {}

  private[this] val rng = new Random()

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
    case "StartGame" => handleStartGame(v.rules.toString, JsonUtils.getIntOption(v.seed))
    case "SelectCard" => handleSelectCard(deviceId, UUID.fromString(v.card.toString), v.pile.toString, v.auto.toString == "true")
    case "SelectPile" => handleSelectPile(deviceId, v.pile.toString, v.auto.toString == "true")
    case "MoveCards" => handleMoveCards(deviceId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString, v.auto.toString == "true")
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

  private[this] def handleStartGame(rules: String, seed: Option[Int]): Unit = {
    gameState.foreach(x => onLoss())
    clearRequests()

    val id = UUID.randomUUID
    gameId = Some(id)

    val gr = GameRulesSet.allByIdWithAliases(rules)
    gameRules = Some(gr)

    val actualSeed = seed.getOrElse(Math.abs(rng.nextInt()))

    val gs = gr.newGame(id, actualSeed, rules)

    gameStatus = "started"
    gameState = Some(gs)
    gs.addPlayer(deviceId, "Offline Player", autoFlipOption = preferences.autoFlip)
    InitialMoves.performInitialMoves(gameRules.getOrElse(throw new IllegalStateException()), gs)

    onGameStart(id, gr.id, actualSeed, System.currentTimeMillis)

    send(GameJoined(gameId.getOrElse(throw new IllegalStateException()), gs.view(deviceId), 0, possibleMoves(), preferences))
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
