package models.analytics

import java.util.UUID

case class InstallEvent(
  deviceId: UUID,
  sessionId: UUID,
  deviceInfo: Map[String, Boolean],
  occurred: Long
)
