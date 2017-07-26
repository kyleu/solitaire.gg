package settings

import models.settings._
import util.{EnumWithDescription, Messages}

import scalatags.Text.all._

object SettingsTemplate {
  lazy val forSettings = {
    val title = div(id := "settings-disclaimer", cls := "settings-section theme")(
      div(Messages("settings.disclaimer"))
    )

    val language = radiosFor("language", Language.values)
    val tilt = boolRadioFor("tilt", "Card Tilt", "No Card Tilt")
    val autoFlip = boolRadioFor("auto-flip", "Auto Flip", "No Auto Flip")
    val audio = boolRadioFor("audio", "Sound", "No Sound")
    val menuPosition = radiosFor("menu-position", Seq(MenuPosition.Top, MenuPosition.Bottom))
    val cardBack = radiosFor("card-back", CardBack.values)
    val cardBlank = radiosFor("card-blank", CardBlank.values)
    val cardFaces = radiosFor("card-faces", CardFaces.values)
    val cardLayout = radiosFor("card-layout", CardLayout.values)
    val cardRanks = radiosFor("card-ranks", CardRanks.values)
    val cardSuits = radiosFor("card-suits", CardSuits.values)

    val backgroundColor = div(cls := "settings-section theme")(div(cls := "cp-wrapper")(
      div(id := "colorpicker", cls := "cp")(),
      div(style := "clear: both;")(),
      div()(input(`type` := "text", id := "colorpicker-hex", value := ""))
    ))

    val backgroundPattern = div(cls := "settings-section theme")(BackgroundPattern.all.zipWithIndex.map { pattern =>
      val st = s"background-image: url(/assets/images/settings/patterns.png);background-position-x:-${pattern._2 * 32}px"
      div(cls := "pattern-option", data("pattern") := pattern._1, style := st, attr("title") := pattern._1)
    })

    div(
      title, language, tilt, autoFlip, audio, menuPosition,
      cardBack, cardBlank, cardFaces, cardLayout, cardRanks, cardSuits,
      backgroundColor, backgroundPattern
    )
  }

  private[this] def radiosFor(k: String, v: Seq[EnumWithDescription]) = div(cls := "settings-section theme")(v.map(r => radioFor(k, r)))

  private[this] def boolRadioFor(k: String, t: String, f: String) = div(cls := "settings-section theme")(
    div(cls := "settings-input")(
      input(`type` := "radio", name := k, id := s"settings-$k-true", value := "true"),
      label(`for` := s"settings-$k-true")(t)
    ),
    div(cls := "settings-input")(
      input(`type` := "radio", name := k, id := s"settings-$k-false", value := "false"),
      label(`for` := s"settings-$k-false")(f)
    )
  )

  private[this] def radioFor(k: String, v: EnumWithDescription) = div(cls := "settings-input")(
    input(`type` := "radio", name := k, id := s"settings-$k-${v.value}", value := v.value),
    label(`for` := s"settings-$k-${v.value}")(v.description)
  )
}
