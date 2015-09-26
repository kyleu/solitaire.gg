package services.user

import java.util.UUID

import com.github.mauricio.async.db.Connection
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import models.queries.auth._
import models.queries.user.UserQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.auth.UserSearchService
import services.database.Database
import services.history.{ GameHistoryService, RequestHistoryService }
import utils.Logging
import utils.cache.UserCache

import scala.concurrent.Future

object UserService extends Logging {
  def create(currentUser: User, profile: CommonSocialProfile): Future[User] = {
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
      UserCache.cacheUser(user)
      user
    }
  }

  def remove(userId: UUID) = {
    val start = System.currentTimeMillis
    Database.transaction { conn =>
      for {
        games <- GameHistoryService.removeGameHistoriesByUser(userId)
        requests <- RequestHistoryService.removeRequestsByUser(userId, Some(conn))
        profiles <- removeProfiles(userId, Some(conn)).map(_.length)
        stats <- UserStatisticsService.removeStatisticsForUser(userId, Some(conn))
        users <- Database.execute(UserQueries.removeById(Seq(userId)), Some(conn))
      } yield {
        UserCache.removeUser(userId)
        val cardCount = games.map(_._2._2).sum
        val moveCount = games.map(_._2._3).sum
        Map(
          "users" -> users,
          "profiles" -> profiles,
          "requests" -> requests,
          "games" -> games.length,
          "cards" -> cardCount,
          "moves" -> moveCount,
          "timing" -> (System.currentTimeMillis - start).toInt
        )
      }
    }
  }
  private[this] def removeProfiles(userId: UUID, conn: Option[Connection]) = Database.query(ProfileQueries.FindProfilesByUser(userId)).flatMap { profiles =>
    Future.sequence(profiles.map { profile =>
      (profile.loginInfo.providerID match {
        case "facebook" => Database.execute(OAuth2InfoQueries.removeById(Seq(profile.loginInfo.providerID, profile.loginInfo.providerKey)), conn)
        case "google" => Database.execute(OAuth2InfoQueries.removeById(Seq(profile.loginInfo.providerID, profile.loginInfo.providerKey)), conn)
        case "twitter" => Database.execute(OAuth1InfoQueries.removeById(Seq(profile.loginInfo.providerID, profile.loginInfo.providerKey)), conn)
        case p => throw new IllegalArgumentException(s"Unknown provider [$p].")
      }).flatMap { infoCount =>
        Database.execute(ProfileQueries.remove(Seq(profile.loginInfo.providerID, profile.loginInfo.providerKey)), conn).map { i =>
          profile
        }
      }
    })
  }
}
