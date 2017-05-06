package navigation

import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}

class NavigationService(onStateChange: (NavigationState, NavigationState, Seq[String]) => Unit) {
  def initialAction() = {
    val args = dom.window.location.pathname.stripPrefix("/beta").stripPrefix("/").split("/").map(_.trim).filter(_.nonEmpty)
    val state = NavigationState.fromString(args.headOption.getOrElse("menu"))
    navigate(state, args.drop(1))
  }

  private[this] var currentState: NavigationState = NavigationState.Loading
  private[this] val delay = 500
  def getState = currentState

  NavigationState.values.foreach {
    case s if s != currentState => s.element.hide()
    case _ => // noop
  }

  def navigate(state: NavigationState, args: Seq[String] = Nil) = if (state == currentState) {
    utils.Logging.warn(s"State transition to self [$currentState].")
  } else {
    utils.Logging.info(s"State transitioning from [$currentState] to [$state] with args [${args.mkString(", ")}].")
    onStateChange(currentState, state, args)
    currentState.element.fadeOut(delay)
    state.element.fadeIn(delay)
    currentState = state
  }

  private[this] var navPosition: Option[Boolean] = None

  def setNavPosition(shown: Boolean, top: Boolean) = {
    val b = $("body")
    val m = $("#menu-nav")
    navPosition match {
      case None => if (shown && top) {
        b.addClass("top-nav")
        m.fadeIn(delay)
      } else if (shown && !top) {
        b.addClass("bottom-nav")
        m.fadeIn(delay)
      }
      case Some(true) => if (!shown) {
        b.removeClass("top-nav")
      } else if (shown && !top) {
        b.removeClass("top-nav").addClass("bottom-nav")
      }
      case Some(false) => if (!shown) {
        b.removeClass("bottom-nav")
      } else if (shown && top) {
        b.removeClass("bottom-nav").addClass("top-nav")
      }
    }
    navPosition = if (shown && top) { Some(true) } else if (shown && !top) { Some(false) } else { None }
  }
}
