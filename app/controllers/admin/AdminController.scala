package controllers.admin

import java.util.UUID

import akka.pattern.ask
import akka.util.Timeout
import controllers.BaseController
import models._
import models.database.queries.auth.UserQueries
import models.user.Role
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.ActorSupervisor
import services.database.{ Schema, Database }
import services.scheduled.ScheduledTask
import services.user.AuthenticationEnvironment
import utils.cache.UserCache

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

@javax.inject.Singleton
class AdminController @javax.inject.Inject() (
    override val messagesApi: MessagesApi,
    override val env: AuthenticationEnvironment,
    scheduledTask: ScheduledTask
) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def index = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.index()))
  }

  def enable = withSession { implicit request =>
    Database.execute(UserQueries.AddRole(request.identity.id, Role.Admin)).map { x =>
      UserCache.removeUser(request.identity.id)
      Ok("OK")
    }
  }

  def status = withAdminSession { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case x: SystemStatus => Ok(views.html.admin.status(x))
    }
  }

  def wipeSchema(key: String) = withAdminSession { implicit request =>
    if (key == "for realz") {
      Schema.wipe().map { tables =>
        Ok(s"OK, Wiped tables [${tables.mkString(", ")}].")
      }
    } else {
      Future.successful(NotFound("Missing key."))
    }
  }

  def observeRandomGame() = withAdminSession { implicit request =>
    (ActorSupervisor.instance ask GetSystemStatus).map {
      case ss: SystemStatus => if (ss.games.isEmpty) {
        Ok("No games available.")
      } else {
        val gameId = ss.games(new Random().nextInt(ss.games.length))._1
        Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", Seq("observe", gameId.toString)))
      }
      case se: ServerError => Ok(s"${se.reason}: ${se.content}")
    }
  }

  def observeGameAsAdmin(gameId: UUID) = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", Seq("observe", gameId.toString))))
  }

  def observeGameAs(gameId: UUID, as: UUID) = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.game.gameplay(s"Observing [$gameId]", request.identity, "", Seq("observe", gameId.toString, as.toString))))
  }
}
