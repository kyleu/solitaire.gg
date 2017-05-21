package msg.req

import java.util.UUID

import models.settings.Settings

sealed trait SocketRequestMessage

case class Ping(ts: Long) extends SocketRequestMessage
case class SaveSettings(settings: Settings) extends SocketRequestMessage

case class OnGameStart(id: UUID, rules: String, seed: Option[Int] = None, occurred: Long)
case class OnGameWon(id: UUID, occurred: Long)
case class OnGameLost(id: UUID, occurred: Long)

case class ClientError(code: String, msg: String, data: String) extends SocketRequestMessage
