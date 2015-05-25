package models.database.queries.game

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.GameHistory
import models.database.queries.BaseQueries
import models.database.{ Query, Statement }
import models.game.{ Suit, Rank }

object GameHistoryCardQueries extends BaseQueries {
  override protected val tableName = "game_cards"
  override protected val columns = Seq("id", "game_id", "sort_order", "rank", "suit")

  case class CreateGameMove(card: GameHistory.Card) extends Statement {
    override val sql = insertSql
    override val values = Seq[Any](card.id, card.gameId, card.sortOrder, card.rank.toChar, card.suit.toChar)
  }

  case class GetGameCardsByGame(id: UUID) extends Query[List[GameHistory.Card]] {
    override val sql = getSql("game_id = ?", Some("sort_order"))
    override val values = Seq(id)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class RemoveGameMovesByGame(gameId: UUID) extends Statement {
    override val sql = s"delete from $tableName where game_id = ?"
    override val values = Seq(gameId)
  }

  private[this] def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val gameId = row("game_id") match { case s: String => UUID.fromString(s) }
    val order = row("sort_order") match { case i: Int => i }
    val rank = row("rank") match { case s: String => Rank.allByChar(s.headOption.getOrElse(throw new IllegalStateException())) }
    val suit = row("suit") match { case s: String => Suit.fromChar(s.headOption.getOrElse(throw new IllegalStateException())) }
    GameHistory.Card(id, gameId, order, rank, suit)
  }
}
