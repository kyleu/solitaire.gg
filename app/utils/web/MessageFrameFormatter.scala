package utils.web

import msg.rsp.SocketResponseMessage
import play.api.mvc.WebSocket.MessageFlowTransformer
import utils.{JsonMessageSerializers, Logging}

class MessageFrameFormatter(debug: Boolean) extends Logging {
  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    JsonMessageSerializers.readRequestMessage(s)
  }.contramap { m: SocketResponseMessage => JsonMessageSerializers.writeResponseMessage(m, debug) }
}
