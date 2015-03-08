package utils

import models._
import play.api.libs.json._
import play.api.libs.functional.syntax._

object RequestMessageSerializers {
  implicit val malformedRequestReads = Json.reads[MalformedRequest]
  implicit val pingReads = Json.reads[Ping]
  // case object [GetVersion]
  implicit val debugInfoReads = Json.reads[DebugInfo]
  implicit val joinGameReads = Json.reads[JoinGame]
  implicit val selectCardReads = Json.reads[SelectCard]
  implicit val selectPileReads = Json.reads[SelectPile]
  implicit val moveCardsReads = Json.reads[MoveCards]

  implicit val requestMessageReads: Reads[RequestMessage] = (
    (__ \ 'c).read[String] and
    (__ \ 'v).read[JsValue]
  ){ (c, v) =>
    val jsResult = c match {
      case "MalformedRequest" => malformedRequestReads.reads(v)
      case "Ping" => pingReads.reads(v)
      case "GetVersion" => JsSuccess(GetVersion)
      case "DebugInfo" => debugInfoReads.reads(v)
      case "JoinGame" => joinGameReads.reads(v)
      case "SelectCard" => selectCardReads.reads(v)
      case "SelectPile" => selectPileReads.reads(v)
      case "MoveCards" => moveCardsReads.reads(v)
      case _ => JsSuccess(MalformedRequest("UnknownType", "c: " + c + ", v: " + Json.stringify(v)))
    }
    jsResult match {
      case rm: JsSuccess[RequestMessage @unchecked] => rm.get
      case e: JsError => throw new IllegalArgumentException("Error parsing json for [" + c + "]: " + JsError.toFlatJson(e).toString())
    }
  }
}
