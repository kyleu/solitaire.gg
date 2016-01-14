package services.history

import models.queries.history.RequestLogQueries
import models.history.RequestLog
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

object RequestHistoryService {
  def insert(log: RequestLog) = Database.execute(RequestLogQueries.insert(log)).map(i => log)

  def searchRequests(q: String, orderBy: String, page: Int) = for {
    count <- Database.query(RequestLogQueries.searchCount(q))
    list <- Database.query(RequestLogQueries.search(q, orderBy, Some(page)))
  } yield count -> list
}
