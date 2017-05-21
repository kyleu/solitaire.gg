package settings

import models.settings._
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}
import utils.TemplateUtils

import scala.scalajs.js

object SettingsPanel {
  private[this] var initialized = false
  private[this] var originalSettings = Settings.default
  private[this] var current = originalSettings
  def getCurrentSettings = current

  private[this] val panel = $("#settings-content .content")

  private[this] def forBoolean(s: String, v: Boolean) = {
    $(s"#settings-$s-true").prop("checked", v)
    $(s"#settings-$s-false").prop("checked", !v)
  }

  def show(settings: Settings) = {
    originalSettings = settings
    current = originalSettings

    val picker = js.Dynamic.global.$("#settings-color-picker")
    picker.spectrum("set", settings.backgroundColor)

    forBoolean("auto-flip", settings.autoFlip)
    forBoolean("audio", settings.audioEnabled)

    MenuPosition.values.foreach(p => $(s"#settings-menu-position-${p.value}").prop("checked", settings.menuPosition == p))
    CardBack.values.foreach(cb => $(s"#settings-card-back-${cb.value}").prop("checked", settings.cardBack == cb))
    CardBlank.values.foreach(cb => $(s"#settings-card-blank-${cb.value}").prop("checked", settings.cardBlank == cb))
    CardFaces.values.foreach(cf => $(s"#settings-card-faces-${cf.value}").prop("checked", settings.cardFaces == cf))
    CardLayout.values.foreach(cl => $(s"#settings-card-layout-${cl.value}").prop("checked", settings.cardLayout == cl))
    CardRanks.values.foreach(cr => $(s"#settings-card-ranks-${cr.value}").prop("checked", settings.cardRanks == cr))
    CardSuits.values.foreach(cs => $(s"#settings-card-suits-${cs.value}").prop("checked", settings.cardSuits == cs))
  }

  def initIfNeeded(settings: Settings) = if (!initialized) {
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initMenuPosition()

    radioChange("auto-flip", "true", () => current = current.copy(autoFlip = true))
    radioChange("auto-flip", "false", () => current = current.copy(autoFlip = false))

    radioChange("audio", "true", () => current = current.copy(audioEnabled = true))
    radioChange("ausio", "false", () => current = current.copy(audioEnabled = false))

    CardBack.values.foreach(cb => radioChange("card-back", cb.value, () => current = current.copy(cardBack = cb)))
    CardBlank.values.foreach(cb => radioChange("card-blank", cb.value, () => current = current.copy(cardBlank = cb)))
    CardFaces.values.foreach(cf => radioChange("card-faces", cf.value, () => current = current.copy(cardFaces = cf)))
    CardLayout.values.foreach(cl => radioChange("card-layout", cl.value, () => current = current.copy(cardLayout = cl)))
    CardRanks.values.foreach(cr => radioChange("card-ranks", cr.value, () => current = current.copy(cardRanks = cr)))
    CardSuits.values.foreach(cs => radioChange("card-suits", cs.value, () => current = current.copy(cardSuits = cs)))

    initBackgroundColor()
    initBackgroundPattern()

    ThemeService.applyColorAndPattern(settings.backgroundColor, settings.backgroundPattern)

    show(settings)
    initialized = true
  }

  private[this] def initMenuPosition() = {
    radioChange("menu-position", "top", () => {
      $("body").removeClass("bottom-nav").addClass("top-nav")
      current = current.copy(menuPosition = MenuPosition.Top)
    })
    radioChange("menu-position", "bottom", () => {
      $("body").removeClass("top-nav").addClass("bottom-nav")
      current = current.copy(menuPosition = MenuPosition.Bottom)
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
    current = current.copy(backgroundPattern = p)
  })

  private[this] def radioChange(k: String, v: String, onSelect: () => Unit) = $(s"#settings-$k-$v", panel).change((_: JQueryEventObject) => onSelect())

  private[this] def colorChange(color: js.Dynamic) = {
    val hex = color.toHexString().toString
    ThemeService.applyColor(hex)
    current = current.copy(backgroundColor = hex)
    true
  }
}
