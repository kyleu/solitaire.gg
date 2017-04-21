package gg.network

import models._
import utils.{Logging, NetworkMessage}

class MessageHandler {
  def handleMessage(rm: ResponseMessage) = rm match {
    case p: Pong => NetworkMessage.setLatencyMs((System.currentTimeMillis - p.timestamp).toInt)
    case _ => Logging.warn(s"Received unknown message of type [${rm.getClass.getSimpleName}].")
  }
}
