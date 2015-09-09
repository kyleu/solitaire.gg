import java.util.UUID

import models.rules.GameRulesSet
import models._
import models.rules.moves.InitialMoves

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Solitaire extends js.JSApp with SolitaireMoveHelper with SolitairePreferenceHelper {
  override def main(): Unit = {}

  private[this] val userId = UUID.randomUUID
  private[this] val rng = new Random()

  protected[this] var undoHelper = new UndoHelper()
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
      case "SelectCard" => handleSelectCard(userId, UUID.fromString(v.card.toString), v.pile.toString)
      case "SelectPile" => handleSelectPile(userId, v.pile.toString)
      case "MoveCards" => handleMoveCards(userId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString)
      case "Undo" => handleUndo()
      case "Redo" => handleRedo()
      case "SetPreference" => handleSetPreference(v.name.toString, v.value.toString)
      case _ => throw new IllegalStateException(s"Invalid message [$c].")
    }
  }

  protected[this] def getResult = GameResult(
    moves = moveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    score = 0,
    durationSeconds = 0,
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
    gameId = UUID.randomUUID
    gameRules = GameRulesSet.allByIdWithAliases(rules)
    gameState = gameRules.newGame(gameId, seed.getOrElse(Math.abs(rng.nextInt())), rules)
    gameState.addPlayer(userId, "Offline Player", autoFlipOption = false)
    InitialMoves.performInitialMoves(gameRules, gameState)

    send(GameJoined(gameId, gameState.view(userId), 0, possibleMoves()))
  }

  private[this] def handleUndo(): Unit = {
    if (undoHelper.historyQueue.nonEmpty) {
      val undone = undoHelper.undo(gameState)
      send(undone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }

  private[this] def handleRedo(): Unit = {
    if (undoHelper.undoneQueue.nonEmpty) {
      val redone = undoHelper.redo(gameState)
      send(redone, registerUndoResponse = false)
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size), registerUndoResponse = false)
    }
  }
}
