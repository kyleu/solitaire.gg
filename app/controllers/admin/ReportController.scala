package controllers.admin

import controllers.BaseController
import models.database.queries.ReportQueries
import org.joda.time.LocalDate
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database
import services.report.DailyMetricService

import scala.concurrent.Future

@javax.inject.Singleton
class ReportController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def email() = withAdminSession { implicit request =>
    Database.query(ReportQueries.ListTables).flatMap { tables =>
      val d = new LocalDate("2015-06-10")
      for {
        metrics <- DailyMetricService.getMetrics(d)
        totals <- DailyMetricService.getTotals(d)
        counts <- Future.sequence(tables.map(table => Database.query(ReportQueries.CountTable(table))))
      } yield Ok(views.html.admin.report.emailReport(new LocalDate(), request.identity.color, metrics, totals, counts))
    }
  }
}
