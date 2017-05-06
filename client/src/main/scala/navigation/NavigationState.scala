package navigation

import java.util.NoSuchElementException

import org.scalajs.jquery.{jQuery => $}

import enumeratum._

sealed abstract class NavigationState(val id: String) extends EnumEntry {
  override def toString = id
  lazy val element = {
    val el = $(s"#panel-$id")
    if (el.length == 0) {
      throw new IllegalStateException(s"Missing dom element [panel-$id].")
    }
    el
  }
}

object NavigationState extends Enum[NavigationState] {
  case object Loading extends NavigationState("loading")
  case object Menu extends NavigationState("menu")
  case object List extends NavigationState("list")
  case object Help extends NavigationState("help")
  case object Game extends NavigationState("game")
  case object Settings extends NavigationState("settings")
  case object Status extends NavigationState("status")

  override val values = findValues

  def fromString(s: String) = try {
    NavigationState.withNameInsensitive(s)
  } catch {
    case _: NoSuchElementException => NavigationState.Game
  }
}
