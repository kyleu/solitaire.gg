package phaser.gameplay

import client.SolitaireGG
import input.{GamepadHandler, InputContextService, InputMessage, KeyboardHandler}

class InputHelper(gg: SolitaireGG) {
  new KeyboardHandler(gg.phaser, onInput)
  new GamepadHandler(gg.phaser, onInput)

  def onInput(i: InputMessage): Unit = i match {
    case InputMessage.Sandbox => SolitaireGG.getActive.onSandbox()
    case InputMessage.ToggleDebug => toggleDebug()
    case InputMessage.ToggleMenu => gg.menu.toggleMenu()
    case InputMessage.Undo => gg.phaser.gameplay.undo()
    case InputMessage.Redo => gg.phaser.gameplay.redo()
    case _ => gg.phaser.gameplay.onInput(i)
  }

  private[this] def toggleDebug() = {
    import org.scalajs.jquery.{jQuery => $}
    val jq = $(".pdebug")
    if (jq.length == 1) {
      jq.fadeToggle()
    }
  }
}
