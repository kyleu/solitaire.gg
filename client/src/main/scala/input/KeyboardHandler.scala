package input

import com.definitelyscala.phaser.Keyboard
import phaser.PhaserGame
import utils.PhaserUtils

class KeyboardHandler(phaser: PhaserGame, onInput: String => Unit) {
  val helpKey = phaser.input.keyboard.addKey(Keyboard.H)
  PhaserUtils.addToSignal(helpKey.onDown, onInput("Help"))

  val undoKey = phaser.input.keyboard.addKey(Keyboard.Z)
  PhaserUtils.addToSignal(undoKey.onDown, onInput("Undo"))

  val redoKey = phaser.input.keyboard.addKey(Keyboard.Y)
  PhaserUtils.addToSignal(redoKey.onDown, onInput("Redo"))

  val hideModalKey = phaser.input.keyboard.addKey(Keyboard.ESC)
  PhaserUtils.addToSignal(hideModalKey.onDown, { onInput("ToggleMenu") })

  val debugKey = phaser.input.keyboard.addKey(Keyboard.QUESTION_MARK)
  PhaserUtils.addToSignal(debugKey.onDown, { onInput("ToggleDebug") })

  val upKey = phaser.input.keyboard.addKey(Keyboard.UP)
  PhaserUtils.addToSignal(upKey.onDown, onInput("PreviousCard"))
  val downKey = phaser.input.keyboard.addKey(Keyboard.DOWN)
  PhaserUtils.addToSignal(downKey.onDown, onInput("NextCard"))
  val leftKey = phaser.input.keyboard.addKey(Keyboard.LEFT)
  PhaserUtils.addToSignal(leftKey.onDown, onInput("PreviousPile"))
  val rightKey = phaser.input.keyboard.addKey(Keyboard.RIGHT)
  PhaserUtils.addToSignal(rightKey.onDown, onInput("NextPile"))
}
