package models.user

import java.util.UUID

import org.joda.time.LocalDateTime
import utils.DateUtils

object User {
  val placeholder = User()
}

case class User(
  id: UUID = UUID.randomUUID,
  username: Option[String] = None,
  email: Option[String] = None,
  preferences: UserPreferences = UserPreferences.default,
  created: LocalDateTime = DateUtils.now
)
