package utils

import models.{GetVersion, RequestMessage, Ping, MalformedRequest}
import play.api.libs.json._
import play.api.libs.functional.syntax._

object RequestMessageSerializers {
  implicit val malformedRequestReads = Json.reads[MalformedRequest]
  implicit val pingReads = Json.reads[Ping]
  // case object [GetVersion]

  implicit val requestMessageReads: Reads[RequestMessage] = (
    (__ \ 'c).read[String] and
    (__ \ 'v).read[JsValue]
  ){ (c, v) =>
    val jsResult = c match {
      case "MalformedRequest" => malformedRequestReads.reads(v)
      case "Ping" => pingReads.reads(v)
      case "GetVersion" => JsSuccess(GetVersion)
    }
    jsResult match {
      case rm: JsSuccess[RequestMessage @unchecked] => rm.get
      case e: JsError => throw new IllegalArgumentException("Error parsing json for [" + c + "]: " + JsError.toFlatJson(e).toString())
    }
  }
}
