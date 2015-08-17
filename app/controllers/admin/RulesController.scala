package controllers.admin

import controllers.BaseController
import models.auth.AuthenticationEnvironment
import models.database.queries.leaderboard.GameSeedQueries
import models.leaderboard.GameSeed
import models.rules.{ GameRules, GameRulesSet }
import play.api.i18n.MessagesApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.database.Database

import scala.concurrent.Future

@javax.inject.Singleton
class RulesController @javax.inject.Inject() (override val messagesApi: MessagesApi, override val env: AuthenticationEnvironment) extends BaseController {
  def rulesList(q: String, sortBy: String) = withAdminSession("list") { implicit request =>
    Database.query(GameSeedQueries.GetCounts(None)).map { seedCounts =>
      val statuses = GameRulesSet.all.map { r =>
        val seedCount = seedCounts.getOrElse(r.id, GameSeed.emptyCount)
        getStatus(r, seedCount)
      }
      val filtered = if (q.nonEmpty) {
        statuses.filter(s => s._1.id.toLowerCase.contains(q) || s._1.title.toLowerCase.contains(q))
      } else {
        statuses
      }
      val sorted = sort(sortBy, filtered)
      Ok(views.html.admin.rules.rulesList(q, sortBy, sorted.size, 0, sorted))
    }
  }

  def rulesData = withAdminSession("data") { implicit request =>
    Future.successful(Ok(views.html.admin.rules.rulesDataList(GameRulesSet.all)))
  }

  def rulesDetail(id: String) = withAdminSession("detail") { implicit request =>
    val rules = GameRulesSet.allByIdWithAliases(id)
    Database.query(GameSeedQueries.GetCounts(Some(s"rules = '$id'"))).map { seedCounts =>
      val cnt = seedCounts.getOrElse(id, GameSeed.emptyCount)
      val status = (rules, cnt)
      Ok(views.html.admin.rules.rulesDetail(status))
    }
  }

  def rulesScreenshot(id: String) = withAdminSession("screenshot") { implicit request =>
    val filename = s"./offline/build/screenshots/$id-hd.png"
    Future.successful(Ok.sendFile(new java.io.File(filename)))
  }

  private[this] def sort(order: String, rs: Seq[(GameRules, GameSeed.SeedCount, Boolean)]) = order match {
    case "title" => rs.sortBy(_._1.title)
    case "status" => rs.sortBy(x => (!x._3, x._1.title))
    case "seeds" => rs.sortBy(_._2.seeds).reverse
    case "games" => rs.sortBy(_._2.games).reverse
    case "wins" => rs.sortBy(_._2.wins).reverse
    case "max-moves" => rs.sortBy(_._2.maxMoves).reverse
    case "avg-moves" => rs.sortBy(_._2.avgMoves).reverse
    case "min-moves" => rs.sortBy(_._2.minMoves).reverse
  }


  private[this] def getStatus(r: GameRules, seedCount: GameSeed.SeedCount) = {
    val completed = GameRulesSet.completed.exists(_._2 == r)
    (r, seedCount, completed)
  }
}
