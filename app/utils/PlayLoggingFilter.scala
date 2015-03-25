package utils

import play.api.Logger
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object PlayLoggingFilter extends Filter {
  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
    nextFilter(requestHeader).map { result =>
      val endTime = System.currentTimeMillis
      val requestTime = endTime - startTime
      Logger.info(s"${result.header.status} (${requestTime}ms): ${requestHeader.method} ${requestHeader.uri}")
      result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}
