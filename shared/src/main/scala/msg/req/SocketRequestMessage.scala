package msg.req

import java.util.UUID

import models.settings.Settings

sealed trait SocketRequestMessage

case class Ping(ts: Long) extends SocketRequestMessage
case class SaveSettings(settings: Settings) extends SocketRequestMessage

case class OnGameStart(id: UUID, rules: String, seed: Int, occurred: Long) extends SocketRequestMessage
case class OnGameComplete(
  id: UUID, rules: String, seed: Int, status: Char, duration: Long, moves: Int, undos: Int, redos: Int, score: Int, firstMove: Long, occurred: Long
) extends SocketRequestMessage

case class ClientError(code: String, msg: String, data: String) extends SocketRequestMessage
