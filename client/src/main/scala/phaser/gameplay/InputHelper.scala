package phaser.gameplay

import input.{GamepadHandler, InputMessage, KeyboardHandler}
import models.game.GameStateDebug
import phaser.PhaserGame

class InputHelper(phaser: PhaserGame) {
  new KeyboardHandler(phaser, onInput)
  new GamepadHandler(phaser, onInput)

  def onInput(i: InputMessage) = i match {
    case InputMessage.Sandbox => phaser.gameplay.onSandbox()
    case InputMessage.Undo => utils.Logging.info("Undo!")
    case InputMessage.Redo => utils.Logging.info("Redo!")
    case _ => utils.Logging.info(s"Unhandled input message [$i].")
  }
}
