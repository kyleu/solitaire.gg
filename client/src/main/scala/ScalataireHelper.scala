import java.util.UUID

import models.game.GameState
import models._
import models.game.variants.GameVariant

trait ScalataireHelper {
  protected[this] def gameId: UUID
  protected[this] def gameVariant: GameVariant
  protected[this] def gameState: GameState
  protected[this] def send(rm: ResponseMessage, registerUndoResponse: Boolean = true)

  protected[this] def possibleMoves() = {
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
          gameState.piles.filterNot(_.id == source.id).foreach { target =>
            if (target.canDragTo(cards, gameState)) {
              ret += PossibleMove("move-cards", cards.map(_.id).toList, source.id, Some(target.id))
            }
          }
        }
      }
    }
    ret
  }

  protected[this] def handleSelectCard(accountId: UUID, cardId: UUID, pileId: String) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException("SelectCard for game [" + gameId + "]: Card [" + card.toString + "] is not part of the [" + pileId + "] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      send(MessageSet(messages))
    }
    if (!checkWinCondition()) {
      send(PossibleMoves(possibleMoves(), 0, 0))
    }
  }

  protected[this] def handleSelectPile(accountId: UUID, pileId: String) {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.length > 0) {
      throw new IllegalStateException("SelectPile [" + pileId + "] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) { pile.onSelectPile(gameState) } else { Nil }
    send(MessageSet(messages))
    if (!checkWinCondition()) { send(PossibleMoves(possibleMoves(), 0, 0)) }
  }

  protected[this] def handleMoveCards(accountId: UUID, cardIds: Seq[UUID], source: String, target: String) {
    val cards = cardIds.map(gameState.cardsById)
    val sourcePile = gameState.pilesById(source)
    val targetPile = gameState.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException("Card [" + c + "] is not a part of source pile [" + sourcePile.id + "].")
      }
    }
    if (sourcePile.canDragFrom(cards, gameState)) {
      if (targetPile.canDragTo(cards, gameState)) {
        val messages = targetPile.onDragTo(sourcePile, cards, gameState)
        send(MessageSet(messages))
        if (!checkWinCondition()) {
          send(PossibleMoves(possibleMoves(), 0, 0))
        }
      } else {
        send(CardMoveCancelled(cardIds, source))
      }
    } else {
      send(CardMoveCancelled(cardIds, source))
    }
  }

  private[this] def checkWinCondition() = {
    if (gameVariant.isWin) {
      send(GameWon(gameId))
      true
    } else {
      false
    }
  }
}
