package input

import enumeratum.values._

sealed abstract class InputMessage(val value: String) extends StringEnumEntry

object InputMessage extends StringEnum[InputMessage] with StringCirceEnum[InputMessage] {
  case object Help extends InputMessage("help")

  case object Undo extends InputMessage("undo")
  case object Redo extends InputMessage("redo")

  case object ToggleMenu extends InputMessage("toggle-menu")
  case object ToggleDebug extends InputMessage("toggle-debug")
  case object Sandbox extends InputMessage("sandbox")

  case object PreviousCard extends InputMessage("previous-card")
  case object NextCard extends InputMessage("next-card")
  case object PreviousPile extends InputMessage("previous-pile")
  case object NextPile extends InputMessage("next-pile")

  case object Select extends InputMessage("select")
  case object Cancel extends InputMessage("cancel")

  override val values = findValues
}
