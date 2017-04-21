package utils.web

import models.{MalformedRequest, RequestMessage, ResponseMessage}
import play.api.mvc.WebSocket.MessageFlowTransformer
import upickle.Js
import utils.Logging
import utils.json.{RequestMessageSerializers, ResponseMessageSerializers}

import scala.util.control.NonFatal

class MessageFrameFormatter(debug: Boolean) extends Logging {
  private[this] def requestFromJsValue(json: Js.Value): RequestMessage = try {
    RequestMessageSerializers.read(json)
  } catch {
    case NonFatal(x) => MalformedRequest(s"Invalid Request [${x.getClass.getSimpleName}]", json.toString)
  }

  private[this] def responseToJsValue(r: ResponseMessage): Js.Value = ResponseMessageSerializers.write(r)

  private[this] def jsValueToString(v: Js.Value) = if (debug) {
    upickle.json.write(v, indent = 2)
  } else {
    upickle.json.write(v)
  }

  private[this] def jsValueFromString(s: String) = {
    try {
      upickle.json.read(s)
    } catch {
      case NonFatal(x) => Js.Arr(Js.Str("models.MalformedRequest"), Js.Obj(
        "reason" -> Js.Str("Invalid JSON"),
        "content" -> Js.Str(s)
      ))
    }
  }

  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    requestFromJsValue(jsValueFromString(s))
  }.contramap { m: ResponseMessage =>
    jsValueToString(responseToJsValue(m))
  }
}
