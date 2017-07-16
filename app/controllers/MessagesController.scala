package controllers

import java.net.URL

import models.settings.Language
import play.api.i18n.Messages
import util.Application

import scala.concurrent.Future

@javax.inject.Singleton
class MessagesController @javax.inject.Inject() (override val app: Application) extends BaseController {
  private[this] val acceptedLanguages = Set("en", "ar", "cs", "de", "es", "fr", "hi", "hr", "it", "iw", "ja", "ko", "nl", "pl", "pt", "sv", "th", "vi", "zh")

  private[this] def parseMsgs(url: URL) = Messages.parse(Messages.UrlMessageSource(url), url.toString).fold(e => throw e, identity).toSeq.sortBy(_._1)

  private[this] lazy val enMsgs = {
    val filename = "client/messages"
    val resource = Option(getClass.getClassLoader.getResource(filename)) match {
      case Some(r) => r
      case None => throw new IllegalStateException("Missing [client/messages].")
    }
    parseMsgs(resource)
  }

  private[this] val enResponse = {
    val vals = enMsgs.map { m =>
      s""""${m._1}": "${m._2.replaceAllLiterally("\"", "\\\"")}""""
    }.mkString(",\n  ")
    s"""window.messages = {\n  $vals\n}"""
  }

  private[this] val responses = collection.mutable.HashMap.empty[String, String]

  def loadResponse(lang: String) = Option(getClass.getClassLoader.getResource("client/messages." + lang)).map { r =>
    val vals = parseMsgs(r).map { m =>
      s""""${m._1}": "${m._2.replaceAllLiterally("\"", "\\\"")}""""
    }.mkString(",\n  ")
    s"""window.messages = {\n  $vals\n};"""
  }.getOrElse(throw new IllegalStateException(s"Missing translation file for [$lang]."))

  private[this] def getResponse(lang: String) = lang match {
    case "en" => enResponse
    case _ => responses.getOrElseUpdate(lang, loadResponse(lang))
  }

  def strings(l: Option[String]) = req("strings") { implicit request =>
    val lang = l match {
      case Some(x) => Language.withValueOpt(x).map(_.value).getOrElse("en")
      case None => request.acceptLanguages.find(al => acceptedLanguages.contains(al.code)).map(_.code).getOrElse("en")
    }
    Future.successful(Ok(getResponse(lang)).as("application/javascript"))
  }
}
