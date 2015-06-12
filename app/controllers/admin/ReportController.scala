package controllers.admin

import controllers.BaseController
import models.database.queries.ReportQueries
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

class ReportController @javax.inject.Inject() (val messagesApi: MessagesApi) extends BaseController {
  def overview() = withAdminSession { implicit request =>
    Database.query(ReportQueries.ListTables()).flatMap { tables =>
      Future.sequence(tables.map { table =>
        Database.query(ReportQueries.CountTable(table))
      }).map { counts =>
        Ok(views.html.admin.report.overview(counts))
      }
    }
  }

  private[this] def getOrderClause(orderBy: String) = orderBy match {
    case x => x
  }
}
