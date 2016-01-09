package models.analytics

import java.util.UUID

case class OpenEvent(
  deviceId: UUID,
  sessionId: UUID,
  deviceInfo: Map[String, Boolean],
  occurred: Long
)
