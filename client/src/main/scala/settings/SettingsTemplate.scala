package settings

import models.settings.{BackgroundPattern, Settings}
import utils.Messages

import scalatags.Text.all._

object SettingsTemplate {
  def forSettings(settings: Settings) = {
    val title = h4(Messages("settings.title"))

    val menuPosition = div(cls := "settings-section theme")(
      radioFor("menu-position", "top", "Top"),
      radioFor("menu-position", "bottom", "Bottom")
    )

    val backgroundColor = div(cls := "settings-section theme")(
      input(`type` := "text", id := "settings-color-picker", value := settings.backgroundColor)
    )

    val backgroundPattern = div(cls := "settings-section theme")(
      BackgroundPattern.all.zipWithIndex.map { pattern =>
        val st = s"background-image: url(/assets/images/settings/patterns.png);background-position-x:-${pattern._2 * 32}px"
        div(cls := "pattern-option", data("pattern") := pattern._1, style := st, attr("title") := pattern._1)
      }
    )

    div(title, menuPosition, backgroundColor, backgroundPattern)
  }

  private[this] def radioFor(k: String, v: String, title: String) = div(cls := "settings-input")(
    input(`type` := "radio", name := k, id := s"settings-$k-$v", value := v),
    label(`for` := s"settings-$k-$v")(title)
  )
}
