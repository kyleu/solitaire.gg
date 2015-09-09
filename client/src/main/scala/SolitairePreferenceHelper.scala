import org.scalajs.dom

trait SolitairePreferenceHelper {
  def handleSetPreference(name: String, value: String) = {
    val current = Option(dom.localStorage.getItem(name))
    dom.localStorage.setItem(name, value)
    println(name + ": " + value + " (originally " + current + ").")
  }
}
