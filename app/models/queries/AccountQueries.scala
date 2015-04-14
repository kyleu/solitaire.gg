package models.queries

import java.util.UUID

import com.simple.jdub._
import models.Account
import org.joda.time.LocalDateTime

object AccountQueries extends BaseQueries {
  override protected val tableName = "accounts"
  override protected val columns = Seq("id", "name", "games_played", "last_game_started", "created")

  case class CreateAccount(name: String) extends Statement {
    val sql = s"insert into $tableName (${columns.mkString(", ")}) values (?, ?, 0, null, now())"
    val values = Seq(UUID.randomUUID, name)
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

  case class IncrementAccountGamesPlayed(id: UUID, started: LocalDateTime) extends Statement {
    val sql = updateSql(Seq("last_game_started"), Some("games_played = games_played + 1"))
    val values = Seq(Some(started), id)
  }

  case class RemoveAccount(id: UUID) extends Statement {
    val sql = removeByIdSql
    val values = Seq(id)
  }

  private[this] def fromRow(row: Row) = for {
    id <- row.uuid("id")
    name <- row.string("name")
    gamesPlayed <- row.int("games_played")
    created <- row.timestamp("created")
  } yield Account(id, name, gamesPlayed, row.timestamp("last_game_started").map(x => new LocalDateTime(x)), new LocalDateTime(created.getTime))
}
