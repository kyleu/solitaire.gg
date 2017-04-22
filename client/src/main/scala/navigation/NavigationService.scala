package navigation

import enumeratum._

object NavigationService {
  sealed abstract class State(val id: String) extends EnumEntry

  object State {
    case object Loading extends State("loading")
  }
}

class NavigationService {
  private[this] var currentState: NavigationService.State = NavigationService.State.Loading

  def getState = currentState

  def navigate(state: NavigationService.State) = if (state == currentState) {
    utils.Logging.info(s"State transition to self [$currentState].")
  } else {
    utils.Logging.info(s"State transitioned from [$currentState] to [$state].")
    currentState = state
  }
}
