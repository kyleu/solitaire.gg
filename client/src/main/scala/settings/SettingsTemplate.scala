package settings

import models.settings._
import utils.Messages

import scalatags.Text.all._

object SettingsTemplate {
  def forSettings(settings: Settings) = {
    val title = h4(Messages("settings.title"))

    val menuPosition = div(cls := "settings-section theme")(
      radioFor("menu-position", "top", "Top"),
      radioFor("menu-position", "bottom", "Bottom")
    )

    val autoFlip = div(cls := "settings-section theme")(radioFor("auto-flip", "true", "Auto Flip"), radioFor("auto-flip", "false", "No Auto Flip"))
    val audio = div(cls := "settings-section theme")(radioFor("audio", "true", "Sound"), radioFor("audio", "false", "No Sound"))
    val cardBack = div(cls := "settings-section theme")(CardBack.values.map(cb => radioFor("card-back", cb.value, cb.title)))
    val cardBlank = div(cls := "settings-section theme")(CardBlank.values.map(cb => radioFor("card-blank", cb.value, cb.title)))
    val cardFaces = div(cls := "settings-section theme")(CardFaces.values.map(cf => radioFor("card-faces", cf.value, cf.title)))
    val cardLayout = div(cls := "settings-section theme")(CardLayout.values.map(cl => radioFor("card-layout", cl.value, cl.title)))
    val cardRanks = div(cls := "settings-section theme")(CardRanks.values.map(cr => radioFor("card-ranks", cr.value, cr.title)))
    val cardSuits = div(cls := "settings-section theme")(CardSuits.values.map(cs => radioFor("card-suits", cs.value, cs.title)))

    val backgroundColor = div(cls := "settings-section theme")(
      input(`type` := "text", id := "settings-color-picker", value := settings.backgroundColor)
    )

    val backgroundPattern = div(cls := "settings-section theme")(
      BackgroundPattern.all.zipWithIndex.map { pattern =>
        val st = s"background-image: url(/assets/images/settings/patterns.png);background-position-x:-${pattern._2 * 32}px"
        div(cls := "pattern-option", data("pattern") := pattern._1, style := st, attr("title") := pattern._1)
      }
    )

    div(
      title, autoFlip, audio, menuPosition,
      cardBack, cardBlank, cardFaces, cardLayout, cardRanks, cardSuits,
      backgroundColor, backgroundPattern
    )
  }

  private[this] def radioFor(k: String, v: String, title: String) = div(cls := "settings-input")(
    input(`type` := "radio", name := k, id := s"settings-$k-$v", value := v),
    label(`for` := s"settings-$k-$v")(title)
  )
}
