package navigation

import game.ActiveGame
import org.scalajs.dom
import org.scalajs.dom.raw.PopStateEvent
import util.Logging

object NavigationService {
  lazy val isLocal = !dom.window.location.protocol.startsWith("http")
  lazy val assetRoot = if (isLocal) { "/assets/" } else { "/assets/" }
}

class NavigationService(onStateChange: (NavigationState, NavigationState, Seq[String]) => Unit) {
  def initialAction() = {
    val args = dom.window.location.pathname.stripPrefix("/beta").stripPrefix("/").split("/").map(_.trim.stripSuffix(".html")).filter(_.nonEmpty)
    val state = NavigationState.withValueOpt(args.headOption.getOrElse("games")).getOrElse(NavigationState.Games)
    navigate(state, args.drop(1), allowSelf = true)
  }

  private[this] var currentState: NavigationState = NavigationState.Loading
  private[this] val delay = 500

  NavigationState.values.filter(_ != currentState).foreach(_.element.hide())

  def getState = currentState

  def setPath(state: NavigationState, args: Seq[String]) = {
    val url = if (args.isEmpty) {
      if (state == NavigationState.Games) { "/" } else { s"/${state.value}" }
    } else {
      s"/${state.value}/${args.mkString("/")}"
    }
    if (!NavigationService.isLocal && dom.document.location.pathname != url) {
      dom.window.history.pushState("", state.value, url)
    }
  }

  dom.window.onpopstate = (e: PopStateEvent) => initialAction()

  def games() = navigate(NavigationState.Games)
  def settings() = navigate(NavigationState.Settings)
  def generalHelp() = navigate(NavigationState.Help, allowSelf = true)
  def rulesHelp(rules: String) = navigate(NavigationState.Help, Seq(rules))
  def play(rules: Seq[String]) = navigate(NavigationState.Play, rules)
  def resume(activeGame: ActiveGame) = navigate(NavigationState.Play, Seq(activeGame.rulesId, activeGame.seed.toString))
  def resign() = navigate(NavigationState.Games, allowSelf = true)

  private[this] def navigate(state: NavigationState, args: Seq[String] = Nil, allowSelf: Boolean = false) = {
    if (state == currentState) {
      if (allowSelf) {
        Logging.debug(s"State self-transition for [$currentState] with args [${args.mkString(", ")}].")
        onStateChange(currentState, state, args)
        setPath(state, args)
      } else {
        Logging.warn(s"State transition to self [$currentState].")
      }
    } else {
      Logging.info(s"State transitioning from [$currentState] to [$state] with args [${args.mkString(", ")}].")
      onStateChange(currentState, state, args)
      setPath(state, args)
      if (currentState == NavigationState.Loading) {
        currentState.element.hide()
        state.element.show()
      } else {
        currentState.element.fadeOut(delay)
        state.element.fadeIn(delay)
      }
      currentState = state
    }
  }
}
