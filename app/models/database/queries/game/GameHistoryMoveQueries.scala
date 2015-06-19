package models.database.queries.game

import java.util.UUID

import models.audit.GameHistory
import models.database.queries.BaseQueries
import models.database.{ Row, Query, Statement }
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameHistoryMoveQueries extends BaseQueries[GameHistory.Move] {
  override protected val tableName = "game_moves"
  override protected val columns = Seq("game_id", "sequence", "player_id", "move", "cards", "src", "tgt", "occurred")
  override protected val searchColumns = Seq("game_id::text", "player_id::text", "move")

  val insert = Insert

  case class GetGameMovesByGame(id: UUID) extends Query[List[GameHistory.Move]] {
    override val sql = getSql(whereClause = Some("game_id = ?"), orderBy = Some("sequence"))
    override val values = Seq(id)
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  case class RemoveGameMovesByGame(gameId: UUID) extends Statement {
    override val sql = s"delete from $tableName where game_id = ?"
    override val values = Seq(gameId)
  }

  override protected def fromRow(row: Row) = {
    val gameId = UUID.fromString(row.as[String]("game_id"))
    val sequence = row.as[Int]("sequence")
    val playerId = UUID.fromString(row.as[String]("player_id"))
    val moveType = row.as[String]("move")
    val cards = row.as[Array[UUID]]("cards")
    val src = row.asOpt[String]("src")
    val tgt = row.asOpt[String]("tgt")
    val occurred = row.as[LocalDateTime]("occurred")
    GameHistory.Move(gameId, sequence, playerId, moveType, Some(cards), src, tgt, occurred)
  }

  protected def toDataSeq(m: GameHistory.Move) = {
    Seq[Any](m.gameId, m.sequence, m.playerId, m.moveType, m.cards.toArray, m.src, m.tgt, DateUtils.toSqlTimestamp(m.occurred))
  }
}
