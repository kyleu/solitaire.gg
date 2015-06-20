package utils

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.user.User
import net.sf.ehcache.{ CacheManager, Element }
import org.apache.commons.lang3.reflect.TypeUtils

import scala.reflect.ClassTag

object CacheService {
  private val manager = CacheManager.create()
  manager.addCache(Config.projectId)
  private val cache = manager.getCache(Config.projectId)

  private val timeout = {
    import scala.concurrent.duration._
    4.hours.toSeconds.toInt
  }

  def getUser(id: UUID) = {
    getAs[User](s"user-$id")
  }

  def cacheUser(user: User) = {
    set(s"user-${user.id}", user, timeout)
    user
  }

  def cacheUserForLoginInfo(user: User, loginInfo: LoginInfo) = {
    set(s"user-${user.id}", user, timeout)
    set(s"user-${loginInfo.providerID}:${loginInfo.providerKey}", user, timeout)
  }

  def getUserByLoginInfo(loginInfo: LoginInfo) = {
    getAs[User](s"user-${loginInfo.providerID}:${loginInfo.providerKey}")
  }

  def removeUser(id: UUID) = {
    getAs[User](s"user-$id").foreach { u =>
      for (p <- u.profiles) {
        cache.remove(s"user-${p.providerID}:${p.providerKey}")
      }
    }
    cache.remove(s"user-$id")
  }

  def cacheSession(session: CookieAuthenticator) = {
    set(session.id, session)
    session
  }

  def getSession(id: String) = {
    getAs[CookieAuthenticator](id)
  }

  def removeSession(id: String) = {
    cache.remove(id)
  }

  def keys() = {
    import collection.JavaConverters._
    cache.getKeys.asScala.map({
      case s: String => s
      case x => x.toString
    })
  }

  private def set(key: String, value: Any, expiration: Int = 0): Unit = {
    val element = new Element(key, value)
    if (expiration == 0) element.setEternal(true)
    element.setTimeToLive(expiration)
    cache.put(element)
  }

  def set(key: String, value: Any): Unit = {
    set(key, value, timeout)
  }

  def get(key: String): Option[Any] = {
    Option(cache.get(key)).map(_.getObjectValue)
  }

  def getAs[T](key: String)(implicit ct: ClassTag[T]): Option[T] = {
    get(key).map { item =>
      if (TypeUtils.isInstance(item, ct.runtimeClass)) {
        item match {
          case t: T => Some(t)
          case _ => None
        }
      } else {
        None
      }
    }.getOrElse(None)
  }
}
