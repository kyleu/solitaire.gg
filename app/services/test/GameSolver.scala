package services.test

import java.util.UUID

import models._
import models.game.{MoveHelper, PossibleMove, RequestMessageHandler, UndoHelper}
import models.history.GameHistory
import models.rules.GameRulesSet
import models.rules.moves.InitialMoves
import util.{DateUtils, Logging}

import scala.util.Random

object GameSolver {
  val moveLimit = 10000
  val userId = UUID.fromString("00000000-0000-0000-0000-000000000000")
}

case class GameSolver(rules: String, testSeed: Int, gameSeed: Int) extends Logging {
  val rng = new Random(testSeed)

  val start = DateUtils.now

  var gameWon = false

  val gameRules = GameRulesSet.allByIdWithAliases(rules)
  val gameId = UUID.randomUUID
  val gameState = gameRules.newGame(gameId, gameSeed, rules)

  val moveHelper = new MoveHelper(gameState, () => ())
  val undoHelper = new UndoHelper()

  private[this] val requests = new RequestMessageHandler(GameSolver.userId, gameState, undoHelper, handle, moveHelper.registerMove _)

  InitialMoves.performInitialMoves(gameRules, gameState)

  private[this] var moves: Seq[PossibleMove] = moveHelper.possibleMoves()
  private[this] var undosAvailable = undoHelper.historyQueue.size

  private[this] val exploredStates = collection.mutable.HashMap.empty[Seq[PossibleMove], collection.mutable.HashMap[PossibleMove, Seq[PossibleMove]]]

  def handle(msg: ResponseMessage, registerUndo: Boolean = false): Unit = {
    if (registerUndo) {
      undoHelper.registerResponse(msg)
    }
    if (gameRules.victoryCondition.check(gameRules, gameState)) {
      gameWon = true
    }
  }

  private[this] def pointless(move: PossibleMove) = move.sourcePile.startsWith("foundation")

  def performMove(): GameMessage = {
    val results = exploredStates.getOrElseUpdate(moves, collection.mutable.HashMap.empty[PossibleMove, Seq[PossibleMove]])
    val unexploredMoves = moves.filter(x => !pointless(x) && !results.contains(x))
    val ret = if (unexploredMoves.isEmpty) {
      if (undosAvailable == 0) {
        throw new IllegalStateException("No undos available, no unexplored moves.")
      } else {
        requests.handle(UN)
        results(PossibleMove(PossibleMove.Type.Undo, "undo")) = moves
        UN
      }
    } else {
      val move = if (rng.nextInt(10) < 7) {
        unexploredMoves.headOption.getOrElse(throw new IllegalStateException())
      } else {
        unexploredMoves(rng.nextInt(unexploredMoves.size))
      }
      val msg = move.toMessage
      requests.handle(msg)
      results(move) = moves
      msg
    }

    moves = moveHelper.possibleMoves()
    undosAvailable = undoHelper.historyQueue.size

    ret
  }

  def getHistory(status: GameHistory.Status) = GameHistory(
    id = gameId,
    rules = rules,
    seed = gameState.seed,
    status = status,
    player = GameSolver.userId,
    cards = gameRules.deckOptions.cardCount,
    moves = moveHelper.getMoveCount,
    undos = undoHelper.undoCount,
    redos = undoHelper.redoCount,
    created = start,
    firstMove = Some(start),
    completed = Some(start)
  )
}
