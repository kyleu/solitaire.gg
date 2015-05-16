package utils

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.user.User
import play.api.Play.current
import play.api.cache.Cache

import scala.concurrent.duration._

object CacheService {
  def cacheUser(user: User) = {
    Cache.set("user-" + user.id, user, 4.hours)
    user
  }

  def getUser(id: UUID) = {
    Cache.getAs[User]("user-" + id)
  }

  def cacheUserForLoginInfo(user: User, loginInfo: LoginInfo) = {
    Cache.set("user-" + loginInfo.providerID + ":" + loginInfo.providerKey, user)
  }

  def getUserByLoginInfo(loginInfo: LoginInfo) = {
    Cache.getAs[User]("user-" + loginInfo.providerID + ":" + loginInfo.providerKey)
  }

  def removeUser(id: UUID) = {
    Cache.remove("user-" + id)
  }

  def cacheSession(session: CookieAuthenticator) = {
    Cache.set(session.id, session, 4.hours)
    session
  }

  def getSession(id: String) = {
    Cache.getAs[CookieAuthenticator](id)
  }

  def removeSession(id: String) = {
    Cache.remove(id)
  }
}
