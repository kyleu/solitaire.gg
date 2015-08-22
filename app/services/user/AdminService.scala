package services.user

import models.queries.user.UserQueries
import models.user.{ Role, User }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.cache.UserCache

import scala.concurrent.Future

object AdminService {
  def enableAdmin(user: User) = {
    Database.query(UserQueries.CountAdmins).flatMap { adminCount =>
      if (adminCount == 0) {
        Database.execute(UserQueries.AddRole(user.id, Role.Admin)).map { x =>
          UserCache.removeUser(user.id)
          "OK"
        }
      } else {
        Future.successful(s"Forbidden. $adminCount admins already exist.")
      }
    }
  }
}
