import java.util.UUID

import models._
import models.game.GameState
import models.rules.GameRules

trait SolitaireVictoryHelper extends SolitaireStatisticsHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit
  protected def getResult: GameResult

  protected def onWin(): Unit

  protected var gameId: UUID = _
  protected var gameRules: GameRules = _
  protected var gameState: GameState = _

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
      send(PossibleMoves(possibleMoves(), 0, 0))
    }
  }

  protected def possibleMoves() = {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gameState.piles.foreach { source =>
      source.cards.zipWithIndex.foreach { c =>
        val cards = source.cards.drop(c._2)
        if (source.canDragFrom(cards, gameState)) {
          gameState.piles.filterNot(_.id == source.id).foreach { target =>
            if (target.canDragTo(source, cards, gameState)) {
              ret += PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
            }
          }
        }
        if (source.canSelectCard(c._1, gameState)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
      }
      if (source.canSelectPile(gameState)) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
    }
    ret
  }

  private[this] def checkWinCondition() = if (gameRules.victoryCondition.check(gameRules, gameState)) {
    onWin()
    true
  } else {
    false
  }
}
