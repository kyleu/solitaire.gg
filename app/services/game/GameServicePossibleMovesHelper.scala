package services.game

import models.{ServerError, PossibleMove, PossibleMoves}

import scala.util.{Failure, Try, Success}

trait GameServicePossibleMovesHelper { this: GameService =>
  protected def handleGetPossibleMoves(player: String) = possibleMoves() match {
    case Success(moves) =>
      sendToPlayer(player, moves)
    case Failure(ex) =>
      log.warn("Exception encountered processing possible moves.", ex)
      sendToAll(ServerError("PossibleMovesException", ex.toString))
  }

  private def possibleMoves() = Try {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gameState.piles.foreach { source =>
      if(source.canSelectPile(gameState)) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
      source.cards.zipWithIndex.foreach { c =>
        if(source.canSelectCard(c._1, gameState)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
        if(source.canDragFrom(Seq(c._1), gameState)) {
          gameState.piles.filter(_ != source).foreach { target =>
            if(target.canDragTo(Seq(c._1), gameState)) {
              ret += PossibleMove("move-cards", Seq(c._1.id), source.id, Some(target.id))
            }
          }
        }
        if(c._2 > 0) {
          if(source.canDragFrom(source.cards.take(c._2), gameState)) {
            gameState.piles.filter(_ != source).foreach { target =>
              if(target.canDragTo(Seq(c._1), gameState)) {
                ret += PossibleMove("move-cards", Seq(c._1.id), source.id, Some(target.id))
              }
            }
          }
        }
      }
    }
    PossibleMoves(ret)
  }
}
