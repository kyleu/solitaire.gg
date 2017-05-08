package models.cache

import net.sf.ehcache.{CacheManager, Element}
import org.apache.commons.lang3.reflect.TypeUtils
import utils.Config

import scala.reflect.ClassTag

object CacheService {
  private[this] val manager = CacheManager.create()
  manager.addCache(Config.projectId)

  protected[this] val cache = manager.getCache(Config.projectId)

  private val timeout = {
    import scala.concurrent.duration._
    4.hours.toSeconds.toInt
  }

  def keys() = {
    import collection.JavaConverters._
    cache.getKeys.asScala.map({
      case s: String => s
      case x => x.toString
    })
  }

  private def set[T](key: String, value: T, expiration: Int = 0): T = {
    val element = new Element(key, value)
    if (expiration == 0) element.setEternal(true)
    element.setTimeToLive(expiration)
    cache.put(element)
    value
  }

  def set[T](key: String, value: T): T = {
    set(key, value, timeout)
  }

  def get(key: String): Option[Any] = {
    Option(cache.get(key)).map(_.getObjectValue)
  }

  def getAs[T](key: String)(implicit ct: ClassTag[T]): Option[T] = {
    val ret = get(key).map { item =>
      if (TypeUtils.isInstance(item, ct.runtimeClass)) {
        item match {
          case t: T => Some(t)
          case _ => None
        }
      } else {
        None
      }
    }.getOrElse(None)
    // log.info(s"Returning [${ret.map(_.getClass.getSimpleName)}] for key [$key]: $ret")
    ret
  }

  def remove(key: String) = {
    // log.info(s"Removing key [$key].")
    cache.remove(key)
  }
}
