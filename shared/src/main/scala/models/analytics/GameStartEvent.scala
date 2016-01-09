package models.analytics

import java.util.UUID

case class GameStartEvent(
  deviceId: UUID,
  sessionId: UUID,
  gameId: UUID,
  rules: String,
  occurred: Long
)
