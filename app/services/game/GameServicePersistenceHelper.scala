package services.game

import java.util.UUID

import models._
import models.audit.GameHistory
import models.database.queries.game.{ GameHistoryMoveQueries, GameHistoryCardQueries, GameHistoryQueries }
import org.joda.time.LocalDateTime
import services.database.Database

trait GameServicePersistenceHelper { this: GameService =>
  private[this] var lastStatus = "created"
  private[this] var movesPersisted = 0

  protected[this] def create() = {
    val gh = GameHistory(id, seed, rules, "started", player.userId, 0, 0, 0, started, None)
    Database.execute(GameHistoryQueries.insert(gh))
    val cards = gameState.deck.cards.zipWithIndex.map(card => new GameHistory.Card(card._1.id, id, card._2, card._1.r, card._1.s))
    Database.execute(GameHistoryCardQueries.insertBatch(cards))
  }

  protected[this] def update() = {
    if (getStatus != lastStatus) {
      lastStatus = getStatus
      GameHistoryService.updateGameHistory(id, lastStatus, moveCount, undoHelper.undoCount, undoHelper.redoCount, Some(new LocalDateTime))
    }

    if (movesPersisted < (moveCount - 1)) {
      // This is ok since this particular method is actor-scoped, even if the persist isn't
      val movesToPersist = gameMessages.drop(movesPersisted)
      movesToPersist.zipWithIndex.foreach { gm =>
        val move = toMove(gm._1._1, gm._2, gm._1._2, gm._1._3)
        Database.execute(GameHistoryMoveQueries.insert(move))
        movesPersisted += 1
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
      case ResignGame => ("resign", None, None, None)
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
