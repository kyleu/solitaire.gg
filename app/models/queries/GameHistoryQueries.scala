package models.queries

import java.util.UUID

import com.simple.jdub._
import models.GameHistory
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameHistoryQueries extends BaseQueries {
  override protected val tableName = "games"
  override protected val columns = Seq("id", "seed", "variant", "status", "accounts", "moves", "undos", "redos", "created", "completed")

  override protected lazy val insertSql = s"insert into $tableName (id, seed, variant, status, accounts) values (?, ?, ?, ?, ?::uuid[])"

  case class CreateGameHistory(id: UUID, seed: Int, variant: String, status: String, accounts: Seq[UUID]) extends Statement {
    val sql = insertSql
    val values = Seq(id, seed, variant, status, "{ " + accounts.mkString(", ") + " }"): Seq[Any]
  }

  case class UpdateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) extends Statement {
    val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed"))
    val values = Seq(status, moves, undos, redos, completed.map(DateUtils.toSqlTimestamp), id): Seq[Any]
  }

  case class SearchGameHistories(q: String) extends Query[List[GameHistory]] {
    override val sql = getSql("id::character varying like lower(?)", Some("created desc"))
    override val values = Seq("%" + q + "%")
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).flatten.toList
  }

  case class GetGameHistory(id: UUID) extends FlatSingleRowQuery[GameHistory] {
    override val sql = getByIdSql
    override val values = Seq(id)
    override def flatMap(row: Row) = fromRow(row)
  }

  case class RemoveGameHistory(id: UUID) extends Statement {
    val sql = removeByIdSql
    val values = Seq(id)
  }

  private[this] def fromRow(row: Row) = for {
    id <- row.uuid("id")
    seed <- row.int("seed")
    variant <- row.string("variant")
    status <- row.string("status")
    accounts <- row.sqlArray("accounts")
    moves <- row.int("moves")
    undos <- row.int("undos")
    redos <- row.int("redos")
    created <- row.timestamp("created").map(x => new LocalDateTime(x.getTime))
  } yield GameHistory(
    id, seed, variant,
    status, accounts.getArray.asInstanceOf[Array[UUID]],
    moves, undos, redos,
    created, row.timestamp("completed").map(x => new LocalDateTime(x.getTime))
  )
}
