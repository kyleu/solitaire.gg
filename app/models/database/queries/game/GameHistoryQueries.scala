package models.database.queries.game

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.GameHistory
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Query, Statement }
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameHistoryQueries extends BaseQueries[GameHistory] {
  override protected val tableName = "games"
  override protected lazy val insertSql = s"insert into $tableName (id, seed, rules, status, player, created) values (?, ?, ?, ?, ?, ?)"
  override protected val columns = Seq("id", "seed", "rules", "status", "player", "moves", "undos", "redos", "created", "completed")
  override protected val searchColumns = Seq("id::text", "seed::text", "rules", "status", "player::text")

  case class CreateGameHistory(id: UUID, seed: Int, rules: String, status: String, player: UUID, created: LocalDateTime) extends Statement {
    override val sql = insertSql
    override val values = Seq[Any](id, seed, rules, status, player, DateUtils.toSqlTimestamp(created))
  }

  case class UpdateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) extends Statement {
    override val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed"))
    override val values = Seq[Any](status, moves, undos, redos, completed.map(DateUtils.toSqlTimestamp), id)
  }

  case class GetGameHistoriesByUser(id: UUID, sortBy: String) extends Query[List[GameHistory]] {
    override val sql = getSql("player = ?", Some("?"))
    override val values = Seq(id, sortBy)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val seed = row("seed") match { case i: Int => i }
    val rules = row("rules") match { case s: String => s }
    val status = row("status") match { case s: String => s }
    val player = row("player") match { case s: String => UUID.fromString(s) }
    val moves = row("moves") match { case i: Int => i }
    val undos = row("undos") match { case i: Int => i }
    val redos = row("redos") match { case i: Int => i }
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    val complete = row("completed") match { case ldt: LocalDateTime => Some(ldt); case _ => None }
    GameHistory(id, seed, rules, status, player, moves, undos, redos, created, complete)
  }
}
