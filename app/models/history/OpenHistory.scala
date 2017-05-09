package models.history

import java.util.UUID

import org.joda.time.LocalDateTime

case class OpenHistory(
  id: UUID,
  userId: UUID,
  deviceId: UUID,
  deviceInfo: Seq[String],
  client: String,
  occurred: LocalDateTime
)
