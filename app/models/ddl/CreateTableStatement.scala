package models.ddl

import models.database.Statement

object CreateTableStatement {
  class St(override val sql: String) extends Statement
}

abstract class CreateTableStatement(val tableName: String) {
  def sql: String
  def statements = DdlFile.split(sql).map(x => new CreateTableStatement.St(x._1))
}
