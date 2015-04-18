package models.queries

import java.util.UUID

import com.simple.jdub._
import models.Account
import org.joda.time.LocalDateTime
import utils.DateUtils

object AccountQueries extends BaseQueries {
  override protected val tableName = "accounts"
  override protected val columns = Seq("id", "name", "role", "games_played", "last_game_started", "created")

  case class CreateAccount(name: String) extends Statement {
    val sql = insertSql
    val values = Seq(UUID.randomUUID, name, "player", 0, None, DateUtils.toSqlTimestamp(new LocalDateTime())): Seq[Any]
  }

  case class SearchAccounts(q: String) extends Query[List[Account]] {
    override val sql = getSql("id::character varying like lower(?) or lower(name) like lower(?)", Some("created desc"))
    override val values = Seq("%" + q + "%", "%" + q + "%")
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).flatten.toList
  }

  case class GetAccount(id: UUID) extends FlatSingleRowQuery[Account] {
    override val sql = getByIdSql
    override val values = Seq(id)
    override def flatMap(row: Row) = fromRow(row)
  }

  case class GetAccountByName(name: String) extends FlatSingleRowQuery[Account] {
    val sql = getSql("name = ?")
    val values = Seq(name)
    override def flatMap(row: Row) = fromRow(row)
  }

  case class UpdateAccountName(id: UUID, name: String) extends Statement {
    val sql = updateSql(Seq("name"))
    val values = Seq(name, id)
  }

  case class UpdateAccountRole(id: UUID, role: String) extends Statement {
    val sql = updateSql(Seq("role"))
    val values = Seq(role, id)
  }

  case class IncrementAccountGamesPlayed(accountId: UUID, started: LocalDateTime) extends Statement {
    val sql = s"update $tableName set last_game_started = ?, games_played = games_played + 1 where id = ?"
    val values = Seq(DateUtils.toSqlTimestamp(started), accountId)
  }

  case class RemoveAccount(id: UUID) extends Statement {
    val sql = removeByIdSql
    val values = Seq(id)
  }

  private[this] def fromRow(row: Row) = for {
    id <- row.uuid("id")
    name <- row.string("name")
    role <- row.string("role")
    gamesPlayed <- row.int("games_played")
    created <- row.timestamp("created")
  } yield {
    Account(id, name, role, gamesPlayed, row.timestamp("last_game_started").map(x => new LocalDateTime(x)), new LocalDateTime(created.getTime))
  }
}
