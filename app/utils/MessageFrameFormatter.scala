package utils

import models.{ MessageSet, MalformedRequest, RequestMessage, ResponseMessage }
import play.api.libs.json._
import play.api.mvc.WebSocket.FrameFormatter

import utils.RequestMessageSerializers._
import utils.ResponseMessageSerializers._

object MessageFrameFormatter {
  private def requestToJson(r: RequestMessage): JsValue = throw new IllegalArgumentException("Attempted to serialize RequestMessage [" + r + "] on server.")
  private def requestFromJson(json: JsValue): RequestMessage = Json.fromJson[RequestMessage](json) match {
    case rm: JsSuccess[RequestMessage @unchecked] => rm.get
    case e: JsError => MalformedRequest(e.errors.map(x => x._1.toString + ": [" + x._2.mkString(" :: ")).mkString(", "), Json.stringify(json))
  }

  private def responseToJson(r: ResponseMessage): JsValue = {
    r match {
      case ms: MessageSet =>
        messageSetWrites.writes(ms)
      case _ =>
        Json.toJson(r)
    }
  }
  private def responseFromJson(json: JsValue): ResponseMessage = throw new IllegalArgumentException("Attempted to deserialize ResponseMessage [" + json + "] on server.")

  private val jsValueFrame: FrameFormatter[JsValue] = {
    val toStr = if (Config.debug) { Json.prettyPrint _ } else { Json.stringify _ }
    FrameFormatter.stringFrame.transform(toStr, { (s: String) =>
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
