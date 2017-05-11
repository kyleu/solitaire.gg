package msg

sealed trait SocketMessage

case class Hello(s: String) extends SocketMessage
case class Howdy(i: Int) extends SocketMessage


