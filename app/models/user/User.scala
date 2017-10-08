package models.user

import java.util.UUID

import models.settings.Settings
import java.time.LocalDateTime
import util.DateUtils

object User {
  val placeholder = User()
  val defaultId = UUID.fromString("00000000-0000-0000-0000-000000000000")
}

case class User(
  id: UUID = UUID.randomUUID,
  username: Option[String] = None,
  email: Option[String] = None,
  settings: Settings = Settings.default,
  created: LocalDateTime = DateUtils.now
)
