package controllers.admin

import controllers.BaseController
import models.audit.RulesStatus
import models.database.queries.leaderboard.GameSeedQueries
import models.rules.{ GameRules, GameRulesSet }
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

@javax.inject.Singleton
class RulesController @javax.inject.Inject() (override val messagesApi: MessagesApi) extends BaseController {
  def rulesList(q: String, sortBy: String) = withAdminSession { implicit request =>
    Database.query(GameSeedQueries.GetCounts(None)).map { seedCounts =>
      val statuses = GameRulesSet.all.map { r =>
        val seedCount = seedCounts.getOrElse(r.id, (0, 0, 0, 0))
        getStatus(r, seedCount)
      }
      val filtered = if(q.nonEmpty) {
        statuses.filter( s => s.rules.id.toLowerCase.contains(q) || s.rules.title.toLowerCase.contains(q))
      } else {
        statuses
      }
      val sorted = sort(sortBy, filtered)
      Ok(views.html.admin.rules.rulesList(q, sortBy, sorted.size, 0, sorted))
    }
  }

  def rulesData = withAdminSession { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesDataList(GameRulesSet.all)))
  }

  def rulesDetail(id: String) = withAdminSession { implicit request =>
    val rules = GameRulesSet.allById(id)
    Database.query(GameSeedQueries.GetCounts(Some(s"rules = '$id'"))).map { seedCounts =>
      val status = getStatus(rules, seedCounts.getOrElse(id, (0, 0, 0, 0)))
      Ok(views.html.admin.rules.rulesDetail(status))
    }
  }

  def rulesScreenshot(id: String) = withAdminSession { implicit request =>
    val filename = s"./offline/build/screenshots/$id-hd.png"
    Future.successful(Ok.sendFile(new java.io.File(filename)))
  }

  private[this] def getStatus(r: GameRules, seedCount: (Int, Int, Int, Int)) = {
    val gameCounts = (0, 0)
    RulesStatus(r,
      gameCounts._1, gameCounts._2,
      seedCount._1, seedCount._2, seedCount._3, seedCount._4,
      completed = GameRulesSet.completed.contains(r),
      inProgress = GameRulesSet.inProgress.contains(r)
    )
  }

  private[this] def sort(order: String, rs: Seq[RulesStatus]) = order match {
    case "title" => rs.sortBy(_.rules.title)
    case "status" => rs.sortBy(x => (!x.completed, !x.inProgress, x.rules.title))
    case "seeds" => rs.sortBy(_.winningSeeds).reverse
    case "max-moves" => rs.sortBy(_.maxMoves).reverse
    case "avg-moves" => rs.sortBy(_.avgMoves).reverse
    case "min-moves" => rs.sortBy(_.minMoves).reverse
  }
}
