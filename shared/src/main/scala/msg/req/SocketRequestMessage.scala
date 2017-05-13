package msg.req

sealed trait SocketRequestMessage

case class Hello(s: String) extends SocketRequestMessage


