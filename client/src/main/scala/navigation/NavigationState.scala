package navigation

import java.util.NoSuchElementException

import org.scalajs.jquery.{jQuery => $}

import enumeratum.values._

sealed abstract class NavigationState(val value: String) extends StringEnumEntry {
  override def toString = value
  lazy val element = {
    val el = $(s"#panel-$value")
    if (el.length == 0) {
      throw new IllegalStateException(s"Missing dom element [panel-$value].")
    }
    el
  }
}

object NavigationState extends StringEnum[NavigationState] with StringUPickleEnum[NavigationState] {
  case object Loading extends NavigationState("loading")
  case object Home extends NavigationState("home")
  case object Games extends NavigationState("games")
  case object Help extends NavigationState("help")
  case object Play extends NavigationState("play")
  case object Settings extends NavigationState("settings")
  case object Status extends NavigationState("status")

  override val values = findValues
}
