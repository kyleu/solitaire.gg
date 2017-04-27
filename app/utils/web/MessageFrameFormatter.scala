package utils.web

import msg.SocketMessage
import play.api.mvc.WebSocket.MessageFlowTransformer
import utils.{JsonSerializers, Logging}

class MessageFrameFormatter(debug: Boolean) extends Logging {
  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    JsonSerializers.readMessage(s)
  }.contramap { m: SocketMessage => JsonSerializers.writeMessage(m, debug) }
}
