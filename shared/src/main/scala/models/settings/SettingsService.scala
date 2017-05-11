package models.settings

import java.util.UUID

object SettingsService {
  val userId = UUID.fromString("33333333-3333-3333-3333-333333333333")
  val deviceId = UUID.fromString("44444444-4444-4444-4444-444444444444")
}

class SettingsService {
  private[this] var settings: Option[Settings] = None

  def getSettings = settings.getOrElse(throw new IllegalStateException("Settings have not been loaded."))

  settings = Some(Settings.default)
}
