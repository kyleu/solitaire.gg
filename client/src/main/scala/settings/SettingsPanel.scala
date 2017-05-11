package settings

import models.settings.Settings
import org.scalajs.jquery.{jQuery => $}

object SettingsPanel {
  private[this] var initialized = false

  def initIfNeeded(settings: Settings) = {
    val panel = $("#settings-content")
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initialized = true
  }
}
