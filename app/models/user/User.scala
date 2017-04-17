package models.user

import java.util.UUID

import org.joda.time.LocalDateTime
import utils.DateUtils

case class User(
  id: UUID,
  username: Option[String],
  email: Option[String],
  preferences: UserPreferences,
  created: LocalDateTime = DateUtils.now
)
