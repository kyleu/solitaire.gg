package services.user

import java.util.UUID
import models.database.queries.auth.UserQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.{IdentityService, AuthInfo}
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import services.database.Database
import utils.Logging
import scala.concurrent.Future


object UserService extends IdentityService[User] with Logging {
  def retrieve(id: UUID): Future[Option[User]] = Database.query(UserQueries.Find(id))
  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = Database.query(UserQueries.FindByLoginInfo(loginInfo))

  def save(user: User): Future[User] = {
    log.info("Creating new user [" + user + "].")
    Database.execute(UserQueries.Create(user)).map(i => user)
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
          gender = None
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
