package services.database

import models.database.queries.ddl.DdlQueries
import models.database.queries.ddl._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.Logging

object Schema extends Logging {
  val tables = Map(
    "users" -> CreateUserTable,
    "user_profiles" -> CreateProfileTable,
    "password_info" -> CreatePasswordInfoTable,
    "oauth1_info" -> CreateOAuth1InfoTable,
    "oauth2_info" -> CreateOAuth2InfoTable,
    "openid_info" -> CreateOpenIdInfoTable,
    "session_info" -> CreateSessionInfoTable,
    "games" -> CreateGameTable
  )

  def update() = {
    tables.foreach { t =>
      Database.query(DdlQueries.DoesTableExist(t._1)).foreach {
        case false =>
          log.info("Creating missing table [" + t._1 + "].")
          val name = "CreateTable-" + t._1
          Database.raw(name, t._2.sql)
        case true => // no op
      }
    }
  }
}
