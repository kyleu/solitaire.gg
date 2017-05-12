package settings

import models.settings.Settings
import org.scalajs.jquery.{jQuery => $}
import utils.TemplateUtils

import scala.scalajs.js

object SettingsPanel {
  private[this] var initialized = false

  def initIfNeeded(settings: Settings) = if (!initialized) {
    val panel = $("#settings-content")
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    TemplateUtils.clickHandler($(".pattern-option", panel), jq => {
      ThemeService.applyPattern(jq.data("pattern").toString)
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
    ThemeService.applyPattern(settings.backgroundPattern.getOrElse("none"))

    initialized = true
  }

  private[this] def colorChange(color: js.Dynamic) = {
    ThemeService.applyColor(color.toHexString().toString)
    true
  }
}
