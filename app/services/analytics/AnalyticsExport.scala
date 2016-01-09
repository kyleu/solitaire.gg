package services.analytics

import models.queries.audit.AnalyticsEventQueries
import services.database.Database

@javax.inject.Singleton
class AnalyticsExport @javax.inject.Inject() () {
  def getPersistedDateCounts = Database.query(AnalyticsEventQueries.GetDateCounts)
}
