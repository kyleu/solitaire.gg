package services.user

import java.util.UUID
import models.database.queries.auth.UserQueries
import models.user.User
import org.joda.time.LocalDateTime
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.{ IdentityService, AuthInfo }
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
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
    case None => if(loginInfo.providerID == "anonymous") {
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

  def save(user: User): Future[User] = {
    log.info("Creating new user [" + user + "].")
    Database.execute(UserQueries.CreateUser(user)).map { i =>
      Cache.set("user-" + user.id, user, 4.hours)
      user
    }
  }

  def save[A <: AuthInfo](profile: CommonSocialProfile): Future[User] = {
    log.info("Saving profile [" + profile + "].")
    retrieve(profile.loginInfo).flatMap {
      case Some(user) =>
        val u = user.copy(
          email = profile.email,
          avatarUrl = profile.avatarURL,
          firstName = profile.firstName.orElse(user.firstName),
          lastName = profile.lastName.orElse(user.lastName),
          fullName = profile.fullName.orElse(user.fullName),
          gender = user.gender
        )
        save(u)
      case None => // Insert a new user
        val u = User(
          id = UUID.randomUUID,
          loginInfos = Seq(profile.loginInfo),
          username = None,
          email = profile.email,
          avatarUrl = profile.avatarURL,
          firstName = profile.firstName,
          lastName = profile.lastName,
          fullName = profile.fullName,
          gender = None,
          created = new LocalDateTime()
        )
        save(u)
    }
  }

  def link[A <: AuthInfo](user: User, profile: CommonSocialProfile): Future[User] = {
    log.info("Linking profile [" + profile + "] to user [" + user + "].")
    val s = user.loginInfos
    val u = user.copy(loginInfos = s :+ profile.loginInfo)
    save(u)
  }
}
