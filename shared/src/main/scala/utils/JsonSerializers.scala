package utils

import msg.SocketMessage
import upickle.Js
import upickle.default._

object JsonSerializers {
  def readMessage(s: String) = read[SocketMessage](s)
  def readMessageJs(json: Js.Value) = readJs[SocketMessage](json)
  def writeMessageJs(sm: SocketMessage) = writeJs(sm)
  def writeMessage(sm: SocketMessage, debug: Boolean = false) = if (debug) { write(sm, indent = 2) } else { write(sm) }
}
