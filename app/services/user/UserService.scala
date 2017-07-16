package services.user

import java.util.UUID

import models.queries.user.UserQueries
import models.settings.Settings
import models.user.User
import util.FutureUtils.defaultContext
import services.database.Database
import services.history.GameHistoryService
import util.Logging

import scala.concurrent.Future

object UserService extends Logging {
  def newUser() = save(User(id = UUID.randomUUID))

  def getById(id: UUID) = Database.query(UserQueries.getById(id))
  def getByIds(ids: Seq[UUID]) = Database.query(UserQueries.getByIds(ids))

  def getByEmail(email: String) = Database.query(UserQueries.GetByEmail(email))

  def getAll(limit: Option[Int], offset: Option[Int]) = Database.query(UserQueries.getAll(limit, offset))

  def search(q: String, limit: Option[Int], offset: Option[Int]) = try {
    getById(UUID.fromString(q)).map(_.toSeq)
  } catch {
    case _: NumberFormatException => Database.query(UserQueries.search(q, "id desc", limit, offset))
  }

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

  def remove(userId: UUID) = Database.transaction { conn =>
    GameHistoryService.removeGameHistoriesByUser(userId).flatMap { _ =>
      UserStatisticsService.removeStatisticsForUser(userId, Some(conn)).flatMap { _ =>
        Database.execute(UserQueries.removeById(Seq(userId)), Some(conn))
      }
    }
  }
}
