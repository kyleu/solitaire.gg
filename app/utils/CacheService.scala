package utils

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.user.User
import play.api.Play.current
import play.api.cache.{ EhCachePlugin, Cache }

import scala.concurrent.duration._

object CacheService {
  private val ehCache = current.plugin[EhCachePlugin] match {
    case Some(plugin) => plugin.cache
    case None => throw new Exception("There is no [EhCache] plugin registered.")
  }

  def getUser(id: UUID) = {
    Cache.getAs[User]("user-" + id)
  }

  def cacheUser(user: User) = {
    Cache.set("user-" + user.id, user, 4.hours)
    user
  }

  def cacheUserForLoginInfo(user: User, loginInfo: LoginInfo) = {
    Cache.set("user-" + user.id, user, 4.hours)
    Cache.set("user-" + loginInfo.providerID + ":" + loginInfo.providerKey, user, 4.hours)
  }

  def getUserByLoginInfo(loginInfo: LoginInfo) = {
    Cache.getAs[User]("user-" + loginInfo.providerID + ":" + loginInfo.providerKey)
  }

  def removeUser(id: UUID) = {
    Cache.getAs[User]("user-" + id).foreach { u =>
      for (p <- u.profiles) {
        Cache.remove("user-" + p.providerID + ":" + p.providerKey)
      }
    }
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

  def keys() = {
    import collection.JavaConversions._
    ehCache.getKeys.toList.map(_.asInstanceOf[String])
  }
}
