package services.game

import java.util.UUID

import models.{ PossibleMove, PossibleMoves }

import scala.util.{ Failure, Try, Success }

trait GameServicePossibleMovesHelper { this: GameService =>
  protected[this] def handleGetPossibleMoves(player: UUID) = {
    val moves = possibleMoves(Some(player))
    sendToAll(PossibleMoves(moves, historyQueue.size, undoneQueue.size))
  }

  protected[this] def possibleMoves(player: Option[UUID]): Seq[PossibleMove] = {
    log.info("Generating possible moves for [" + id + "].")
    val ret = possibleMoves() match {
      case Success(moves) =>
        moves
      case Failure(ex) =>
        log.error("Exception [" + ex.getClass.getSimpleName + "] encountered trying to calculate possible moves.", ex)
        Nil
    }
    log.info("Generated [" + ret.size + "] possible moves for [" + id + "].")
    ret
  }

  protected[this] def possibleMoves(): Try[Seq[PossibleMove]] = Try {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gameState.piles.foreach { source =>
      if (source.canSelectPile(gameState)) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
      source.cards.zipWithIndex.foreach { c =>
        if (source.canSelectCard(c._1, gameState)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
        val cards = source.cards.drop(c._2)
        if (source.canDragFrom(cards, gameState)) {
          gameState.piles.filter(p => p.id != source.id).foreach { target =>
            if (target.canDragTo(cards, gameState)) {
              ret += PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
            }
          }
        }
      }
    }
    ret
  }
}
