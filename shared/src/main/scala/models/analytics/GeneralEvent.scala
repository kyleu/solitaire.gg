package models.analytics

import java.util.UUID

case class GeneralEvent(
  deviceId: UUID,
  key: String,
  events: Map[String, String],
  occurred: Long
)
