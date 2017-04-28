package settings

object SettingsService {

}

class SettingsService {
  private[this] var settings: Option[PlayerSettings] = None

  def getSettings = settings.getOrElse(throw new IllegalStateException("Settings have not been loaded."))

  settings = Some(PlayerSettings.default)
}
