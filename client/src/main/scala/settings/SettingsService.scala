package settings

import models.settings.Settings
import org.scalajs.dom
import utils.JsonSerializers

import scala.util.control.NonFatal

object SettingsService {
  private val settingsKey = "solitaire.gg.settings"
}

class SettingsService(onSave: Settings => Unit) {
  private[this] var settings = loadSettings()

  def getSettings = settings

  def apply(s: Settings) = {
    settings = s
  }

  def save() = {
    val json = JsonSerializers.writeSettings(settings)
    utils.Logging.info(s"Persisting settings [$json].")
    dom.window.localStorage.setItem(SettingsService.settingsKey, json)
    onSave(settings)
  }

  def applyAndSave(s: Settings) = {
    apply(s)
    save()
  }

  def loadSettings() = Option(dom.window.localStorage.getItem(SettingsService.settingsKey)) match {
    case Some(json) => try {
      JsonSerializers.readSettings(json)
    } catch {
      case NonFatal(_) => Settings.default
    }
    case None => Settings.default
  }
}
