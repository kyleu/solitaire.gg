package navigation

import enumeratum._
import NavigationService.State
import org.scalajs.jquery.{jQuery => $}

object NavigationService {
  sealed abstract class State(val id: String) extends EnumEntry {
    override def toString = id
    lazy val element = {
      val el = $(s"#panel-$id")
      if (el.length == 0) {
        throw new IllegalStateException(s"Missing dom element [panel-$id].")
      }
      el
    }
  }

  object State extends Enum[State] {
    case object Loading extends State("loading")
    case object Menu extends State("menu")
    case object List extends State("list")
    case object Help extends State("help")
    case object Game extends State("game")
    case object Settings extends State("settings")
    case object Status extends State("status")

    override val values = findValues
  }
}

class NavigationService(onStateChange: (State, State) => Unit) {
  private[this] var currentState: State = State.Loading
  private[this] val delay = 500
  def getState = currentState

  State.values.foreach {
    case s if s != currentState => s.element.hide()
    case _ => // noop
  }

  def navigate(state: State) = if (state == currentState) {
    utils.Logging.warn(s"State transition to self [$currentState].")
  } else {
    utils.Logging.info(s"State transitioned from [$currentState] to [$state].")
    onStateChange(currentState, state)
    if (currentState == State.Loading) {
      $("#menu-toggle").fadeIn(delay)
    }
    currentState.element.fadeOut(delay)
    state.element.fadeIn(delay)
    currentState = state
  }
}
