package services.user

import java.util.UUID

import com.mohiva.play.silhouette.api.AuthInfo
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import models.database.queries.auth.{ ProfileQueries, UserQueries }
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.history.{ RequestHistoryService, GameHistoryService }
import utils.{ CacheService, Logging }

import scala.concurrent.Future

object UserService extends Logging {
  def create[A <: AuthInfo](currentUser: User, profile: CommonSocialProfile): Future[User] = {
    log.info(s"Saving profile [$profile].")
    UserSearchService.retrieve(profile.loginInfo).flatMap {
      case Some(existingUser) =>
        if (existingUser.id == currentUser.id) {
          val u = existingUser.copy(
            profiles = existingUser.profiles.filterNot(_.providerID == profile.loginInfo.providerID) :+ profile.loginInfo
          )
          save(u, update = true)
        } else {
          Future.successful(existingUser)
        }
      case None => // Link to currentUser
        Database.execute(ProfileQueries.insert(profile)).flatMap { x =>
          val u = currentUser.copy(
            profiles = currentUser.profiles.filterNot(_.providerID == profile.loginInfo.providerID) :+ profile.loginInfo
          )
          save(u, update = true)
        }
    }
  }

  def save(user: User, update: Boolean = false): Future[User] = {
    val statement = if (update) {
      log.info(s"Updating user [$user].")
      UserQueries.UpdateUser(user)
    } else {
      log.info(s"Creating new user [$user].")
      UserQueries.insert(user)
    }
    Database.execute(statement).map { i =>
      CacheService.removeUser(user.id)
      user
    }
  }

  def remove(userId: UUID) = {
    for {
      games <- GameHistoryService.removeGameHistoriesByUser(userId)
      requests <- RequestHistoryService.removeRequestsByUser(userId)
      removed <- Database.execute(UserQueries.removeById(Seq(userId))).map(_ == 1)
    } yield {
      CacheService.removeUser(userId)
      val cardCount = games.map(_._2._2).sum
      val moveCount = games.map(_._2._3).sum
      Seq("success" -> removed, "requests" -> requests, "games" -> games.size, "cards" -> cardCount, "moves" -> moveCount)
    }
  }
}
