package services.user

import java.util.UUID
import models.user.{BaseInfo, User}
import play.api.libs.json._
import play.api.libs.concurrent.Execution.Implicits._
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.{IdentityService, AuthInfo}
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import scala.concurrent.Future
import scala.collection.mutable


object UserService extends IdentityService[User] {
  override def retrieve(loginInfo: LoginInfo): Future[Option[User]] = Future.successful {
    UserService.users.find {
      case (id, user) => user.loginInfo == loginInfo || user.socials.map(_.find(li => li == loginInfo)).isDefined
    }.map(_._2)
  }

  def save(user: User): Future[User] = {
    UserService.users += (user.loginInfo.toString -> user)
    Future.successful(user)
  }

  def save[A <: AuthInfo](profile: CommonSocialProfile): Future[User] = {
    retrieve(profile.loginInfo).flatMap {
      case Some(user) => // Update user with profile
        val u = user.copy(
          info = BaseInfo(
            firstName = profile.firstName,
            lastName = profile.lastName,
            fullName = profile.fullName,
            gender = None
          ),
          email = profile.email,
          avatarUrl = profile.avatarURL
        )
        save(u)
      case None => // Insert a new user
        val u = User(
          id = UUID.randomUUID,
          loginInfo = profile.loginInfo,
          username = None,
          info = BaseInfo(
            firstName = profile.firstName,
            lastName = profile.lastName,
            fullName = profile.fullName,
            gender = None
          ),
          email = profile.email,
          avatarUrl = profile.avatarURL
        )
        save(u)
    }
  }

  def link[A <: AuthInfo](user: User, profile: CommonSocialProfile): Future[User] = {
    val s = user.socials.getOrElse(Seq())
    val u = user.copy(socials = Some(s :+ profile.loginInfo))
    save(u)
  }

  val users: mutable.HashMap[String, User] = mutable.HashMap()
}
