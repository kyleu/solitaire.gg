package settings

import models.settings.{MenuPosition, Settings}
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}
import utils.TemplateUtils

import scala.scalajs.js

object SettingsPanel {
  private[this] var initialized = false
  private[this] var originalSettings = Settings.default
  private[this] var currentSettings = originalSettings
  def getCurrentSettings = currentSettings

  private[this] val panel = $("#settings-content .content")

  def show(settings: Settings) = {
    originalSettings = settings
    currentSettings = originalSettings

    val picker = js.Dynamic.global.$("#settings-color-picker")
    picker.spectrum("set", settings.backgroundColor)

    $("#settings-menu-position-top").prop("checked", settings.menuPosition == MenuPosition.Top)
    $("#settings-menu-position-bottom").prop("checked", settings.menuPosition == MenuPosition.Bottom)
  }

  def initIfNeeded(settings: Settings) = if (!initialized) {
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initMenuPosition()
    initBackgroundColor()
    initBackgroundPattern()

    ThemeService.applyColorAndPattern(settings.backgroundColor, settings.backgroundPattern)

    show(settings)
    initialized = true
  }

  private[this] def initMenuPosition() = {
    $("#settings-menu-position-top", panel).change((jq: JQueryEventObject) => {
      $("body").removeClass("bottom-nav").addClass("top-nav")
      currentSettings = currentSettings.copy(menuPosition = MenuPosition.Top)
    })
    $("#settings-menu-position-bottom", panel).change((jq: JQueryEventObject) => {
      $("body").removeClass("top-nav").addClass("bottom-nav")
      currentSettings = currentSettings.copy(menuPosition = MenuPosition.Bottom)
    })
  }

  private[this] def initBackgroundColor() = js.Dynamic.global.$("#settings-color-picker", panel).spectrum(js.Dynamic.literal(
    //"color" -> settings.backgroundColor,
    "flat" -> true,
    //"showInput" -> true,
    "showButtons" -> false,
    "preferredFormat" -> "hex3",
    "move" -> colorChange _,
    "change" -> colorChange _
  ))

  private[this] def initBackgroundPattern() = TemplateUtils.clickHandler($(".pattern-option", panel), jq => {
    val p = jq.data("pattern").toString match {
      case "none" => None
      case x => Some(x)
    }
    ThemeService.applyPattern(p)
    currentSettings = currentSettings.copy(backgroundPattern = p)
  })

  private[this] def colorChange(color: js.Dynamic) = {
    val hex = color.toHexString().toString
    ThemeService.applyColor(hex)
    currentSettings = currentSettings.copy(backgroundColor = hex)
    true
  }
}
