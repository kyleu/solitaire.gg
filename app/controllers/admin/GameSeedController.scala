package controllers.admin

import better.files._
import controllers.BaseController
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameSeedService
import utils.Application
import utils.json.SeedSerializers

import scala.concurrent.Future
import scala.util.control.NonFatal

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

  def exportSeeds() = withAdminSession("export") { implicit request =>
    GameSeedService.getAll.map { seeds =>
      val lines = seeds.map(SeedSerializers.writeSeed)
      Ok(lines.mkString("\n"))
    }
  }

  def importSeeds() = withAdminSession("export") { implicit request =>
    val seeds = "./tmp/seeds.txt".toFile.lines.map(SeedSerializers.readSeed)
    val completed = seeds.foldLeft(Future.successful(Seq.empty[Boolean])) { (x, seed) =>
      x.flatMap { ret =>
        GameSeedService.insert(seed).recover {
          case NonFatal(_) =>
            log.warn(s"Attempted to insert duplicate seed [${seed.rules}:${seed.seed}].")
            false
        }.map(x => ret :+ x)
      }
    }
    completed.map { ret =>
      Ok(s"[${seeds.size}] seeds imported. [${ret.count(x => x)}] inserted, [${ret.count(x => !x)}] skipped.")
    }
  }
}
