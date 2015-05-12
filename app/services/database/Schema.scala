package services.database

import models.database.queries.DdlQueries
import models.database.queries.DdlQueries.{ CreateGameTable, CreateAccountTable }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.Logging

object Schema extends Logging {
  val tables = Map("accounts" -> CreateAccountTable, "games" -> CreateGameTable)

  def update() = {
    tables.foreach { t =>
      Database.query(DdlQueries.DoesTableExist(t._1)).foreach {
        case false =>
          log.info("Creating missing table [" + t._1 + "].")
          val name = "CreateTable" + t._1.head.toUpper + t._1.tail
          Database.raw(name, t._2.sql)
        case true => // no op
      }
    }
  }
}
