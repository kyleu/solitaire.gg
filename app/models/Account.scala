package models

import java.util.UUID

import org.joda.time.LocalDateTime

case class Account(
  id: UUID,
  name: String,
  created: LocalDateTime
)
