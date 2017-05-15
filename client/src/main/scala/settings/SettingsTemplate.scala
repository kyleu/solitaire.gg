package settings

import models.settings.{BackgroundPattern, Settings}
import utils.Messages

import scalatags.Text.all._

object SettingsTemplate {
  def forSettings(settings: Settings) = {
    val title = h4(Messages("settings.title"))

    val colorInput = div(input(`type` := "text", id := "settings-color-picker", value := settings.backgroundColor))

    val backgroundColor = div(div(cls := "color-palette theme")(colorInput))

    val backgroundPattern = div(
      div(cls := "pattern-palette theme")(
        BackgroundPattern.all.zipWithIndex.map { pattern =>
          val st = s"background-image: url(/assets/images/settings/patterns.png);background-position-x:-${pattern._2 * 32}px"
          div(cls := "pattern-option", data("pattern") := pattern._1, style := st, attr("title") := pattern._1)
        }
      )
    )

    div(title, backgroundColor, backgroundPattern)
  }
}
