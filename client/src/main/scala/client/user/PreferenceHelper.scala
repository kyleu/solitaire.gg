package client.user

import models.game.GameState
import models.user.{CardPreferences, UserPreferences}
import org.scalajs.dom

trait PreferenceHelper {
  protected[this] var preferences = readPreferences()
  setUi()

  def handleSetPreference(name: String, value: String, gameState: GameState) = {
    dom.window.localStorage.setItem("preferences." + name, value)
    preferences = preferences.withNewValue(name, value)
    if (name == "auto-flip") {
      gameState.setAutoFlipOption(value.toBoolean)
    }
  }

  def readPreferences() = UserPreferences(
    color = Option(dom.window.localStorage.getItem("preferences.background-color")).getOrElse("greyblue"),
    cards = CardPreferences(
      back = Option(dom.window.localStorage.getItem("preferences.card-back")).getOrElse("a"),
      layout = Option(dom.window.localStorage.getItem("preferences.card-layout")).getOrElse("a"),
      ranks = Option(dom.window.localStorage.getItem("preferences.card-rank")).getOrElse("a"),
      suits = Option(dom.window.localStorage.getItem("preferences.card-suit")).getOrElse("a"),
      faceCards = Option(dom.window.localStorage.getItem("preferences.card-face")).getOrElse("a")
    ),
    autoFlip = Option(dom.window.localStorage.getItem("preferences.auto-flip")).forall(_.toBoolean),
    audio = Option(dom.window.localStorage.getItem("preferences.audio")).exists(_.toBoolean),
    gamepad = Option(dom.window.localStorage.getItem("preferences.gamepad")).exists(_.toBoolean)
  )

  def setUi() = {
    val bodyClassName = dom.document.body.className
    val bgIndex = bodyClassName.indexOf("background-")
    dom.document.body.className = bodyClassName.substring(0, bgIndex) + " background-" + preferences.color

    val buttons = dom.document.getElementsByClassName("btn")

    (0 until buttons.length).foreach { i =>
      val btn = buttons(i)

      val cn = btn.attributes.getNamedItem("class").value

      val colorIndexStart = cn.indexOf("btn-")
      var colorIndexEnd = cn.indexOf(" ", colorIndexStart)
      if (colorIndexEnd == -1) {
        colorIndexEnd = cn.length
      }
      val className = cn.substring(0, colorIndexStart) + "btn-" + preferences.color + cn.substring(colorIndexEnd)
      btn.attributes.getNamedItem("class").value = className
    }

    val elements = dom.document.getElementsByClassName("game-option")
    (0 until elements.length).foreach { i =>
      val element = elements(i)
      val c = element.attributes.getNamedItem("data-option-class").value
      val v = element.attributes.getNamedItem("data-option-value").value

      def setActive() = element.attributes.getNamedItem("class").value = "game-option active"
      def setInactive() = element.attributes.getNamedItem("class").value = "game-option"

      def tagBooleanEl(b: Boolean) = if ((b && v == "true") || ((!b) && v == "false")) { setActive() } else { setInactive() }
      def tagStringEl(s: String) = if (s == v) { setActive() } else { setInactive() }

      c match {
        case "background-color" => tagStringEl(preferences.color)
        case "card-back" => tagStringEl(preferences.cards.back)
        case "card-layout" => tagStringEl(preferences.cards.layout)
        case "card-rank" => tagStringEl(preferences.cards.ranks)
        case "card-suit" => tagStringEl(preferences.cards.suits)
        case "card-face" => tagStringEl(preferences.cards.faceCards)
        case "auto-flip" => tagBooleanEl(preferences.autoFlip)
        case "audio" => tagBooleanEl(preferences.audio)
        case "gamepad" => tagBooleanEl(preferences.gamepad)
      }
    }
  }
}
