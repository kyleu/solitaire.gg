package utils

import models.{RequestMessage, ResponseMessage}
import play.api.libs.json._
import play.api.mvc.WebSocket.FrameFormatter

import RequestMessageSerializers._
import ResponseMessageSerializers._

object MessageFrameFormatter {
  private def requestToJson(r: RequestMessage): JsValue = throw new IllegalArgumentException("Attempted to serialize RequestMessage [" + r + "] on server.")
  private def requestFromJson(json: JsValue): RequestMessage = Json.fromJson[RequestMessage](json) match {
    case rm: JsSuccess[RequestMessage @unchecked] => rm.get
    case e: JsError => throw new IllegalArgumentException("Error parsing json [" + JsError.toFlatJson(e).toString() + "].")
  }

  private def responseToJson(r: ResponseMessage): JsValue = Json.toJson(r)
  private def responseFromJson(json: JsValue): ResponseMessage = throw new IllegalArgumentException("Attempted to deserialize ResponseMessage [" + json + "] on server.")

  implicit val requestFormatter = FrameFormatter.jsonFrame.transform(requestToJson, requestFromJson)
  implicit val responseFormatter = FrameFormatter.jsonFrame.transform(responseToJson, responseFromJson)
}
