package models.ddl

import java.util.UUID

import models.database.{ Row, Statement, SingleRowQuery }

object DdlQueries {
  val adminId = UUID.fromString("11111111-1111-1111-1111-111111111111")

  case class DoesTableExist(tableName: String) extends SingleRowQuery[Boolean] {
    override val sql = "select exists (select * from information_schema.tables WHERE table_name = ?);"
    override val values = tableName :: Nil
    override def map(row: Row) = row.as[Boolean]("exists")
  }

  case object InsertTestUser extends Statement {
    override def sql = s"""insert into users (
      id, username, prefs, profiles, roles, created
    ) values (
      '${services.test.TestService.testUserId}', 'Test User', '{}'::json, '{ }', '{ "user" }', '2015-01-01 00:00:00.000'
    )"""
  }

  case object InsertAdminUser extends Statement {
    override def sql = s"""insert into users (
      id, username, prefs, profiles, roles, created
    ) values (
      '$adminId', 'Kyle', '{}'::json, '{ }', '{ "user", "admin" }', '2015-01-01 00:00:00.000'
    )"""
  }

  case class TruncateTables(tableNames: Seq[String]) extends Statement {
    override val sql = s"truncate ${tableNames.mkString(", ")}"
  }

  def trim(s: String) = s.replaceAll("""[\s]+""", " ").trim
}
