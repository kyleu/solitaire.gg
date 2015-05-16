package models.audit

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import org.joda.time.LocalDateTime
import play.api.mvc.RequestHeader

object RequestLog {
  def apply(r: RequestHeader, userId: UUID, loginInfo: LoginInfo, duration: Long, status: Int): RequestLog = RequestLog(
    id = UUID.randomUUID,
    userId = userId,
    authProvider = loginInfo.providerID,
    authKey = loginInfo.providerKey,
    remoteAddress = r.remoteAddress,

    method = r.method,
    host = r.host,
    secure = r.secure,
    path = r.path,
    queryString = r.rawQueryString,

    cookie = if(r.cookies.isEmpty) {
      None
    } else {
      Some(r.cookies.toSeq.map(c => c.name + "=" + c.value).mkString(", "))
    },
    referrer = r.headers.get("HTTP_REFERER"),
    userAgent = r.headers.get("USER_AGENT"),
    started = new LocalDateTime(),
    duration = duration,
    status = status
  )
}

case class RequestLog(
  id: UUID,
  userId: UUID,
  authProvider: String,
  authKey: String,
  remoteAddress: String,

  method: String,
  host: String,
  secure: Boolean,
  path: String,
  queryString: String,

  cookie: Option[String],
  referrer: Option[String],
  userAgent: Option[String],
  started: LocalDateTime,
  duration: Long,
  status: Int
)
