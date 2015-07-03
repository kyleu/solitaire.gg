package services.sandbox

import java.nio.file.{ Files, Paths }

import models.rules.GameRulesSet
import models.user.{ User, UserPreferences }
import play.api.i18n.MessagesApi
import play.api.test.FakeRequest
import play.twirl.api.Html
import services.test.TestService
import utils.DateUtils
import utils.play.RoutesMap

import scala.concurrent.Future

object ExportStatic {
  private[this] val outPath = Paths.get(".", "offline", "build", "templates")
  private[this] val helpPath = outPath.resolve("help")

  private[this] val offlineUser = User(TestService.testUserId, Some("Offline"), UserPreferences(), Nil, created = DateUtils.now)

  def run(messagesApi: MessagesApi) = {
    if (!Files.exists(outPath)) {
      throw new IllegalStateException()
    }
    if (!Files.exists(helpPath)) {
      Files.createDirectory(helpPath)
    }

    implicit val request = FakeRequest("GET", "/")
    implicit val session = request.session
    implicit val flash = request.flash
    implicit val messages = messagesApi.preferred(request)

    render("index.html", views.html.index(offlineUser, RoutesMap.static))
    render("gameplay.html", views.html.game.gameplay(
      title = "Solitaire.gg",
      user = offlineUser,
      rulesId = "default",
      initialAction = Seq("start"),
      seed = None,
      offline = false,
      routesMap = RoutesMap.static
    ))

    GameRulesSet.completed.map { rules =>
      render(s"help/${rules._1}.html", views.html.help.helpPage(offlineUser, rules._2, RoutesMap.static))
    }

    Future.successful("Ok!")
  }

  private[this] def render(filename: String, content: Html) = {
    if (Files.exists(outPath.resolve(filename))) {
      Files.write(outPath.resolve(filename), content.toString().getBytes("UTF-8"))
    }
  }
}
