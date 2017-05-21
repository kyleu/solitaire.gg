package settings

import models.settings._
import org.scalajs.jquery.{jQuery => $}

import scala.scalajs.js

object SettingsPanel {
  private[this] var initialized = false
  private[this] var originalSettings = Settings.default
  private[this] var current = originalSettings
  def getCurrentSettings = current
  def setCurrentSettings(settings: Settings) = current = settings

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
    SettingsPanelInit.init(settings)
    initialized = true
  }
}
