package msg.rsp

sealed trait SocketResponseMessage

case class Howdy(i: Int) extends SocketResponseMessage


