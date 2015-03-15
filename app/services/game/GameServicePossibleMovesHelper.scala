package services.game

import models.{PossibleMove, PossibleMoves}
import play.api.libs.json.Json
import utils.GameSerializers._

trait GameServicePossibleMovesHelper { this: GameService =>
  protected def handleGetPossibleMoves(player: String) = {
    sendToAll(possibleMoves())
  }

  private def possibleMoves() = {
    val ret = collection.mutable.ArrayBuffer.empty[PossibleMove]
    gameState.piles.foreach { source =>
      if(source.canSelectPile) {
        ret += PossibleMove("select-pile", Nil, source.id)
      }
      source.cards.zipWithIndex.foreach { c =>
        if(source.canSelectCard(c._1)) {
          ret += PossibleMove("select-card", Seq(c._1.id), source.id)
        }
        if(source.canDragFrom(Seq(c._1))) {
          gameState.piles.filter(_ != source).foreach { target =>
            if(target.canDragTo(Seq(c._1))) {
              ret += PossibleMove("move-cards", Seq(c._1.id), source.id, Some(target.id))
            }
          }
        }
      }
    }
    PossibleMoves(ret)
  }

  private def logPossibleMoves = {
    Json.prettyPrint(Json.toJson(gameState))
  }
}
