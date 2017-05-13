package settings

import java.util.UUID

import models.settings.Settings
import org.scalajs.dom

import scala.util.control.NonFatal

object SettingsService {
  val settingsKey = "solitaire.gg.settings"
}

class SettingsService {
  private[this] var settings = loadSettings()

  def getSettings = settings

  def apply(s: Settings) = {
    settings = s
  }

  def save() = {
    val json = upickle.default.write(settings)
    utils.Logging.info(s"Persisting settings [$json].")
    dom.window.localStorage.setItem(SettingsService.settingsKey, json)
  }

  def applyAndSave(s: Settings) = {
    apply(s)
    save()
  }

  def loadSettings() = Option(dom.window.localStorage.getItem(SettingsService.settingsKey)) match {
    case Some(json) => try {
      upickle.default.read[Settings](json)
    } catch {
      case NonFatal(x) => Settings.default
    }
    case None => Settings.default
  }
}
