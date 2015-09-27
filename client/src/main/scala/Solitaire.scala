import java.util.UUID

import models.rules.GameRulesSet
import models._
import models.rules.moves.InitialMoves

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

import scala.util.Random

object Solitaire extends js.JSApp with SolitaireUndoHelper with SolitaireMoveHelper with SolitairePreferenceHelper {
  override def main(): Unit = {}

  private[this] val userId = UUID.randomUUID
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
      case "SelectCard" => handleSelectCard(userId, UUID.fromString(v.card.toString), v.pile.toString)
      case "SelectPile" => handleSelectPile(userId, v.pile.toString)
      case "MoveCards" => handleMoveCards(userId, JsonUtils.getUuidSeq(v.cards), v.src.toString, v.tgt.toString)
      case "Undo" => handleUndo()
      case "Redo" => handleRedo()
      case "SetPreference" => handleSetPreference(v.name.toString, v.value.toString, gameState)
      case "DebugInfo" => handleDebugInfo(v.data.toString)
      case _ => throw new IllegalStateException(s"Invalid message [$c].")
    }
  }

  protected[this] def getResult = GameResult(
    moves = moveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    score = gameState.calculateScore(moveCount, undoHelper.undoCount, elapsed),
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
    gameId = UUID.randomUUID
    gameRules = GameRulesSet.allByIdWithAliases(rules)
    gameState = gameRules.newGame(gameId, seed.getOrElse(Math.abs(rng.nextInt())), rules)
    gameState.addPlayer(userId, "Offline Player", autoFlipOption = preferences.autoFlip)
    InitialMoves.performInitialMoves(gameRules, gameState)

    send(GameJoined(gameId, gameState.view(userId), 0, possibleMoves(), preferences))
  }

  override protected def onWin() = {
    val completed = lastMoveMade.getOrElse(0L)
    val stats = registerGame(win = true,
      gameState.rules, gameState.seed, moveCount,
      undoHelper.undoCount, undoHelper.redoCount,
      firstMoveMade.map(x => completed - x).getOrElse(0L), completed
    )
    send(GameWon(gameId, firstForRules = false, firstForSeed = false, getResult, stats))
  }

  private[this] def handleDebugInfo(data: String) = data match {
    case "cheat win" => send(GameWon(gameId, firstForRules = false, firstForSeed = false, getResult, readStatistics()))
    case _ => throw new IllegalStateException("Debug: " + data)
  }
}
