package services.game

import java.util.UUID

import models._
import models.database.queries.history.{ GameHistoryQueries, GameHistoryMoveQueries, GameHistoryCardQueries }
import models.card.Card
import models.history.GameHistory
import org.joda.time.LocalDateTime
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.GameHistoryService
import utils.DateUtils

import scala.concurrent.Future

trait GameServicePersistenceHelper { this: GameService =>
  private[this] var lastStatus = "created"
  private[this] var cardsPersisted = false
  private[this] var originalCards = Seq.empty[Card]
  private[this] var movesPersisted = 0

  protected[this] def create() = if (testGame) {
    Future.successful(true)
  } else {
    originalCards = gameState.deck.cards
    val gh = GameHistory(id, seed, rules, "started", player.userId, originalCards.size, 0, 0, 0, started, None)
    GameHistoryService.insert(gh)
  }

  protected[this] def update() = {
    if (testGame) {
      Future.successful(true)
    } else {
      if (getStatus != lastStatus) {
        lastStatus = getStatus
        GameHistoryService.updateGameHistory(id, lastStatus, moveCount, undoHelper.undoCount, undoHelper.redoCount, Some(DateUtils.now))
      }

      if (movesPersisted < moveCount) {
        val persist = if (cardsPersisted) {
          Future.successful(false)
        } else if (testGame) {
          cardsPersisted = true
          Future.successful(true)
        } else {
          val cards = originalCards.zipWithIndex.map { card =>
            new GameHistory.Card(card._1.id, id, card._2, card._1.r, card._1.s)
          }
          cardsPersisted = true
          Database.execute(GameHistoryCardQueries.insertBatch(cards)).map(_ == cards.size)
        }

        persist.map { ok =>
          // This is ok since this particular method is actor-scoped, even if the persist isn't
          val movesToPersist = gameMessages.drop(movesPersisted)

          movesToPersist.zipWithIndex.foreach { gm =>
            val move = toMove(gm._1._1, gm._2, gm._1._2, gm._1._3)
            Database.execute(GameHistoryMoveQueries.insert(move))
            movesPersisted += 1
          }
        }
      }
    }
  }

  private[this] def toMove(gm: GameMessage, idx: Int, playerId: UUID, occurred: LocalDateTime) = {
    val params = gm match {
      case GetPossibleMoves => ("get-possible", None, None, None)

      case sc: SelectCard => ("select-card", Some(Seq(sc.card)), Some(sc.pile), None)
      case sp: SelectPile => ("select-pile", None, Some(sp.pile), None)
      case mc: MoveCards => ("move", Some(mc.cards), Some(mc.src), Some(mc.tgt))

      case Undo => ("undo", None, None, None)
      case Redo => ("redo", None, None, None)
    }
    GameHistory.Move(
      gameId = id,
      sequence = movesPersisted,
      playerId = playerId,
      moveType = params._1,
      cards = params._2,
      src = params._3,
      tgt = params._4,
      occurred = occurred
    )
  }
}
