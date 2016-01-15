package models.analytics

import java.util.UUID

case class ErrorEvent(
  deviceId: UUID,
  sessionId: UUID,
  message: String,
  url: String,
  line: Int,
  col: Int,
  stack: Option[String],
  deviceInfo: Map[String, Boolean],
  occurred: Long
)
