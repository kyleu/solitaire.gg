package services.user

import java.util.UUID

import com.github.mauricio.async.db.Connection
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

  def create(user: User): Future[User] = {
    log.info(s"Saving user [${user.id}:${user.email}].")
    UserService.getByEmail(user.email).flatMap {
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
      log.info(s"Updating user [${user.id}].")
      UserQueries.UpdateUser(user)
    } else {
      log.info(s"Creating new user [${user.id}].")
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
