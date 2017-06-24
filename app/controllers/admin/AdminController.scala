package controllers.admin

import akka.util.Timeout
import controllers.BaseController
import models.rules.GameRulesSet
import utils.FutureUtils.defaultContext
import services.database.Schema
import utils.Application

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random

@javax.inject.Singleton
class AdminController @javax.inject.Inject() (override val app: Application) extends BaseController {
  implicit val timeout = Timeout(10.seconds)

  def index = withAdminSession("index") { implicit request =>
    Future.successful(Ok(views.html.admin.index()))
  }

  def unfinished = withAdminSession("unfinished") { implicit request =>
    val rules = GameRulesSet.unfinished(Random.nextInt(GameRulesSet.unfinished.size))._2
    Future.successful(Redirect(controllers.routes.SolitaireController.startArgs(rules.id)))
  }

  def wipeSchema(key: String) = withAdminSession("wipe.schema") { implicit request =>
    if (key == "for realz") {
      Schema.wipe().map { tables =>
        Ok(s"OK, Wiped tables [${tables.mkString(", ")}].")
      }
    } else {
      Future.successful(NotFound("Missing key."))
    }
  }
}
