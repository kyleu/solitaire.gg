package services.game

import java.util.UUID

import models._
import models.leaderboard.GameSeed
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.leaderboard.GameSeedService
import utils.DateUtils

trait GameServiceCardHelper { this: GameService =>
  protected[this] def handleSelectCard(userId: UUID, cardId: UUID, pileId: String) {
    val card = gameState.cardsById(cardId)
    val pile = gameState.pilesById(pileId)
    if (!pile.cards.contains(card)) {
      log.warn(s"SelectCard for game [$id]: Card [$card] is not part of the [$pileId] pile.")
    }
    if (pile.canSelectCard(card, gameState)) {
      val messages = pile.onSelectCard(card, gameState)
      sendToAll("SelectCard", messages)
    } else {
      log.warn(s"SelectCard called on [$card], which cannot be selected.")
    }
    checkWinCondition()
    if (!gameWon) {
      handleGetPossibleMoves(userId)
    }
  }

  protected[this] def handleSelectPile(userId: UUID, pileId: String) {
    val pile = gameState.pilesById(pileId)
    if (pile.cards.nonEmpty) {
      log.warn(s"SelectPile [$pileId] called on a non-empty deck.")
    }
    val messages = if (pile.canSelectPile(gameState)) {
      pile.onSelectPile(gameState)
    } else {
      log.warn(s"SelectPile called on [$pile], which cannot be selected.")
      Nil
    }
    sendToAll("SelectPile", messages)

    checkWinCondition()
    if (!gameWon) {
      handleGetPossibleMoves(userId)
    }
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
        sendToAll("DragTo", messages)
        checkWinCondition()
        if (!gameWon) {
          handleGetPossibleMoves(userId)
        }
      } else {
        log.warn(s"Cannot drag cards [${cards.map(_.toString).mkString(", ")}] to pile [${targetPile.id}].")
        sendToAll("CardMoveCancelled", CardMoveCancelled(cardIds, source))
      }
    } else {
      log.warn(s"Cannot drag cards [${cards.map(_.toString).mkString(", ")}] from pile [${sourcePile.id}].")
      sendToAll("CardMoveCancelled", CardMoveCancelled(cardIds, source))
    }
  }

  private[this] def checkWinCondition() = if (!gameWon && gameRules.victoryCondition.check(gameState)) {
    gameWon = true
    setStatus("win")

    val completed = new LocalDateTime()
    val elapsed = (DateUtils.toMillis(completed) - DateUtils.toMillis(started)).toInt
    val gs = GameSeed(
      rules = rules,
      seed = seed,
      player = player.userId,
      moves = moveCount,
      elapsed = elapsed,
      completed = completed
    )
    log.debug(s"Processing winning [${gs.rules}] game with seed [${gs.seed}].")
    GameSeedService.register(gs).map { firstForSeed =>
      sendToAll("GameWon", GameWon(id, firstForSeed))
    }

    true
  } else {
    false
  }
}
