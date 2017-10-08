package models.audit

import java.util.UUID

import java.time.LocalDateTime

object UserFeedback {
  case class FeedbackNote(
    id: UUID,
    feedbackId: UUID,
    authorId: UUID,
    content: String,
    occurred: LocalDateTime
  )
}

case class UserFeedback(
  id: UUID,
  deviceId: UUID,
  activeGameId: Option[UUID],
  feedback: String,
  occurred: LocalDateTime
)
