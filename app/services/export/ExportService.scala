package services.export

import better.files._
import models.rules.GameRulesSet
import models.user.User
import util.FutureUtils.defaultContext
import play.api.test.FakeRequest
import util.Application

object ExportService {
  private[this] val baseUrl = "http://localhost:5000/"
  val rootPath = "/Users/kyle/Projects/Personal/solitaire.gg".toFile
  private[this] val outPath = rootPath / "build/web"
  private[this] val offlineUser = User(User.defaultId)

  def go(ctx: Application) = {
    if (!outPath.exists) {
      outPath.createDirectory
    }
    outPath.children.foreach(_.delete(swallowIOExceptions = true))
    (outPath / "play").createDirectory

    val debug = ctx.config.debug

    implicit val request = FakeRequest("GET", "/")
    implicit val session = request.session
    implicit val flash = request.flash
    implicit val messages = ctx.messagesApi.preferred(request)

    render("index.html", views.html.solitaire.solitaire(offlineUser.settings, debug = debug).toString())
    GameRulesSet.all.foreach { r =>
      render(s"play/${r.id}.html", views.html.solitaire.solitaire(offlineUser.settings, debug = debug).toString())
    }
    val counter = 1 + GameRulesSet.all.size

    new ExportCrawler(ctx.ws, baseUrl, outPath, debug).crawlLocal().map { result =>
      s"Ok: [${result.size + counter}] files cached (debug == $debug)."
    }
  }

  private[this] def render(filename: String, content: String, prefix: Option[String] = None) = {
    val out = replaceGameLinks(replaceStaticLinks(injectMobileScript(content, prefix), prefix), prefix)
    (outPath / filename).writeBytes(out.getBytes("UTF-8").iterator)
  }

  private[this] val staticReplacements = Seq(
    "/assets/" -> "[]assets/",
    "/strings.js" -> "[]strings.js",
    "&#x27;" -> "'"
  )

  private[this] def replaceStaticLinks(s: String, prefix: Option[String]) = staticReplacements.foldLeft(s) { (s, r) =>
    val swap = prefix match {
      case Some(p) => r._2.replaceAllLiterally("[]", p)
      case None => r._2.replaceAllLiterally("[]", "/")
    }
    s.replaceAllLiterally(r._1, swap)
  }

  private[this] def replaceGameLinks(s: String, prefix: Option[String]) = GameRulesSet.completed.foldLeft(s) { (s, r) =>
    val swap = prefix match {
      case Some(_) => s"""href="$prefix/play.html?game=${r._1}""""
      case None => s"""href="gameplay.html?game=${r._1}""""
    }
    s.replaceAllLiterally(s"""href="/play/${r._1}"""", swap)
  }

  private[this] def injectMobileScript(s: String, prefix: Option[String]) = {
    val url = prefix.getOrElse("") + "mobile.js"
    s.replaceAllLiterally("  </head>", "    <script src=\"" + url + "\" type=\"text/javascript\"></script>\n  </head>")
  }
}
