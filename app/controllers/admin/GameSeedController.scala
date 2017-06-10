package controllers.admin

import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameSeedService
import utils.Application
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.auto._
import io.circe.parser._
import io.circe.syntax._
import models.history.GameSeed
import utils.json.SeedSerializers

@javax.inject.Singleton
class GameSeedController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def list(q: String, sortBy: String, page: Int) = withAdminSession("list") { implicit request =>
    GameSeedService.search(q, sortBy, page).map { seeds =>
      Ok(views.html.admin.seed.list(q, sortBy, seeds._1, page, seeds._2))
    }
  }

  def remove(rules: String, seed: Int) = withAdminSession("remove") { implicit request =>
    GameSeedService.remove(rules, seed).map { ok =>
      val msg = s"Seed [$rules:$seed] removed."
      Redirect(controllers.admin.routes.GameSeedController.list()).flashing("success" -> msg)
    }
  }

  def export() = withAdminSession("export") { implicit request =>
    GameSeedService.getAll.map { seeds =>
      val lines = seeds.map(SeedSerializers.writeSeed)
      Ok(lines.mkString("\n"))
    }
  }
}
