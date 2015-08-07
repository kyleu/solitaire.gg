package services.sandbox

import java.util.UUID
import models.database.Statement
import models.database.queries.history.GameHistoryQueries
import models.rules.GameRulesSet
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import scala.concurrent._
import scala.concurrent.duration._

object Scratchpad {
  def run() = {
    val gamesFuture = Database.query(GameHistoryQueries.search("", "created", None, None))

    gamesFuture.flatMap { games =>
      games.map { game =>
        val rules = GameRulesSet.allByIdWithAliases(game.rules)
        val state = rules.newGame(UUID.randomUUID(), 0, rules.id)
        val cardCount = state.deck.cards.size
        val dbFuture = Database.execute(UpdateCardCount(game.id, cardCount))
        Await.result(dbFuture, 10.seconds)
      }
      Future.successful("Ok: [" + games.length + "] games updated.")
    }
  }

  case class UpdateCardCount(id: UUID, cardCount: Int) extends Statement {
    override def sql: String = "update games set cards = ? where id = ?"
    override def values = Seq(cardCount, id)
  }
}
