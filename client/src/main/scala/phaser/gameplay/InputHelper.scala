package phaser.gameplay

import client.SolitaireGG
import input.{GamepadHandler, InputMessage, KeyboardHandler}
import phaser.PhaserGame

class InputHelper(phaser: PhaserGame) {
  new KeyboardHandler(phaser, onInput)
  new GamepadHandler(phaser, onInput)

  def onInput(i: InputMessage): Unit = i match {
    case InputMessage.Sandbox => SolitaireGG.getActive.onSandbox()
    case InputMessage.Undo => phaser.gameplay.undo()
    case InputMessage.Redo => phaser.gameplay.redo()
    case InputMessage.ToggleDebug => toggleDebug()
    case InputMessage.ToggleMenu => // TODO
    case _ => utils.Logging.info(s"Unhandled input message [$i].")
  }

  private[this] def toggleDebug() = {
    import org.scalajs.jquery.{jQuery => $}
    val jq = $(".pdebug")
    if (jq.length == 1) {
      jq.fadeToggle()
    }
  }
}
