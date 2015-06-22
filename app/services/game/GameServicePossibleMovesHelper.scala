package services.game

import java.util.UUID

import models.{ PossibleMove, PossibleMoves }

import scala.util.{ Failure, Try, Success }

trait GameServicePossibleMovesHelper { this: GameService =>
  protected[this] def handleGetPossibleMoves(player: UUID) = {
    val moves = possibleMoves(Some(player))
    sendToAll("PossibleMoves", PossibleMoves(moves, undoHelper.historyQueue.size, undoHelper.undoneQueue.size))
  }

  protected[this] def possibleMoves(player: Option[UUID]): Seq[PossibleMove] = {
    possibleMoves() match {
      case Success(moves) =>
        moves
      case Failure(ex) =>
        log.error(s"Exception [${ex.getClass.getSimpleName}] encountered trying to calculate possible moves.", ex)
        Nil
    }
  }

  private[this] def possibleMoves(): Try[Seq[PossibleMove]] = Try {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    val boringMoves = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gameState.piles.foreach { source =>
      val sourceBehavior = source.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
      source.cards.zipWithIndex.foreach { c =>
        val cards = source.cards.drop(c._2)
        val remainingCards = source.cards.take(c._2)
        if (source.canDragFrom(cards, gameState)) {
          gameState.piles.filter(p => p.id != source.id).foreach { target =>
            val targetBehavior = target.pileSet.map(_.behavior).getOrElse(throw new IllegalStateException())
            if (target.canDragTo(source, cards, gameState)) {
              val move = PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
              if (
                sourceBehavior == "tableau" && targetBehavior == "tableau" && (
                  //remainingCards.lastOption.exists(_.u) || // Tableau bounce
                  (remainingCards.isEmpty && target.cards.isEmpty) // King bounce
                )
              ) {
                boringMoves += move
              } else {
                ret += move
              }
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
    ret ++ boringMoves
  }
}
