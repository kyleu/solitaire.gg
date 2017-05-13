package msg.rsp

import java.util.UUID

import models.settings.Settings

sealed trait SocketResponseMessage

case class Pong(ts: Long) extends SocketResponseMessage

case class Profile(userId: UUID, username: Option[String], email: Option[String], settings: Settings) extends SocketResponseMessage


