package services.history

import java.util.UUID

import com.github.mauricio.async.db.Connection
import models.audit.RequestLog
import models.database.queries.RequestLogQueries
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object RequestHistoryService {
  def insert(log: RequestLog) = Database.execute(RequestLogQueries.insert(log)).map(i => log)

  def searchRequests(q: String, orderBy: String, page: Int) = Database.query(RequestLogQueries.count(q)).flatMap { count =>
    Database.query(RequestLogQueries.search(q, orderBy, Some(page))).map { list =>
      count -> list
    }
  }

  def getByUser(id: UUID) = Database.query(RequestLogQueries.GetRequestsByUser(id))

  def removeRequestsByUser(userId: UUID, conn: Option[Connection]) = Database.execute(RequestLogQueries.RemoveRequestsByUser(userId), conn)
}
