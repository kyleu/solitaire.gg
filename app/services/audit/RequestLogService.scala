package services.audit

import java.util.UUID

import models.audit.RequestLog
import models.database.queries.RequestLogQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object RequestLogService {
  def save(log: RequestLog) = Database.execute(RequestLogQueries.CreateRequest(log)).map(i => log)
  def findByUser(id: UUID) = Database.query(RequestLogQueries.FindRequestsByUser(id))
  def recentLogs() = Database.query(RequestLogQueries.GetRecentRequests())
}
