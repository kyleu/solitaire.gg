package parser.politaire

import parser.politaire.defaults.ParserDefaults
import parser.utils.IntUtils
import play.api.libs.json._
import parser.JsonUtils
import parser.politaire.lookup.PolitaireLookup

import scala.io.Source

object PolitaireParser {
  case class Attr(id: String, title: String, value: Any, translation: Option[String], defaultVal: Boolean)
  case class Variant(id: String, attributes: collection.mutable.LinkedHashMap[String, Attr], body: JsValue)

  lazy val politaireList = {
    val body = Source.fromFile("../politaire.js").getLines().mkString("\n")
    val gamesStartIndex = body.indexOf("var games=") + 10
    if (gamesStartIndex < 10) {
      throw new IllegalStateException("Can't find games declaration.")
    }
    val gamesEndIndex = body.indexOf("};", gamesStartIndex) + 1
    if (gamesEndIndex < 10) {
      throw new IllegalStateException("Can't parse games declaration.")
    }

    val gamesContent = body.substring(gamesStartIndex, gamesEndIndex).replaceAll("([a-zA-Z0-9]*):", "\"$1\":")
    val cleanGamesContent = JsonUtils.cleanJson(gamesContent)
    val gamesJson = Json.parse(cleanGamesContent).as[JsObject]
    parseVariants(gamesJson).toSeq.sortBy(_.id)
  }

  lazy val gameRules = politaireList.map(p => new GameRulesParser(p).parse())

  private[this] val doNotInheritFrom = Seq("related", "like")

  private[this] def parseVariants(json: JsObject) = {
    val variants = json.value.flatMap { js =>
      try {
        val overrides = PolitaireOverrides.overrides.getOrElse(js._1, Map.empty)
        val ret = js._2.asOpt[JsObject].map { attrsJson =>
          val attributes = getAttributes(js._1, attrsJson, overrides)
          Variant(js._1, attributes, js._2)
        }
        ret
      } catch {
        case x: Exception => throw new IllegalArgumentException(s"Unable to parse json [${js._2.toString}].", x)
      }
    }.toList

    def withParent(v: Variant, parentId: String): Variant = {
      variants.find(_.id == parentId) match {
        case Some(parent) =>
          val mergedParent = parent.attributes.get("like").map { x =>
            if (x.value.toString.nonEmpty) { withParent(parent, x.value.toString) } else { parent }
          }.getOrElse(parent)
          val newAttributes = new collection.mutable.LinkedHashMap[String, Attr]()
          newAttributes ++= mergedParent.attributes.filterNot( x => doNotInheritFrom.contains(x._1))
          newAttributes ++= v.attributes.filterNot(_._2.defaultVal)
          v.copy(attributes = newAttributes)
        case None => throw new IllegalStateException(s"Invalid parent [$parentId].")
      }
    }

    variants.map { v =>
      val related = variants.filter(_.attributes.get("like").map(_.value.toString).contains(v.id)).map(_.id)
      v.attributes("related") = Attr("related", PolitaireLookup.getTitle("related").getOrElse("related"), related.mkString(", "), None, defaultVal = false)

      v.attributes.get("like") match {
        case Some(like) if like.value.toString.nonEmpty => withParent(v, like.value.toString)
        case Some(_) => v
        case None => v
      }
    }
  }

  private[this] def getAttributes(id: String, data: JsObject, overrides: Map[String, Int]) = {
    val ret = collection.mutable.LinkedHashMap.empty[String, Attr]
    def processAttr(attr: (String, String), default: Option[Any] = None, markDefaults: Boolean = true) {
      (data \ attr._1).toOption match {
        case Some(JsString(s)) =>
          val value = IntUtils.parse(s.trim).getOrElse(s.trim)
          ret(attr._1) = Attr(attr._1, attr._2, value, None, defaultVal = false)
        case Some(JsNumber(n)) => ret(attr._1) = PolitaireLookup.getTranslation(attr._1) match {
          case Some(x) => Attr(attr._1, attr._2, n.toInt, x.get(n.toInt), defaultVal = false)
          case None => Attr(attr._1, attr._2, n.toInt, None, defaultVal = false)
        }
        case Some(b: JsBoolean) => ret(attr._1) = Attr(attr._1, attr._2, b.value, None, defaultVal = false)
        case None => default.foreach(x => ret(attr._1) = Attr(attr._1, attr._2, x, PolitaireLookup.getTranslation(attr._1) match {
          case Some(a) => try { a.get(x.toString.toInt) } catch { case x: Exception => Some(x.toString) }
          case None => None
        }, defaultVal = markDefaults))
        case x => throw new IllegalArgumentException(s"Invalid type [${x.getClass.getSimpleName}: $x].")
      }
    }

    data.value.foreach { value =>
      val title = PolitaireLookup.getTitle(value._1)
      processAttr((value._1, title.getOrElse("*" + value._1)), ParserDefaults.getDefault(value._1))
    }
    processAttr("title" -> "Title", Some(id.head.toUpper + id.tail), markDefaults = false)

    overrides.foreach { o =>
      val translation = PolitaireLookup.getTranslation(o._1) match {
        case Some(x) => x.get(o._2)
        case None => None
      }
      ret(o._1) = PolitaireParser.Attr(o._1, PolitaireLookup.getTitle(o._1).getOrElse("*" + o._1), o._2, translation, defaultVal = false)
    }

    ret
  }
}
