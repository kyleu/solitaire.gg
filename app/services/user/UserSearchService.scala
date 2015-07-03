package services.user

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.services.IdentityService
import models.database.queries.auth.UserQueries
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import utils.Logging
import utils.cache.CacheService

import scala.concurrent.Future

object UserSearchService extends IdentityService[User] with Logging {
  def retrieve(id: UUID): Future[Option[User]] = CacheService.getUser(id) match {
    case Some(u) => Future.successful(Some(u))
    case None => Database.query(UserQueries.getById(Seq(id))).map {
      case Some(dbUser) =>
        CacheService.cacheUser(dbUser)
        Some(dbUser)
      case None => None
    }
  }

  def retrieve(username: String): Future[Option[User]] = Database.query(UserQueries.FindUserByUsername(username))

  override def retrieve(loginInfo: LoginInfo) = CacheService.getUserByLoginInfo(loginInfo) match {
    case Some(u) => Future.successful(Some(u))
    case None => if (loginInfo.providerID == "anonymous") {
      Database.query(UserQueries.getById(Seq(UUID.fromString(loginInfo.providerKey)))).map {
        case Some(dbUser) =>
          if (dbUser.profiles.nonEmpty) {
            log.warn(s"Attempt to authenticate as anonymous for user with profiles [${dbUser.profiles}].")
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
}
