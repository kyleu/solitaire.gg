package services.user

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.{ AuthInfo, IdentityService }
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import models.database.queries.auth.{ ProfileQueries, UserQueries }
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.{ CacheService, Logging }

import scala.concurrent.Future

object UserService extends IdentityService[User] with Logging {
  def create[A <: AuthInfo](currentUser: User, profile: CommonSocialProfile): Future[User] = {
    log.info("Saving profile [" + profile + "].")
    retrieve(profile.loginInfo).flatMap {
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
        Database.execute(ProfileQueries.CreateProfile(profile)).flatMap { x =>
          val u = currentUser.copy(
            profiles = currentUser.profiles.filterNot(_.providerID == profile.loginInfo.providerID) :+ profile.loginInfo
          )
          save(u, update = true)
        }
    }
  }

  def retrieve(id: UUID): Future[Option[User]] = CacheService.getUser(id) match {
    case Some(u) => Future.successful(Some(u))
    case None => Database.query(UserQueries.FindUser(id)).map {
      case Some(dbUser) =>
        CacheService.cacheUser(dbUser)
        Some(dbUser)
      case None => None
    }
  }

  def retrieve(username: String): Future[Option[User]] = Database.query(UserQueries.FindUserByUsername(username))

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = CacheService.getUserByLoginInfo(loginInfo) match {
    case Some(u) => Future.successful(Some(u))
    case None => if (loginInfo.providerID == "anonymous") {
      Database.query(UserQueries.FindUser(UUID.fromString(loginInfo.providerKey))).map {
        case Some(dbUser) =>
          if (dbUser.profiles.nonEmpty) {
            log.warn("Attempt to authenticate as anonymous for user with profiles [" + dbUser.profiles + "].")
            None
          } else {
            CacheService.cacheUserForLoginInfo(dbUser, loginInfo)
            Some(dbUser)
          }
        case None => None
      }
    } else {
      Database.query(UserQueries.FindUserByProfile(loginInfo)).map {
        case Some(dbUser) =>
          CacheService.cacheUserForLoginInfo(dbUser, loginInfo)
          Some(dbUser)
        case None => None
      }
    }
  }

  def save(user: User, update: Boolean = false): Future[User] = {
    val statement = if (update) {
      log.info("Updating user [" + user + "].")
      UserQueries.UpdateUser(user)
    } else {
      log.info("Creating new user [" + user + "].")
      UserQueries.CreateUser(user)
    }
    Database.execute(statement).map { i =>
      CacheService.removeUser(user.id)
      user
    }
  }
}
