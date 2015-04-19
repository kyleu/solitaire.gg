package utils.parser

import play.api.libs.json._
import utils.JsonUtils

import scala.io.Source

object PolitaireParser {
  case class Attr(id: String, title: String, value: Any, translation: Option[String], defaultVal: Boolean)
  case class Variant(id: String, attributes: collection.mutable.LinkedHashMap[String, Attr], body: JsValue)

  lazy val politaireList = {
    val body = Source.fromFile("../politaire.js").getLines().mkString("\n")
    val gamesStartIndex = body.indexOf("var games=") + 10
    if(gamesStartIndex < 10) {
      throw new IllegalStateException()
    }
    val gamesEndIndex = body.indexOf("};", gamesStartIndex) + 1
    if(gamesEndIndex < 10) {
      throw new IllegalStateException()
    }

    val gamesContent = body.substring(gamesStartIndex, gamesEndIndex).replaceAll("([a-zA-Z0-9]*):", "\"$1\":")
    val cleanGamesContent = JsonUtils.cleanJson(gamesContent)
    val gamesJson = Json.parse(cleanGamesContent).as[JsObject]
    parseVariants(gamesJson).toSeq
  }

  lazy val gameRules = politaireList.map(new PolitaireGameRulesParser(_).parse())

  private[this] def parseVariants(json: JsObject) = json.value.flatMap { js =>
    try {
      js._2.asOpt[JsObject].map { attrsJson =>
        val attributes = getAttributes(js._1, attrsJson)
        Variant(js._1, attributes, js._2)
      }
    } catch {
      case x: Exception => throw new IllegalArgumentException("Unable to parse json [" + js._2.toString + "].", x)
    }
  }

  private[this] def getAttributes(id: String, data: JsObject) = {
    val ret = collection.mutable.LinkedHashMap.empty[String, Attr]

    def processAttr(attr: (String, String), default: Option[Any] = None) {
      data \ attr._1 match {
        case JsString(s) => ret(attr._1) = Attr(attr._1, attr._2, s, None, defaultVal = false)
        case JsNumber(n) => ret(attr._1) = PolitaireTranslations.getTranslation(attr._1) match {
          case Some(x) => Attr(attr._1, attr._2, n.toInt, x.get(n.toInt), defaultVal = false)
          case None => Attr(attr._1, attr._2, n.toInt, None, defaultVal = false)
        }
        case _: JsUndefined => default.foreach(x => ret(attr._1) = Attr(attr._1, attr._2, x, PolitaireTranslations.getTranslation(attr._1) match {
          case Some(a) => try { a.get(x.toString.toInt) } catch { case x: Exception => Some(x.toString) }
          case None => None
        }, defaultVal = true))
        case x => ret(attr._1) = Attr(attr._1, attr._2, x, None, defaultVal = false)
      }
    }

    for(attr <- PolitaireLookup.titleTable) {
      attr._1 match {
        case "title" => processAttr(attr, Some(id.head.toUpper + id.tail))
        case _ => processAttr(attr, PolitaireDefaults.getDefault(attr._1))
      }
    }

    data.value.filterNot(x => PolitaireLookup.titleMap.get(x._1).isDefined).foreach { unknown =>
      processAttr((unknown._1, "*" + unknown._1), PolitaireDefaults.getDefault(unknown._1))
    }

    ret
  }
}
