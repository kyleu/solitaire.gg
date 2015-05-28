package services.game

import models.GameHistory
import models.database.queries.game.{ GameHistoryCardQueries, GameHistoryQueries }
import org.joda.time.LocalDateTime
import services.database.Database

trait GameServicePersistenceHelper { this: GameService =>
  private[this] var lastStatus = "created"
  private[this] var cardsPersisted = false
  private[this] var movesPersisted = 0

  protected[this] def create() = {
    Database.execute(GameHistoryQueries.CreateGameHistory(id, seed, rules, "started", player.userId, started))
    gameState.deck.cards.zipWithIndex.foreach { card =>
      val c = new GameHistory.Card(card._1.id, id, card._2, card._1.r, card._1.s)
      Database.execute(GameHistoryCardQueries.CreateGameCard(c))
    }
  }

  protected[this] def update() = {
    if(getStatus != lastStatus) {
      lastStatus = getStatus
      GameHistoryService.updateGameHistory(id, lastStatus, moveCount, undoHelper.undoCount, undoHelper.redoCount, Some(new LocalDateTime))
    }

    if(movesPersisted < (moveCount - 1)) {
      // TODO - This won't work. Concurrency from futures screws up transactions.
      val movesToPersist = gameMessages.drop(movesPersisted)
    }
  }
}
