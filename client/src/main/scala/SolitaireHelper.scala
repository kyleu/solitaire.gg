import java.util.UUID

import models._
import models.game.GameState
import models.game.rules.GameRules

trait SolitaireHelper {
  protected def send(rm: ResponseMessage, registerUndoResponse: Boolean = true): Unit

  protected var gameId: UUID = _
  protected var gameRules: GameRules = _
  protected var gameState: GameState = _

  protected[this] def possibleMoves() = {
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

  protected[this] def handleSelectCard(userId: UUID, cardId: UUID, pileId: String) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      throw new IllegalStateException(s"SelectCard for game [$gameId]: Card [${card.toString}] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      send(MessageSet(messages))
    }
    if (!checkWinCondition()) {
      send(PossibleMoves(possibleMoves(), 0, 0))
    }
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String) {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      throw new IllegalStateException(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) { pile.onSelectPile(gameState) } else { Nil }
    send(MessageSet(messages))
    if (!checkWinCondition()) { send(PossibleMoves(possibleMoves(), 0, 0)) }
  }

  protected[this] def handleMoveCards(userId: UUID, cardIds: Seq[UUID], source: String, target: String) {
    val cards = cardIds.map(gameState.cardsById)
    val sourcePile = gameState.pilesById(source)
    val targetPile = gameState.pilesById(target)
    for (c <- cards) {
      if (!sourcePile.cards.contains(c)) {
        throw new IllegalArgumentException(s"Card [$c] is not a part of source pile [${sourcePile.id}].")
      }
    }
    if (sourcePile.canDragFrom(cards, gameState)) {
      if (targetPile.canDragTo(sourcePile, cards, gameState)) {
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
    if (gameRules.victoryCondition.check(gameState)) {
      send(GameWon(gameId))
      true
    } else {
      false
    }
  }
}
