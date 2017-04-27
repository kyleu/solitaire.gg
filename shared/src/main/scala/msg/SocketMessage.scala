package msg

import enumeratum._

sealed trait SocketMessage extends EnumEntry

case class Hello(s: String) extends SocketMessage
case class Howdy(i: Int) extends SocketMessage

object SocketMessage extends Enum[SocketMessage] {
  override val values = findValues
}

