package services.database

import models.ddl.DdlQueries
import models.ddl._
import utils.FutureUtils.defaultContext
import utils.Logging

import scala.concurrent.Future

object Schema extends Logging {
  val tables = Seq(
    CreateDailyMetricsTable,

    CreateUsersTable,
    CreateUserStatisticsTable,

    CreateUserFeedbackTable,
    CreateUserFeedbackNotesTable,

    CreateGamesTable,
    CreateGameSeedsTable,

    CreateOpensTable,
    CreateInstallsTable
  )

  def update() = tables.foldLeft(Future.successful(Unit)) { (f, t) =>
    f.flatMap { _ =>
      Database.query(DdlQueries.DoesTableExist(t.tableName)).flatMap { exists =>
        if (exists) {
          Future.successful(Unit)
        } else {
          log.info(s"Creating missing table [${t.tableName}].")
          t.statements.foldLeft(Future.successful(Unit)) { (x, y) =>
            x.flatMap { _ =>
              Database.execute(y).map(_ => Unit)
            }
          }
        }
      }
    }
  }

  def wipe() = {
    log.warn("Wiping database schema.")
    val tableNames = tables.reverseMap(_.tableName)
    Database.execute(DdlQueries.TruncateTables(tableNames)).map(_ => tableNames)
  }
}
