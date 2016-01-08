package models.analytics

import java.util.UUID

case class InstallEvent(
  deviceId: UUID,
  deviceInfo: Map[String, String],
  occurred: Long
)
