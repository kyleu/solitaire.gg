package models.history

import org.joda.time.{ LocalDateTime, LocalDate }

case class DataArchiveCount(
  table: String,
  day: LocalDate,
  count: Int,
  archived: LocalDateTime
)
