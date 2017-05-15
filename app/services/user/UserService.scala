package services.user

import java.util.UUID

import models.queries.user.UserQueries
import models.settings.Settings
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Logging

import scala.concurrent.Future

object UserService extends Logging {
  def newUser() = save(User(id = UUID.randomUUID))

  def getById(id: UUID): Future[Option[User]] = Database.query(UserQueries.getById(id))

  def getByEmail(email: String) = Database.query(UserQueries.GetByEmail(email))

  def save(user: User, update: Boolean = false): Future[User] = {
    val statement = if (update) {
      log.info(s"Updating user [${user.id}].")
      UserQueries.UpdateUser(user)
    } else {
      log.info(s"Creating new user [${user.id}].")
      UserQueries.insert(user)
    }
    Database.execute(statement).map { _ =>
      user
    }
  }

  def saveSettings(userId: UUID, settings: Settings) = Database.execute(UserQueries.SetSettings(userId, settings))
}
