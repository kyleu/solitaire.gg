package models.history

import java.time.{LocalDateTime, LocalDate}

case class DataArchiveCount(
  table: String,
  day: LocalDate,
  count: Int,
  archived: LocalDateTime
)
