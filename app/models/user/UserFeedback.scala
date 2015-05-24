package models.user

import java.util.UUID

import org.joda.time.LocalDateTime

case class UserFeedback(
  id: UUID,
  userId: UUID,
  activeGameId: Option[UUID],
  feedback: String,
  occurred: LocalDateTime
)
