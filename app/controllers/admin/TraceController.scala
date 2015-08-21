package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.supervisor.ActorSupervisor
import utils.ApplicationContext

import scala.concurrent.duration._

@javax.inject.Singleton
class TraceController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def traceConnection(connectionId: UUID) = withAdminSession("connection") { implicit request =>
    (ctx.supervisor ask ConnectionTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.activity.trace("Connection", tr))
      case se: ServerError => Ok(s"${se.reason}: ${se.content}")
    }
  }

  def traceClient(connectionId: UUID) = withAdminSession("client") { implicit request =>
    (ctx.supervisor ask ClientTrace(connectionId)).map {
      case tr: TraceResponse => Ok(views.html.admin.activity.trace("Client", tr))
      case se: ServerError => Ok(s"${se.reason}: ${se.content}")
    }
  }

  def traceGame(gameId: UUID) = withAdminSession("game") { implicit request =>
    (ctx.supervisor ask GameTrace(gameId)).map {
      case tr: TraceResponse =>
        val buttons = Seq("Observe As Admin" -> controllers.admin.routes.AdminController.observeGameAsAdmin(gameId).url)
        Ok(views.html.admin.activity.trace("Game", tr, buttons))
      case se: ServerError => Ok(s"${se.reason}: ${se.content}")
    }
  }
}
