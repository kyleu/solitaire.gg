package utils

import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object PlayLoggingFilter extends Filter with Logging {
  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
    nextFilter(requestHeader).map { result =>
      if (requestHeader.path.startsWith("/assets")) {
        result
      } else {
        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime
        log.info(s"${result.header.status} (${requestTime}ms): ${requestHeader.method} ${requestHeader.uri}")
        result.withHeaders("X-Request-Time-Ms" -> requestTime.toString)
      }
    }
  }
}
