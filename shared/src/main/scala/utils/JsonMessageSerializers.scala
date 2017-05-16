package utils

import msg.req.SocketRequestMessage
import msg.rsp.SocketResponseMessage
import upickle.default._

object JsonMessageSerializers {
  def readRequestMessage(s: String) = read[SocketRequestMessage](s)
  def writeRequestMessage(sm: SocketRequestMessage, debug: Boolean = false) = if (debug) { write(sm, indent = 2) } else { write(sm) }

  def readResponseMessage(s: String) = read[SocketResponseMessage](s)
  def writeResponseMessage(sm: SocketResponseMessage, debug: Boolean = false) = if (debug) { write(sm, indent = 2) } else { write(sm) }
}
