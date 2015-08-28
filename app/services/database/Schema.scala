package services.database

import models.database.Statement
import models.ddl.DdlQueries
import models.ddl._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.Logging

import scala.concurrent.Future

object Schema extends Logging {
  val tables = Seq(
    "users" -> CreateUsersTable,
    "user_statistics" -> CreateUserStatisticsTable,

    "user_profiles" -> CreateUserProfilesTable,
    "password_info" -> CreatePasswordInfoTable,
    "oauth1_info" -> CreateOAuth1InfoTable,
    "oauth2_info" -> CreateOAuth2InfoTable,
    "openid_info" -> CreateOpenIdInfoTable,
    "session_info" -> CreateSessionInfoTable,

    "requests" -> CreateRequestsTable,
    "client_trace" -> CreateClientTraceTable,

    "game_seeds" -> CreateGameSeedsTable,

    "games" -> CreateGamesTable,
    "game_cards" -> CreateGameCardsTable,
    "game_moves" -> CreateGameMovesTable,

    "user_feedback" -> CreateUserFeedbackTable,
    "user_feedback_notes" -> CreateUserFeedbackNotesTable,

    "daily_metrics" -> CreateDailyMetricsTable,

    "data_archive" -> CreateDataArchiveTable,
    "adhoc_queries" -> CreateAdHocQueriesTable
  )

  def update() = {
    val tableFuture = tables.foldLeft(Future.successful(Unit)) { (f, t) =>
      f.flatMap { u =>
        Database.query(DdlQueries.DoesTableExist(t._1)).flatMap { exists =>
          if (exists) {
            Future.successful(Unit)
          } else {
            log.info(s"Creating missing table [${t._1}].")
            val name = s"CreateTable-${t._1}"
            Database.raw(name, t._2.sql).map(x => Unit)
          }
        }
      }
    }

    tableFuture.flatMap { ok =>
      createUser(Database.query(DdlQueries.DoesTestUserExist), DdlQueries.InsertTestUser).flatMap { stillOk =>
        createUser(Database.query(DdlQueries.DoesAdminUserExist), DdlQueries.InsertAdminUser)
      }
    }
  }

  def wipe() = {
    log.warn("Wiping database schema.")
    val tableNames = tables.reverse.map(_._1)
    Database.execute(DdlQueries.TruncateTables(tableNames)).map(x => tableNames)
  }

  private[this] def createUser(q: Future[Boolean], insert: Statement) = q.flatMap { exists =>
    if (exists) {
      Future.successful(Unit)
    } else {
      log.info(s"Creating user [${insert.getClass.getSimpleName}].")
      Database.execute(insert).map(x => x == 1)
    }
  }
}
