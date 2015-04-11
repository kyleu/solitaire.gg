package models.queries

import java.util.UUID

import com.simple.jdub._
import models.Account
import org.joda.time.LocalDateTime

object AccountQueries {
  private[this] val tableName = "accounts"
  private[this] val columns = "id, name, created"

  case class CreateAccount(name: String) extends Statement {
    val sql = s"insert into $tableName ($columns) values (?, ?, now())"
    val values = Seq(UUID.randomUUID, name)
  }

  case class SearchAccounts(q: String) extends Query[List[Account]] {
    override val sql = s"select $columns from $tableName where id::character varying like lower(?) or lower(name) like lower(?) order by created desc"
    override val values = Seq("%" + q + "%", "%" + q + "%")
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      for {
        id <- row.uuid("id")
        name <- row.string("name")
        created <- row.timestamp("created")
      } yield Account(id, name, new LocalDateTime(created.getTime))
    }.flatten.toList
  }

  case class GetAccount(id: UUID) extends FlatSingleRowQuery[Account] {
    override val sql = s"select $columns from $tableName where id = ?"
    override val values = Seq(id)
    override def flatMap(row: Row) = for {
      id <- row.uuid("id")
      name <- row.string("name")
      created <- row.timestamp("created")
    } yield Account(id, name, new LocalDateTime(created.getTime))
  }

  case class GetAccountByName(name: String) extends FlatSingleRowQuery[Account] {
    val sql = s"select $columns from $tableName where name = ?"
    val values = Seq(name)
    override def flatMap(row: Row) = for {
      id <- row.uuid("id")
      name <- row.string("name")
      created <- row.timestamp("created")
    } yield Account(id, name, new LocalDateTime(created.getTime))
  }

  case class UpdateAccountName(id: UUID, name: String) extends Statement {
    val sql = s"update $tableName set name = ? where id = ?"
    val values = Seq(name, id)
  }

  case class RemoveAccount(id: UUID) extends Statement {
    val sql = s"delete from $tableName where id = ?"
    val values = Seq(id)
  }
}
