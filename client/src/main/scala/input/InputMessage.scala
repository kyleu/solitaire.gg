package input

import enumeratum._

sealed trait InputMessage extends EnumEntry

object InputMessage extends Enum[InputMessage] {
  case object Help extends InputMessage

  case object Undo extends InputMessage
  case object Redo extends InputMessage

  case object ToggleMenu extends InputMessage
  case object ToggleDebug extends InputMessage
  case object Sandbox extends InputMessage

  case object PreviousCard extends InputMessage
  case object NextCard extends InputMessage
  case object PreviousPile extends InputMessage
  case object NextPile extends InputMessage

  case object GamepadA extends InputMessage
  case object GamepadB extends InputMessage
  case object GamepadX extends InputMessage
  case object GamepadY extends InputMessage

  override val values = findValues
}
