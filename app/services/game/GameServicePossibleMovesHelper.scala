package services.game

import models.{PossibleMove, PossibleMoves}
import play.api.libs.json.Json

import scala.util.{Failure, Try, Success}

trait GameServicePossibleMovesHelper { this: GameService =>
  protected def handleGetPossibleMoves(player: String) = {
    val moves = possibleMoves(Some(player))
    sendToPlayer(player, moves)

    import utils.ResponseMessageSerializers._
    val json = Json.prettyPrint(Json.toJson(moves))
    log.info(json)
  }

  protected def possibleMoves(player: Option[String]): PossibleMoves = {
    log.info("Generating possible moves for [" + id + "].")
    val ret = possibleMoves() match {
      case Success(moves) =>
        moves
      case Failure(ex) =>
        log.error("Exception [" + ex.getClass.getSimpleName + "] encountered trying to calculate possible moves.", ex)
        PossibleMoves(Nil)
    }
    log.info("Generated [" + ret.moves.size + "] possible moves for [" + id + "].")
    ret
  }

  protected def possibleMoves(): Try[PossibleMoves] = Try {
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
