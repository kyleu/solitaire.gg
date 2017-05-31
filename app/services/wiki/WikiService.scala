package services.wiki

import models.rules.{GameRules, GameRulesSet}
import utils.{Application, Logging}
import better.files._
import play.api.i18n.Messages
import play.api.i18n.Messages.UrlMessageSource

import scala.concurrent.Future

object WikiService extends Logging {
  private[this] lazy val root = {
    val f = "../solitaire.gg.wiki".toFile
    if (!f.exists) {
      log.warn("Missing wiki directory")
    }
    f
  }

  private[this] def write(r: GameRules, messages: (String, Seq[Any]) => String) = {
    val f = root / "rules" / (r.id + ".md")
    if (f.exists) {
      throw new IllegalStateException(s"File [$f] already exists.")
    }
    f.createIfNotExists()
    val template = new WikiHelpTemplate(r, messages).getTemplate
    f.write(template)
  }

  def run(ctx: Application) = {
    val ret = "OK"
    val rulesDir = root / "rules"
    rulesDir.delete(swallowIOExceptions = true)
    rulesDir.createIfNotExists(asDirectory = true)

    val messagesMap = Messages.parse(UrlMessageSource(getClass.getClassLoader.getResource("client/messages")), "client") match {
      case Right(x) => x
      case Left(x) => throw x
    }

    def messages(key: String, args: Any*) = {
      val msg = messagesMap.get(key) match {
        case Some(x) => x
        case None => key
      }
      args.zipWithIndex.foldLeft(msg) { (x, y) =>
        x.replaceAllLiterally(s"{${y._2}}", y._1.toString)
      }
    }

    GameRulesSet.all.foreach(r => write(r, messages))
    Future.successful(ret)
  }

}
