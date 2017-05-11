package settings

import models.settings.{BackgroundColor, BackgroundPattern, Settings}
import utils.Messages

import scalatags.Text.all._
import org.scalajs.jquery.{jQuery => $}

object SettingsTemplate {
  private[this] var initialized = false

  def initIfNeeded(settings: Settings) = {
    val panel = $("#settings-content")
    val content = SettingsTemplate.forSettings(settings)
    panel.html(content.toString)

    initialized = true
  }

  def forSettings(settings: Settings) = {
    val title = h4(Messages("settings.title"))

    val backgroundColor = div(
      h5("Table Color"),
      div(cls := "color-palette")(
        BackgroundColor.all.map { color =>
          div(cls := "color-option", data("color") := color._2, style := s"background-color: #${color._2}")
        }
      )
    )

    val backgroundPattern = div(
      h5("Table Pattern"),
      div(cls := "pattern-palette")(
        div(cls := "pattern-option", data("pattern") := "none") +: BackgroundPattern.all.map { pattern =>
          div(cls := "pattern-option", data("pattern") := pattern, style := s"background-image: url(/assets/images/background/$pattern.png")
        }
      )
    )

    div(title, backgroundColor, backgroundPattern)
  }
}
