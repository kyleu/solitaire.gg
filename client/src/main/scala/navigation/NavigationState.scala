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
  case object Menu extends NavigationState("menu")
  case object List extends NavigationState("list")
  case object Help extends NavigationState("help")
  case object Game extends NavigationState("game")
  case object Settings extends NavigationState("settings")
  case object Status extends NavigationState("status")

  override val values = findValues

  def fromString(s: String) = try {
    NavigationState.withValue(s)
  } catch {
    case _: NoSuchElementException => NavigationState.Game
  }
}
