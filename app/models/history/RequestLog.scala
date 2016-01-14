package models.history

import java.util.UUID

import org.joda.time.LocalDateTime
import play.api.mvc.RequestHeader
import utils.DateUtils

object RequestLog {
  def apply(r: RequestHeader, duration: Int, status: Int): RequestLog = RequestLog(
    id = UUID.randomUUID,
    remoteAddress = r.remoteAddress,

    method = r.method,
    host = r.host,
    secure = r.secure,
    path = r.path,
    queryString = r.rawQueryString,

    lang = r.acceptLanguages.headOption.map(_.code),
    cookie = if (r.cookies.isEmpty) { None } else { Some(r.cookies.toSeq.map(c => s"${c.name}=${c.value}").mkString(", ")) },
    referrer = r.headers.get("Referer"),
    userAgent = r.headers.get("User-Agent"),
    started = DateUtils.now,
    duration = duration,
    status = status
  )
}

case class RequestLog(
  id: UUID,
  remoteAddress: String,

  method: String,
  host: String,
  secure: Boolean,
  path: String,
  queryString: String,

  lang: Option[String],
  cookie: Option[String],
  referrer: Option[String],
  userAgent: Option[String],
  started: LocalDateTime,
  duration: Int,
  status: Int
)
