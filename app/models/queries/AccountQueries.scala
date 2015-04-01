package models.queries

import java.util.UUID

import com.simple.jdub.{ FlatSingleRowQuery, Statement, Row }
import models.Account
import org.joda.time.LocalDateTime

object AccountQueries {
  private val tableName = "accounts"
  private val columns = "id, name, created"

  case class CreateAccount(name: String) extends Statement {
    val sql = s"insert into $tableName ($columns) values (?, ?, now())"
    val values = Seq(UUID.randomUUID, name)
  }

  case class GetAccount(id: UUID) extends FlatSingleRowQuery[Account] {
    val sql = s"select $columns from $tableName where id = ?"
    val values = Seq(id)
    override def flatMap(row: Row) = Some(Account(row.uuid("id").get, row.string("name").get, new LocalDateTime(row.timestamp("created").get.getTime)))
  }

  case class GetAccountByName(name: String) extends FlatSingleRowQuery[Account] {
    val sql = s"select $columns from $tableName where name = ?"
    val values = Seq(name)
    override def flatMap(row: Row) = Some(Account(row.uuid("id").get, row.string("name").get, new LocalDateTime(row.timestamp("created").get.getTime)))
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
