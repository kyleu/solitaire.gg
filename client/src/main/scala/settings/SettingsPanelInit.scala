package settings

import models.settings._
import org.scalajs.dom
import org.scalajs.jquery.{JQueryEventObject, jQuery => $}
import util.TemplateUtils

import scala.scalajs.js

object SettingsPanelInit {
  private[this] val panel = $("#settings-content .content")

  var colorPicker: Option[js.Dynamic] = None

  def init(settings: Settings) = {
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initMenuPosition()

    Language.values.foreach(l => radioChange("language", l.value, () => {
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(language = l))
    }))

    radioChange("tilt", "true", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(tilt = true)))
    radioChange("tilt", "false", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(tilt = false)))

    radioChange("auto-flip", "true", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(autoFlip = true)))
    radioChange("auto-flip", "false", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(autoFlip = false)))

    radioChange("audio", "true", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(audio = true)))
    radioChange("audio", "false", () => SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(audio = false)))

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

    initBackgroundColor(SettingsPanel.getCurrentSettings.backgroundColor)
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

  private[this] def initBackgroundColor(c: String) = {
    val hexInput = $("#colorpicker-hex", panel)
    val cp = js.Dynamic.global.ColorPicker(dom.document.getElementById("colorpicker"), (hex: String) => {
      hexInput.value(hex)
      ThemeService.applyColor(hex)
      SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(backgroundColor = hex))
    })
    TemplateUtils.changeHandler(hexInput, jq => {
      colorPicker.map(_.setHex(jq.value()))
      true
    })
    colorPicker = Some(cp)
  }

  private[this] def initBackgroundPattern() = TemplateUtils.clickHandler($(".pattern-option", panel), jq => {
    val p = jq.data("pattern").toString match {
      case "none" => None
      case x => Some(x)
    }
    ThemeService.applyPattern(p)
    SettingsPanel.setCurrentSettings(SettingsPanel.getCurrentSettings.copy(backgroundPattern = p))
  })

  private[this] def radioChange(k: String, v: String, onSelect: () => Unit) = $(s"#settings-$k-$v", panel).change((_: JQueryEventObject) => onSelect())
}
