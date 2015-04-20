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
    if (gamesStartIndex < 10) {
      throw new IllegalStateException()
    }
    val gamesEndIndex = body.indexOf("};", gamesStartIndex) + 1
    if (gamesEndIndex < 10) {
      throw new IllegalStateException()
    }

    val gamesContent = body.substring(gamesStartIndex, gamesEndIndex).replaceAll("([a-zA-Z0-9]*):", "\"$1\":")
    val cleanGamesContent = JsonUtils.cleanJson(gamesContent)
    val gamesJson = Json.parse(cleanGamesContent).as[JsObject]
    parseVariants(gamesJson).toSeq.sortBy(_.id)
  }

  lazy val gameRules = politaireList.map(p => new PolitaireGameRulesParser(p).parse())

  private[this] def parseVariants(json: JsObject) = {
    val variants = json.value.flatMap { js =>
      try {
        val ret = js._2.asOpt[JsObject].map { attrsJson =>
          val attributes = getAttributes(js._1, attrsJson)
          Variant(js._1, attributes, js._2)
        }
        ret
      } catch {
        case x: Exception => throw new IllegalArgumentException("Unable to parse json [" + js._2.toString + "].", x)
      }
    }

    def withParent(v: Variant, parentId: String): Variant = {
      variants.find(_.id == parentId) match {
        case Some(parent) =>
          val mergedParent = parent.attributes.get("like").map { x =>
            if (x.value.toString.nonEmpty) { withParent(parent, x.value.toString) } else { parent }
          }.getOrElse(parent)
          val newAttributes = new collection.mutable.LinkedHashMap[String, Attr]()
          newAttributes ++= mergedParent.attributes
          newAttributes ++= v.attributes.filterNot(_._2.defaultVal)
          v.copy(attributes = newAttributes)
        case None => throw new IllegalStateException("Invalid parent [" + parentId + "].")
      }
    }

    variants.map { v =>
      v.attributes.get("like") match {
        case Some(like) if like.value.toString.nonEmpty => withParent(v, like.value.toString)
        case Some(_) => v
        case None => v
      }
    }
  }

  private[this] def getAttributes(id: String, data: JsObject) = {
    val ret = collection.mutable.LinkedHashMap.empty[String, Attr]
    def processAttr(attr: (String, String), default: Option[Any] = None, markDefaults: Boolean = true) {
      data \ attr._1 match {
        case JsString(s) => ret(attr._1) = Attr(attr._1, attr._2, s.trim, None, defaultVal = false)
        case JsNumber(n) => ret(attr._1) = PolitaireTranslations.getTranslation(attr._1) match {
          case Some(x) => Attr(attr._1, attr._2, n.toInt, x.get(n.toInt), defaultVal = false)
          case None => Attr(attr._1, attr._2, n.toInt, None, defaultVal = false)
        }
        case b: JsBoolean => ret(attr._1) = Attr(attr._1, attr._2, b.value, None, defaultVal = false)
        case _: JsUndefined =>
          default.foreach(x => ret(attr._1) = Attr(attr._1, attr._2, x, PolitaireTranslations.getTranslation(attr._1) match {
            case Some(a) => try { a.get(x.toString.toInt) } catch { case x: Exception => Some(x.toString) }
            case None => None
          }, defaultVal = markDefaults))
        case x => throw new IllegalArgumentException("Invalid type [" + x.getClass.getSimpleName + ": " + x + "].")
      }
    }
    for (attr <- PolitaireLookup.titleTable) {
      attr._1 match {
        case "title" => processAttr(attr, Some(id.head.toUpper + id.tail), markDefaults = false)
        case _ => processAttr(attr, PolitaireDefaults.getDefault(attr._1))
      }
    }
    data.value.filterNot(x => PolitaireLookup.titleMap.get(x._1).isDefined).foreach { unknown =>
      processAttr((unknown._1, "*" + unknown._1), PolitaireDefaults.getDefault(unknown._1))
    }
    ret
  }
}
