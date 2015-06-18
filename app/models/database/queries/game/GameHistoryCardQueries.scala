package models.database.queries.game

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.audit.GameHistory
import models.audit.GameHistory.Card
import models.database.queries.BaseQueries
import models.database.{ Query, Statement }
import models.game.{ Rank, Suit }

object GameHistoryCardQueries extends BaseQueries[GameHistory.Card] {
  override protected val tableName = "game_cards"
  override protected val columns = Seq("card_id", "game_id", "sort_order", "rank", "suit")
  override protected val searchColumns = Seq("card_id::text", "game_id::text")

  val insertBatch = InsertBatch

  case class GetGameCardsByGame(id: UUID) extends Query[List[GameHistory.Card]] {
    override val sql = getSql(Some("game_id = ?"), Some("sort_order"))
    override val values = Seq(id)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class RemoveGameMovesByGame(gameId: UUID) extends Statement {
    override val sql = s"delete from $tableName where game_id = ?"
    override val values = Seq(gameId)
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val gameId = row("game_id") match { case s: String => UUID.fromString(s) }
    val order = row("sort_order") match { case i: Int => i }
    val rank = row("rank") match { case s: String => Rank.allByChar(s.headOption.getOrElse(throw new IllegalStateException())) }
    val suit = row("suit") match { case s: String => Suit.fromChar(s.headOption.getOrElse(throw new IllegalStateException())) }
    GameHistory.Card(id, gameId, order, rank, suit)
  }

  override protected def toDataSeq(c: Card) = Seq[Any](c.id, c.gameId, c.sortOrder, c.rank.toChar, c.suit.toChar)
}
