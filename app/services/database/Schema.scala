package services.database

import models.database.Statement
import models.ddl.DdlQueries
import models.ddl._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.Logging

import scala.concurrent.Future

object Schema extends Logging {
  val tables = Seq(
    CreateRequestsTable,

    CreateAnalyticsEventsTable,

    CreateUserFeedbackTable,
    CreateUserFeedbackNotesTable,

    CreateDailyMetricsTable,

    CreateDataArchiveTable,
    CreateAdHocQueriesTable
  )

  def update() = {
    val tableFuture = tables.foldLeft(Future.successful(Unit)) { (f, t) =>
      f.flatMap { u =>
        Database.query(DdlQueries.DoesTableExist(t.tableName)).flatMap { exists =>
          if (exists) {
            Future.successful(Unit)
          } else {
            log.info(s"Creating missing table [${t.tableName}].")
            Database.execute(t).map(x => Unit)
          }
        }
      }
    }
  }

  def wipe() = {
    log.warn("Wiping database schema.")
    val tableNames = tables.reverseMap(_.tableName)
    Database.execute(DdlQueries.TruncateTables(tableNames)).map(x => tableNames)
  }
}
