package controllers.admin

import controllers.BaseController
import models.database.queries.ReportQueries
import org.joda.time.LocalDate
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

class ReportController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def email() = withAdminSession { implicit request =>
    Database.query(ReportQueries.ListTables).flatMap { tables =>
      Future.sequence(tables.map(table => Database.query(ReportQueries.CountTable(table)))).map { counts =>
        Ok(views.html.admin.report.emailReport(new LocalDate(), request.identity.color, counts))
      }
    }
  }
}
