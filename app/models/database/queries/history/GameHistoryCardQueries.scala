package models.database.queries.history

import java.util.UUID

import models.database.queries.BaseQueries
import models.database.{ Query, Row, Statement }
import models.game.{ Rank, Suit }
import models.history.GameHistory
import models.history.GameHistory.Card

object GameHistoryCardQueries extends BaseQueries[GameHistory.Card] {
  override protected val tableName = "game_cards"
  override protected val columns = Seq("card_id", "game_id", "sort_order", "rank", "suit")
  override protected val searchColumns = Seq("card_id::text", "game_id::text")

  val insertBatch = InsertBatch

  case class GetGameCardsByGame(id: UUID) extends Query[List[GameHistory.Card]] {
    override val sql = getSql(Some("game_id = ?"), Some("sort_order"))
    override val values = Seq(id)
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  case class RemoveGameCardsByGame(gameId: UUID) extends Statement {
    override val sql = s"delete from $tableName where game_id = ?"
    override val values = Seq(gameId)
  }

  override protected def fromRow(row: Row) = {
    val id = UUID.fromString(row.as[String]("id"))
    val gameId = UUID.fromString(row.as[String]("game_id"))
    val order = row.as[Int]("sort_order")
    val rank = Rank.allByChar(row.as[String]("rank").headOption.getOrElse(throw new IllegalStateException()))
    val suit = Suit.fromChar(row.as[String]("suit").headOption.getOrElse(throw new IllegalStateException()))
    GameHistory.Card(id, gameId, order, rank, suit)
  }

  override protected def toDataSeq(c: Card) = Seq[Any](c.id, c.gameId, c.sortOrder, c.rank.toChar, c.suit.toChar)
}
