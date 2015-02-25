package utils

import models.{MalformedRequest, RequestMessage, ResponseMessage}
import play.api.Logger
import play.api.libs.json._
import play.api.mvc.WebSocket.FrameFormatter

import RequestMessageSerializers._
import ResponseMessageSerializers._

object MessageFrameFormatter {
  private def requestToJson(r: RequestMessage): JsValue = throw new IllegalArgumentException("Attempted to serialize RequestMessage [" + r + "] on server.")
  private def requestFromJson(json: JsValue): RequestMessage = Json.fromJson[RequestMessage](json) match {
    case rm: JsSuccess[RequestMessage @unchecked] => rm.get
    case e: JsError => MalformedRequest(e.errors.map(x =>  x._1.toString + ": [" + x._2.mkString(" :: ")).mkString(", "), Json.stringify(json))
  }

  private def responseToJson(r: ResponseMessage): JsValue = {
    Logger.debug("Sending message: " + r)
    Json.toJson(r)
  }
  private def responseFromJson(json: JsValue): ResponseMessage = throw new IllegalArgumentException("Attempted to deserialize ResponseMessage [" + json + "] on server.")

  private val jsValueFrame: FrameFormatter[JsValue] = {
    val toStr = if(Config.debug) { Json.prettyPrint _ } else { Json.stringify _ }
    FrameFormatter.stringFrame.transform(toStr, Json.parse)
  }

  implicit val requestFormatter = jsValueFrame.transform(requestToJson, requestFromJson)
  implicit val responseFormatter = jsValueFrame.transform(responseToJson, responseFromJson)
}
