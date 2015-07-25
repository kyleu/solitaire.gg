package services.sandbox

import java.nio.file.{ Files, Paths }

import models.rules.GameRulesSet
import models.user.{ User, UserPreferences }
import play.api.i18n.MessagesApi
import play.api.test.FakeRequest
import services.test.TestService
import utils.DateUtils

import scala.concurrent.Future

object ExportStatic {
  private[this] val outPath = Paths.get(".", "offline", "build", "templates")
  private[this] val helpPath = outPath.resolve("help")

  private[this] val offlineUser = User(TestService.testUserId, Some("Offline"), UserPreferences(), Nil, created = DateUtils.now)

  def run(messagesApi: MessagesApi) = {
    if (!Files.exists(outPath)) {
      Files.createDirectory(outPath)
    }
    if (!Files.exists(helpPath)) {
      Files.createDirectory(helpPath)
    }

    implicit val request = FakeRequest("GET", "/")
    implicit val session = request.session
    implicit val flash = request.flash
    implicit val messages = messagesApi.preferred(request)

    render("index.html", views.html.index(offlineUser).toString())
    render("gameplay.html", views.html.game.gameplay(
      title = "Solitaire.gg",
      user = offlineUser,
      rulesId = "",
      rulesDescription = "",
      initialAction = Seq("start"),
      seed = None,
      offline = true,
      debug = false
    ).toString)

    GameRulesSet.completed.map { rules =>
      render(s"help/${rules._1}.html", views.html.help.helpPage(offlineUser, rules._2).toString, prefix = Some("../"))
    }

    Future.successful("Ok!")
  }

  private[this] def render(filename: String, content: String, prefix: Option[String] = None) = {
    val out = replaceGameLinks(replaceStaticLinks(content, prefix), prefix)
    Files.write(outPath.resolve(filename), out.getBytes("UTF-8"))
  }

  private[this] val staticReplacements = Seq(
    """href="/"""" -> """href="[]index.html"""",
    """href="/profile"""" -> """href="[]profile.html"""",
    """href="/about"""" -> """href="[]about.html"""",
    """/assets/""" -> """[]assets/""",
    """&#x27;""" -> """'"""
  )

  private[this] def replaceStaticLinks(s: String, prefix: Option[String]) = staticReplacements.foldLeft(s) { (s, r) =>
    val swap = prefix match {
      case Some(p) => r._2.replaceAllLiterally("[]", p)
      case None => r._2.replaceAllLiterally("[]", "")
    }
    s.replaceAllLiterally(r._1, swap)
  }

  private[this] def replaceGameLinks(s: String, prefix: Option[String]) = GameRulesSet.completed.foldLeft(s) { (s, r) =>
    val swap = prefix match {
      case Some(p) => s"""href="$prefix/play.html?game=${r._1}""""
      case None => s"""href="gameplay.html?game=${r._1}""""
    }
    s.replaceAllLiterally(s"""href="/play/${r._1}"""", swap)
  }
}
