package services.user

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.{ AuthInfo, IdentityService }
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import models.database.queries.auth.UserQueries
import models.user.User
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Logging

import scala.concurrent.Future
import scala.concurrent.duration._

object UserService extends IdentityService[User] with Logging {
  def retrieve(id: UUID): Future[Option[User]] = Cache.getAs[User]("user-" + id.toString) match {
    case Some(u) => Future.successful(Some(u))
    case None => Database.query(UserQueries.FindUser(id)).map {
      case Some(dbUser) =>
        Cache.set("user-" + dbUser.id, dbUser, 4.hours)
        Some(dbUser)
      case None => None
    }
  }

  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = Cache.getAs[User]("user-" + loginInfo.providerID + ":" + loginInfo.providerKey) match {
    case Some(u) => Future.successful(Some(u))
    case None => if (loginInfo.providerID == "anonymous") {
      Database.query(UserQueries.FindUser(UUID.fromString(loginInfo.providerKey))).map {
        case Some(dbUser) =>
          Cache.set("user-" + loginInfo.providerID + ":" + loginInfo.providerKey, dbUser, 4.hours)
          Some(dbUser)
        case None => None
      }
    } else {
      Database.query(UserQueries.FindUserByLoginInfo(loginInfo)).map {
        case Some(dbUser) =>
          Cache.set("user-" + loginInfo.providerID + ":" + loginInfo.providerKey, dbUser, 4.hours)
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
      Cache.set("user-" + user.id, user, 4.hours)
      user
    }
  }

  def link[A <: AuthInfo](currentUser: User, profile: CommonSocialProfile): Future[User] = {
    log.info("Saving profile [" + profile + "].")
    retrieve(profile.loginInfo).flatMap {
      case Some(existingUser) =>
        val u = existingUser.copy(
          email = profile.email.orElse(existingUser.email),
          avatarUrl = profile.avatarURL.orElse(existingUser.avatarUrl),
          firstName = profile.firstName.orElse(existingUser.firstName),
          lastName = profile.lastName.orElse(existingUser.lastName),
          fullName = profile.fullName.orElse(existingUser.fullName),
          gender = existingUser.gender
        )
        save(u, update = true)
      case None => // Link to currentUser
        val u = currentUser.copy(
          email = profile.email.orElse(currentUser.email),
          avatarUrl = profile.avatarURL.orElse(currentUser.avatarUrl),
          firstName = profile.firstName.orElse(currentUser.firstName),
          lastName = profile.lastName.orElse(currentUser.lastName),
          fullName = profile.fullName.orElse(currentUser.fullName),
          gender = currentUser.gender
        )
        save(u, update = true)
    }
  }
}
