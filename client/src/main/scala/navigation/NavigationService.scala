package navigation

import org.scalajs.dom

object NavigationService {
  lazy val isLocal = !dom.window.location.protocol.startsWith("http")
  lazy val assetRoot = if (isLocal) { "assets/" } else { "/assets/" }
}

class NavigationService(onStateChange: (NavigationState, NavigationState, Seq[String]) => Unit) {
  def initialAction() = {
    val args = dom.window.location.pathname.stripPrefix("/beta").stripPrefix("/").split("/").map(_.trim).filter(_.nonEmpty)
    val state = NavigationState.withValueOpt(args.headOption.getOrElse("games")).getOrElse(NavigationState.Games)
    navigate(state, args.drop(1))
  }

  private[this] var currentState: NavigationState = NavigationState.Loading
  private[this] val delay = 500

  NavigationState.values.foreach {
    case s if s != currentState => s.element.hide()
    case _ => // noop
  }

  def getState = currentState

  def setPath(state: NavigationState, args: Seq[String]) = {
    val url = if (args.isEmpty) {
      if (state == NavigationState.Games) { "/" } else { s"/${state.value}" }
    } else {
      s"/${state.value}/${args.mkString("/")}"
    }
    if (!NavigationService.isLocal) {
      dom.window.history.replaceState("", state.value, url)
    }
  }

  def navigate(state: NavigationState, args: Seq[String] = Nil, allowSelf: Boolean = false) = if (state == currentState) {
    if (allowSelf) {
      util.Logging.debug(s"State self-transition for [$currentState] with args [${args.mkString(", ")}].")
      onStateChange(currentState, state, args)
      setPath(state, args)
    } else {
      util.Logging.warn(s"State transition to self [$currentState].")
    }
  } else {
    util.Logging.debug(s"State transitioning from [$currentState] to [$state] with args [${args.mkString(", ")}].")
    onStateChange(currentState, state, args)
    setPath(state, args)
    currentState.element.fadeOut(delay)
    state.element.fadeIn(delay)
    currentState = state
  }
}
