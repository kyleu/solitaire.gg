package client.game

import java.util.UUID

import client.user.StatisticsHelper

import models._
import models.game.GameState
import models.rules.GameRules
import client.network.AnalyticsHelper

trait VictoryHelper extends StatisticsHelper with AnalyticsHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit

  protected def getResult: GameResult

  protected def undoHelper: UndoHelper

  protected var gameId: Option[UUID] = None
  protected var gameRules: Option[GameRules] = None
  protected var gameState: Option[GameState] = None
  protected def gs = gameState.getOrElse(throw new IllegalStateException())

  protected[this] var moveCount = 0
  protected[this] var firstMoveMade: Option[Long] = None
  protected[this] var lastMoveMade: Option[Long] = None

  protected[this] def elapsed = {
    val ms = firstMoveMade.map(f => lastMoveMade.map(l => l - f).getOrElse(0L)).getOrElse(0L)
    (ms / 1000).toInt
  }

  protected def onWin() = {
    gameStatus = "won"
    val gs = gameState.getOrElse(throw new IllegalStateException())
    val completed = lastMoveMade.getOrElse(0L)
    val stats = registerGame(
      win = true,
      gs.rules, gs.seed, moveCount,
      undoHelper.undoCount, undoHelper.redoCount,
      firstMoveMade.map(x => completed - x).getOrElse(0L), completed
    )

    val msg = GameWon(
      id = gameId.getOrElse(throw new IllegalStateException()),
      rules = gameState.map(_.rules).getOrElse("?"),
      firstForRules = false,
      firstForSeed = false,
      getResult,
      stats
    )
    onGameWon(msg, System.currentTimeMillis)
    send(msg)
  }

  protected def onLoss() = if (gameStatus != "won") {
    val gs = gameState.getOrElse(throw new IllegalStateException())
    val completed = lastMoveMade.getOrElse(0L)
    val stats = registerGame(
      win = false,
      gs.rules, gs.seed, moveCount,
      undoHelper.undoCount, undoHelper.redoCount,
      firstMoveMade.map(x => completed - x).getOrElse(0L), completed
    )
    val msg = GameLost(
      id = gameId.getOrElse(throw new IllegalStateException()),
      rules = gameState.map(_.rules).getOrElse("?"),
      getResult,
      stats
    )
    gameStatus = "lost"
    onGameResigned(msg, System.currentTimeMillis)
    send(msg)
  }
}
