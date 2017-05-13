package settings

import models.settings.Settings
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

import scala.scalajs.js

object SettingsPanel {
  private[this] var initialized = false
  private[this] var originalSettings = Settings.default
  private[this] var currentSettings = originalSettings
  def getCurrentSettings = currentSettings

  def initIfNeeded(settings: Settings) = if (!initialized) {
    val panel = $("#settings-content")
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".pattern-option", panel), jq => {
      val p = jq.data("pattern").toString match {
        case "none" => None
        case x => Some(x)
      }
      ThemeService.applyPattern(p)
      currentSettings = currentSettings.copy(backgroundPattern = p)
    })

    val picker = js.Dynamic.global.$("#settings-color-picker")

    picker.spectrum(js.Dynamic.literal(
      "color" -> settings.backgroundColor,
      "flat" -> true,
      //"showInput" -> true,
      "showButtons" -> false,
      "preferredFormat" -> "hex3",
      "move" -> colorChange _,
      "change" -> colorChange _
    ))

    ThemeService.applyColor(settings.backgroundColor)
    picker.spectrum("set", settings.backgroundColor)
    ThemeService.applyPattern(settings.backgroundPattern)

    show(settings)
    initialized = true
  }

  def show(settings: Settings) = {
    originalSettings = settings
    currentSettings = originalSettings

    val picker = js.Dynamic.global.$("#settings-color-picker")
    picker.spectrum("set", settings.backgroundColor)
  }

  private[this] def colorChange(color: js.Dynamic) = {
    val hex = color.toHexString().toString
    ThemeService.applyColor(hex)
    currentSettings = currentSettings.copy(backgroundColor = hex)
    true
  }
}
