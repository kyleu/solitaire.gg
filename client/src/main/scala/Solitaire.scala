import java.util.UUID

import json.{ JsonUtils, JsonSerializers }
import models.rules.GameRulesSet
import models._
import models.rules.moves.InitialMoves

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Solitaire extends js.JSApp with SolitaireHelper {
  override def main(): Unit = {}

  private[this] val rng = new Random()

  override protected val undoHelper = new UndoHelper()
  private[this] var sendCallback: js.Function1[String, Unit] = _

  @JSExport
  def register(callback: js.Function1[String, Unit]) = {
    sendCallback = callback
  }

  @JSExport
  def receive(c: String, v: js.Dynamic): Unit = {
    c match {
      case "GetVersion" => send(VersionResponse("0.0"))
      case "Ping" => send(Pong(JsonUtils.getLong(v.timestamp)))
      case "StartGame" => handleStartGame(v.rules.toString, JsonUtils.getIntOption(v.seed))
      case "SelectCard" => handleSelectCard(deviceId, UUID.fromString(v.card.toString), v.pile.toString)
      case "SelectPile" => handleSelectPile(deviceId, v.pile.toString)
      case "MoveCards" => handleMoveCards(deviceId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString)
      case "Undo" => handleUndo()
      case "Redo" => handleRedo()
      case "SetPreference" => handleSetPreference(v.name.toString, v.value.toString, gameState.getOrElse(throw new IllegalStateException()))
      case "DebugInfo" => handleDebugInfo(v.data.toString)
      case _ => throw new IllegalStateException(s"Invalid message [$c].")
    }
  }

  protected[this] def getResult = GameResult(
    moves = moveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    score = gameState.getOrElse(throw new IllegalStateException()).calculateScore(moveCount, undoHelper.undoCount, elapsed),
    durationSeconds = elapsed,
    leaderboardRanking = 0
  )

  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit = {
    if (registerUndoResponse) {
      undoHelper.registerResponse(rm)
    }
    val json = JsonSerializers.write(rm)
    sendCallback(JsonSerializers.write(json))
  }

  private[this] def handleStartGame(rules: String, seed: Option[Int]): Unit = {
    gameState.foreach(x => onLoss())

    val id = UUID.randomUUID
    gameId = Some(id)

    val gr = GameRulesSet.allByIdWithAliases(rules)
    gameRules = Some(gr)

    val actualSeed = seed.getOrElse(Math.abs(rng.nextInt()))

    val gs = gr.newGame(id, actualSeed, rules)

    gameState = Some(gs)
    gs.addPlayer(deviceId, "Offline Player", autoFlipOption = preferences.autoFlip)
    InitialMoves.performInitialMoves(gameRules.getOrElse(throw new IllegalStateException()), gs)

    onGameStart(id, gr.id, actualSeed, System.currentTimeMillis)

    send(GameJoined(gameId.getOrElse(throw new IllegalStateException()), gs.view(deviceId), 0, possibleMoves(), preferences))
  }

  private[this] def handleDebugInfo(data: String) = data match {
    case "cheat win" => send(GameWon(
      gameId.getOrElse(throw new IllegalStateException()),
      firstForRules = false,
      firstForSeed = false,
      getResult,
      readStatistics()
    ))
    case _ => throw new IllegalStateException("Debug: " + data)
  }
}
