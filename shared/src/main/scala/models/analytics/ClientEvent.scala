package models.analytics

import java.util.UUID

import models.{GameWon, GameLost}

sealed trait ClientEvent {
  def deviceId: UUID
  def sessionId: UUID
  def occurred: Long
}

case class ErrorEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  message: String,
  url: String,
  line: Int,
  col: Int,
  stack: Option[String],
  deviceInfo: Map[String, Boolean],
  override val occurred: Long
) extends ClientEvent

case class GameResignedEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  message: GameLost,
  requests: Seq[Seq[String]],
  override val occurred: Long
) extends ClientEvent

case class GameStartEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  gameId: UUID,
  rules: String,
  override val occurred: Long
) extends ClientEvent

case class GameWonEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  message: GameWon,
  requests: Seq[Seq[String]],
  override val occurred: Long
) extends ClientEvent

case class GeneralEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  key: String,
  events: Map[String, String],
  override val occurred: Long
) extends ClientEvent

case class InstallEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  deviceInfo: Map[String, Boolean],
  override val occurred: Long
) extends ClientEvent

case class OpenEvent(
  override val deviceId: UUID,
  override val sessionId: UUID,
  deviceInfo: Map[String, Boolean],
  override val occurred: Long
) extends ClientEvent

