import models.user.{ CardPreferences, UserPreferences }
import org.scalajs.dom

trait SolitairePreferenceHelper {
  def handleSetPreference(name: String, value: String) = {
    val current = Option(dom.localStorage.getItem(name))
    dom.localStorage.setItem("preferences." + name, value)
    println(name + ": " + value + " (originally " + current + ").")
  }

  def readPreferences() = UserPreferences(
    color = Option(dom.localStorage.getItem("preferences.color")).getOrElse("greyblue"),
    cards = CardPreferences(
      back = Option(dom.localStorage.getItem("preferences.card.back")).getOrElse("a"),
      layout = Option(dom.localStorage.getItem("preferences.card.layout")).getOrElse("a"),
      ranks = Option(dom.localStorage.getItem("preferences.card.ranks")).getOrElse("a"),
      suits = Option(dom.localStorage.getItem("preferences.card.suits")).getOrElse("a"),
      faceCards = Option(dom.localStorage.getItem("preferences.card.faceCards")).getOrElse("a")
    ),
    autoFlip = Option(dom.localStorage.getItem("preferences.autoFlip")).map(_.toBoolean).getOrElse(true),
    audio = Option(dom.localStorage.getItem("preferences.audio")).exists(_.toBoolean),
    gamepad = Option(dom.localStorage.getItem("preferences.gamepad")).exists(_.toBoolean)
  )
}
