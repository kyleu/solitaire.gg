import java.util.UUID

import json.{ BaseSerializers, ResponseMessageSerializers }
import models._
import models.game.GameState
import models.rules.GameRules

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

  protected[this] def registerMove() = {
    moveCount += 1
    if (firstMoveMade.isEmpty) {
      firstMoveMade = Some(System.currentTimeMillis)
    }
    lastMoveMade = Some(System.currentTimeMillis)
    if (!checkWinCondition()) {
      send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size))
    }
  }

  protected def possibleMoves() = {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val awesomeMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val boringMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gs.piles.foreach { source =>
      val sourceBehavior = source.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
      source.cards.zipWithIndex.foreach { c =>
        val cards = source.cards.drop(c._2)
        val remainingCards = source.cards.take(c._2)
        if (source.canDragFrom(cards, gs)) {
          gs.piles.filter(p => p.id != source.id).foreach { target =>
            val targetBehavior = target.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
            if (target.canDragTo(source, cards, gs)) {
              val move = PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
              if (sourceBehavior == "tableau" && targetBehavior == "tableau" && remainingCards.isEmpty && target.cards.isEmpty) {
                boringMoves += move
              } else if (targetBehavior == "foundation" && sourceBehavior != "foundation") {
                awesomeMoves += move
              } else {
                ret += move
              }
            }
          }
        }
        if (source.canSelectCard(c._1, gs)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
      }
      if (source.canSelectPile(gs)) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
    }
    awesomeMoves ++ ret ++ boringMoves

  }

  private[this] def checkWinCondition() = {
    val gr = gameRules.getOrElse(throw new IllegalStateException())
    val gs = gameState.getOrElse(throw new IllegalStateException())
    if (gr.victoryCondition.check(gr, gs)) {
      onWin()
      true
    } else {
      false
    }
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
