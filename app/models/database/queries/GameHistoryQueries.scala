package models.database.queries

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.GameHistory
import models.database.{ Query, FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime
import utils.DateUtils

import scala.collection.mutable.ArrayBuffer

object GameHistoryQueries extends BaseQueries {
  override protected val tableName = "games"
  override protected val columns = Seq("id", "seed", "rules", "status", "accounts", "moves", "undos", "redos", "created", "completed")

  override protected lazy val insertSql = s"insert into $tableName (id, seed, rules, status, accounts, created) values (?, ?, ?, ?, ?::uuid[], ?)"

  case class CreateGameHistory(id: UUID, seed: Int, rules: String, status: String, accounts: Seq[UUID], created: LocalDateTime) extends Statement {
    override val sql = insertSql
    override val values = Seq(id, seed, rules, status, "{ " + accounts.mkString(", ") + " }", DateUtils.toSqlTimestamp(created)): Seq[Any]
  }

  case class UpdateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) extends Statement {
    override val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed"))
    override val values = Seq(status, moves, undos, redos, completed.map(DateUtils.toSqlTimestamp), id): Seq[Any]
  }

  case class SearchGameHistories(q: String, orderBy: String) extends Query[List[GameHistory]] {
    override val sql = getSql("id::character varying like lower(?)", Some("?"))
    override val values = Seq("%" + q + "%", orderBy)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class GetGameHistoriesByAccount(id: UUID, sortBy: String) extends Query[List[GameHistory]] {
    override val sql = getSql("accounts @> array[?]", Some("?"))
    override val values = Seq(id, sortBy)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class GetGameHistory(id: UUID) extends FlatSingleRowQuery[GameHistory] {
    override val sql = getByIdSql
    override val values = Seq(id)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class RemoveGameHistory(id: UUID) extends Statement {
    override val sql = removeByIdSql
    override val values = Seq(id)
  }

  private[this] def fromRow(row: RowData) = {
    val id = UUID.fromString(row("id").asInstanceOf[String])
    val seed = row("seed").asInstanceOf[Int]
    val rules = row("rules").asInstanceOf[String]
    val status = row("status").asInstanceOf[String]
    val accts = row("accounts").asInstanceOf[ArrayBuffer[String]].map(UUID.fromString)
    val moves = row("moves").asInstanceOf[Int]
    val undos = row("undos").asInstanceOf[Int]
    val redos = row("redos").asInstanceOf[Int]
    val created = row("created").asInstanceOf[LocalDateTime]
    val complete = Option(row("completed").asInstanceOf[LocalDateTime])
    GameHistory(id, seed, rules, status, accts, moves, undos, redos, created, complete)
  }
}
