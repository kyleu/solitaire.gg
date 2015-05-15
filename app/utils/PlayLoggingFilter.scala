package utils

import com.codahale.metrics.Meter
import play.api.http.Status
import play.api.mvc._
import utils.metrics.Instrumented
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

object PlayLoggingFilter extends Filter with Logging with Instrumented {
  val prefix = "solitaire-gg.requests."

  val knownStatuses = Seq(
    Status.OK, Status.BAD_REQUEST, Status.FORBIDDEN, Status.NOT_FOUND,
    Status.CREATED, Status.TEMPORARY_REDIRECT, Status.INTERNAL_SERVER_ERROR, Status.CONFLICT,
    Status.UNAUTHORIZED, Status.NOT_MODIFIED
  )

  lazy val statusCodes: Map[Int, Meter] = knownStatuses.map(s => s -> metricRegistry.meter(prefix + s.toString)).toMap

  lazy val requestsTimer = metricRegistry.timer(prefix + "requestTimer")
  lazy val activeRequests = metricRegistry.counter(prefix + "activeRequests")
  lazy val otherStatuses = metricRegistry.meter(prefix + "other")

  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis
    val context = requestsTimer.time()
    activeRequests.inc()

    def logCompleted(result: Result): Unit = {
      activeRequests.dec()
      context.stop()
      statusCodes.getOrElse(result.header.status, otherStatuses).mark()
    }

    nextFilter(requestHeader).transform(
      result => {
        logCompleted(result)
        if (requestHeader.path.startsWith("/assets")) {
          result
        } else {
          val endTime = System.currentTimeMillis
          val requestTime = endTime - startTime
          log.info(s"${result.header.status} (${requestTime}ms): ${requestHeader.method} ${requestHeader.uri}")
          result.withHeaders("X-Request-Time-Ms" -> requestTime.toString)
        }
      },
      exception => {
        logCompleted(Results.InternalServerError)
        exception
      }
    )
  }
}
