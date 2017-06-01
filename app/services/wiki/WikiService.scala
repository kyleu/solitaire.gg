package services.wiki

import models.rules.{GameRules, GameRulesSet}
import utils.{Application, Logging}
import better.files._
import play.api.i18n.Messages
import play.api.i18n.Messages.UrlMessageSource
import services.wiki.template.WikiRules

import scala.concurrent.Future

object WikiService extends Logging {
  private[this] lazy val root = {
    val f = "../solitaire.gg.wiki".toFile
    if (!f.exists) {
      log.warn("Missing wiki directory")
    }
    f
  }

  lazy val messagesMap = Messages.parse(UrlMessageSource(getClass.getClassLoader.getResource("client/messages")), "client") match {
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

  def numberAsString(i: Int, properCase: Boolean = false) = {
    val ret = i match {
      case 0 => messages("general.number.zero")
      case 1 => messages("general.number.one")
      case 2 => messages("general.number.two")
      case 3 => messages("general.number.three")
      case 4 => messages("general.number.four")
      case 5 => messages("general.number.five")
      case 6 => messages("general.number.six")
      case 7 => messages("general.number.seven")
      case 8 => messages("general.number.eight")
      case 9 => messages("general.number.nine")
      case 10 => messages("general.number.ten")
      case 11 => messages("general.number.eleven")
      case 12 => messages("general.number.twelve")
      case _ => i.toString
    }
    if (properCase) {
      ret.head.toUpper + ret.tail
    } else {
      ret
    }
  }

  private[this] def write(r: GameRules) = {
    val f = root / "rules" / (r.id + ".md")
    if (f.exists) {
      throw new IllegalStateException(s"File [$f] already exists.")
    }
    f.createIfNotExists()
    val template = new WikiRules(r).getTemplate
    f.write(template)
  }

  def run(ctx: Application) = {
    val ret = "OK"
    val rulesDir = root / "rules"
    rulesDir.delete(swallowIOExceptions = true)
    rulesDir.createIfNotExists(asDirectory = true)

    GameRulesSet.all.foreach(r => write(r))
    Future.successful(ret)
  }

}
