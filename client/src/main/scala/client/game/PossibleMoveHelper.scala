package client.game

import models._
import models.game.UndoHelper

trait PossibleMoveHelper extends VictoryHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit

  protected def getResult: GameResult

  protected def undoHelper: UndoHelper

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
    (awesomeMoves ++ ret ++ boringMoves).toIndexedSeq
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
}
