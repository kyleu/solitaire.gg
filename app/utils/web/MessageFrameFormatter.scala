package utils.web

import models.{ MalformedRequest, MessageSet, RequestMessage, ResponseMessage }
import play.api.libs.json._
import play.api.mvc.WebSocket.FrameFormatter
import utils.json.RequestMessageSerializers._
import utils.json.ResponseMessageSerializers._

class MessageFrameFormatter @javax.inject.Inject() () {
  private[this] def requestToJson(r: RequestMessage): JsValue = {
    throw new IllegalArgumentException(s"Attempted to serialize RequestMessage [$r] on server.")
  }

  private[this] def requestFromJson(json: JsValue): RequestMessage = Json.fromJson[RequestMessage](json) match {
    case rm: JsSuccess[RequestMessage @unchecked] => rm.get
    case e: JsError => MalformedRequest(e.errors.map(x => s"$x._1: [${x._2.mkString(" :: ")}").mkString(", "), Json.stringify(json))
  }

  private[this] def responseToJson(r: ResponseMessage): JsValue = {
    r match {
      case ms: MessageSet =>
        messageSetWrites.writes(ms)
      case _ =>
        Json.toJson(r)
    }
  }
  private[this] def responseFromJson(json: JsValue): ResponseMessage = {
    throw new IllegalArgumentException(s"Attempted to deserialize ResponseMessage [$json] on server.")
  }

  private[this] val jsValueFrame: FrameFormatter[JsValue] = {
    FrameFormatter.stringFrame.transform(Json.stringify, { (s: String) =>
      val ret = try {
        Json.parse(s)
      } catch {
        case x: Exception => JsObject(Seq("c" -> JsString("MalformedRequest"), "v" -> JsObject(Seq(
          "reason" -> JsString("Invalid JSON"),
          "content" -> JsString(s)
        ))))
      }
      ret
    })
  }

  implicit val requestFormatter = jsValueFrame.transform(requestToJson, requestFromJson)
  implicit val responseFormatter = jsValueFrame.transform(responseToJson, responseFromJson)
}
