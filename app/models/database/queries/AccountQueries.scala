package models.database.queries

import java.util.UUID

import com.github.mauricio.async.db.RowData
import models.Account
import models.database.{ Query, FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime
import utils.DateUtils

object AccountQueries extends BaseQueries {
  override protected val tableName = "accounts"
  override protected val columns = Seq("id", "name", "role", "games_played", "last_game_started", "created")

  case class CreateAccount(name: String) extends Statement {
    override val sql = insertSql
    override val values = Seq(UUID.randomUUID, name, "player", 0, None, DateUtils.toSqlTimestamp(new LocalDateTime())): Seq[Any]
  }

  case class SearchAccounts(q: String) extends Query[List[Account]] {
    override val sql = getSql("id::character varying like lower(?) or lower(name) like lower(?)", Some("created desc"))
    override val values = Seq("%" + q + "%", "%" + q + "%")
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class GetAccount(id: UUID) extends FlatSingleRowQuery[Account] {
    override val sql = getByIdSql
    override val values = Seq(id)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class GetAccountByName(name: String) extends FlatSingleRowQuery[Account] {
    override val sql = getSql("name = ?")
    override val values = Seq(name)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class UpdateAccountName(id: UUID, name: String) extends Statement {
    override val sql = updateSql(Seq("name"))
    override val values = Seq(name, id)
  }

  case class UpdateAccountRole(id: UUID, role: String) extends Statement {
    override val sql = updateSql(Seq("role"))
    override val values = Seq(role, id)
  }

  case class IncrementAccountGamesPlayed(accountId: UUID, started: LocalDateTime) extends Statement {
    override val sql = s"update $tableName set last_game_started = ?, games_played = games_played + 1 where id = ?"
    override val values = Seq(DateUtils.toSqlTimestamp(started), accountId)
  }

  case class RemoveAccount(id: UUID) extends Statement {
    override val sql = removeByIdSql
    override val values = Seq(id)
  }

  private[this] def fromRow(row: RowData) = {
    val id = UUID.fromString(row("id") match { case s: String => s })
    val name = row("name") match { case s: String => s }
    val role = row("role") match { case s: String => s }
    val gamesPlayed = row("games_played") match { case i: Int => i }
    val lastGameStarted = Option(row("last_game_started") match { case ldt: LocalDateTime => ldt })
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    Account(id, name, role, gamesPlayed, lastGameStarted, created)
  }
}
