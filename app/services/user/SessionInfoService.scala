package services.user

import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.daos.AuthenticatorDAO
import models.database.queries.auth.SessionInfoQueries
import play.api.Play.current
import play.api.cache.Cache
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future
import scala.concurrent.duration._

object SessionInfoService extends AuthenticatorDAO[CookieAuthenticator] {
  override def save(session: CookieAuthenticator) = Database.execute(SessionInfoQueries.CreateSessionInfo(session)).map { x =>
    Cache.set(session.id, session, 4.hours)
    session
  }

  override def remove(id: String) = Database.execute(SessionInfoQueries.RemoveSessionInfo(id)).map { i =>
    Cache.remove(id)
  }

  override def find(id: String) = Cache.getAs[CookieAuthenticator](id) match {
    case Some(sess) => Future.successful(Some(sess))
    case None => Database.query(SessionInfoQueries.FindSessionInfo(id)).map {
      case Some(dbSess) =>
        Cache.set(dbSess.id, dbSess, 4.hours)
        Some(dbSess)
      case None => None
    }
  }
}
