package services.test

import java.util.UUID

import models._
import models.game.{MoveHelper, PossibleMove, RequestMessageHandler, UndoHelper}
import models.rules.GameRulesSet
import models.rules.moves.InitialMoves
import utils.Logging

import scala.util.Random

object GameSolver {
  val moveLimit = 100
  val userId = UUID.fromString("00000000-0000-0000-0000-000000000000")
}

case class GameSolver(rules: String, testSeed: Int, gameSeed: Int) extends Logging {
  val rng = new Random(testSeed)

  var gameWon = false

  val gameRules = GameRulesSet.allByIdWithAliases(rules)
  val gameId = UUID.randomUUID
  val gameState = gameRules.newGame(gameId, gameSeed, rules)

  val moveHelper = new MoveHelper(gameState, () => ())
  val undo = new UndoHelper()

  private[this] val requests = new RequestMessageHandler(GameSolver.userId, gameState, undo, handle, moveHelper.registerMove)

  InitialMoves.performInitialMoves(gameRules, gameState)

  private[this] var moves: Seq[PossibleMove] = moveHelper.possibleMoves()
  private[this] var undosAvailable = undo.historyQueue.size

  private[this] val exploredStates = collection.mutable.HashMap.empty[Seq[PossibleMove], collection.mutable.HashMap[PossibleMove, Seq[PossibleMove]]]

  def handle(msg: ResponseMessage, registerUndo: Boolean = false): Unit = {
    if (registerUndo) {
      undo.registerResponse(msg)
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
        requests.handle(Undo)
        results(PossibleMove(PossibleMove.Type.Undo, "undo")) = moves
        Undo
      }
    } else {
      val move = if (rng.nextInt(10) < 7) {
        unexploredMoves.headOption.getOrElse(throw new IllegalStateException())
      } else {
        unexploredMoves(rng.nextInt(unexploredMoves.size))
      }
      val msg = move.toMessage
      log.info("Sending [" + msg + "].")
      requests.handle(msg)
      results(move) = moves
      msg
    }

    moves = moveHelper.possibleMoves()
    undosAvailable = undo.historyQueue.size

    ret
  }
}
