package services.export

import better.files._
import models.rules.GameRulesSet
import models.user.User
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.test.FakeRequest
import utils.Application

object ExportService {
  private[this] val baseUrl = "http://localhost:5000/"
  private[this] val outPath = "./build/web".toFile
  private[this] val offlineUser = User(User.defaultId)

  def go(ctx: Application) = {
    if (!outPath.exists) {
      outPath.createDirectory
    }
    outPath.children.foreach(_.delete(swallowIOExceptions = true))

    implicit val request = FakeRequest("GET", "/")
    implicit val session = request.session
    implicit val flash = request.flash
    implicit val messages = ctx.messagesApi.preferred(request)

    render("index.html", views.html.solitaire.solitaire(offlineUser.settings, debug = ctx.config.debug).toString())

    new ExportCrawler(ctx.ws, baseUrl, outPath, ctx.config.debug).crawlLocal().map { result =>
      s"Ok: [${result.size}] files cached (debug == ${ctx.config.debug})."
    }
  }

  private[this] def render(filename: String, content: String, prefix: Option[String] = None) = {
    val out = replaceGameLinks(replaceStaticLinks(injectMobileScript(content, prefix), prefix), prefix)
    (outPath / filename).writeBytes(out.getBytes("UTF-8").iterator)
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

  private[this] def injectMobileScript(s: String, prefix: Option[String]) = {
    val url = prefix.getOrElse("") + "mobile.js"
    s.replaceAllLiterally("  </head>", "    <script src=\"" + url + "\" type=\"text/javascript\"></script>\n  </head>")
  }

}
