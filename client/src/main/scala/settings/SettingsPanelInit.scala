package settings

import models.settings._
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}
import utils.TemplateUtils

import scala.scalajs.js

object SettingsPanelInit {
  private[this] val panel = $("#settings-content .content")

  def init(settings: Settings) = {
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initMenuPosition()

    radioChange("auto-flip", "true", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(autoFlip = true)))
    radioChange("auto-flip", "false", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(autoFlip = false)))

    radioChange("audio", "true", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(audioEnabled = true)))
    radioChange("audio", "false", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(audioEnabled = false)))

    CardBack.values.foreach(cb => radioChange("card-back", cb.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardBack = cb))
    }))
    CardBlank.values.foreach(cb => radioChange("card-blank", cb.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardBlank = cb))
    }))
    CardFaces.values.foreach(cf => radioChange("card-faces", cf.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardFaces = cf))
    }))
    CardLayout.values.foreach(cl => radioChange("card-layout", cl.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardLayout = cl))
    }))
    CardRanks.values.foreach(cr => radioChange("card-ranks", cr.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardRanks = cr))
    }))
    CardSuits.values.foreach(cs => radioChange("card-suits", cs.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(cardSuits = cs))
    }))

    initBackgroundColor()
    initBackgroundPattern()

    ThemeService.applyColorAndPattern(settings.backgroundColor, settings.backgroundPattern)

    SettingsPanel.show(settings)
  }

  private[this] def initMenuPosition() = {
    radioChange("menu-position", "top", () => {
      $("body").removeClass("bottom-nav").addClass("top-nav")
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(menuPosition = MenuPosition.Top))
    })
    radioChange("menu-position", "bottom", () => {
      $("body").removeClass("top-nav").addClass("bottom-nav")
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(menuPosition = MenuPosition.Bottom))
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
    SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(backgroundPattern = p))
  })

  private[this] def colorChange(color: js.Dynamic) = {
    val hex = color.toHexString().toString
    ThemeService.applyColor(hex)
    SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(backgroundColor = hex))
    true
  }

  private[this] def radioChange(k: String, v: String, onSelect: () => Unit) = $(s"#settings-$k-$v", panel).change((_: JQueryEventObject) => onSelect())
}
