package services.user

import java.util.UUID

import models.cache.UserCache
import models.queries.user.UserQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Logging

import scala.concurrent.Future

object UserService extends Logging {
  def getById(id: UUID): Future[Option[User]] = UserCache.get(id) match {
    case Some(u) => Future.successful(Some(u))
    case None => Database.query(UserQueries.getById(id)).map {
      case Some(dbUser) =>
        UserCache.set(dbUser)
        Some(dbUser)
      case None => None
    }
  }

  def getByEmail(email: String) = Database.query(UserQueries.GetByEmail(email))

  def create(user: User): Future[User] = {
    log.info(s"Saving user [${user.id}:${user.email}].")
    Future.successful(user)
  }

  def save(user: User, update: Boolean = false): Future[User] = {
    val statement = if (update) {
      log.info(s"Updating user [${user.id}].")
      UserQueries.UpdateUser(user)
    } else {
      log.info(s"Creating new user [${user.id}].")
      UserQueries.insert(user)
    }
    Database.execute(statement).map { i =>
      UserCache.set(user)
      user
    }
  }
}
