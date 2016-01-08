package models.analytics

import java.util.UUID

case class GameStartEvent(
  deviceId: UUID,
  deviceInfo: Map[String, String],
  gameId: UUID,
  rules: String,
  occurred: Long
)
