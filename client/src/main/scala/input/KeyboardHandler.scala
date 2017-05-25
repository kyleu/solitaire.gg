package input

import com.definitelyscala.phaser.Keyboard
import phaser.PhaserGame
import utils.PhaserUtils

class KeyboardHandler(phaser: PhaserGame, onInput: InputMessage => Unit) {
  val helpKey = phaser.input.keyboard.addKey(Keyboard.H)
  PhaserUtils.addToSignal(helpKey.onDown, onInput(InputMessage.Help))

  val undoKey = phaser.input.keyboard.addKey(Keyboard.Z)
  PhaserUtils.addToSignal(undoKey.onDown, onInput(InputMessage.Undo))

  val redoKey = phaser.input.keyboard.addKey(Keyboard.Y)
  PhaserUtils.addToSignal(redoKey.onDown, onInput(InputMessage.Redo))

  val hideModalKey = phaser.input.keyboard.addKey(Keyboard.ESC)
  PhaserUtils.addToSignal(hideModalKey.onDown, { onInput(InputMessage.ToggleMenu) })

  val debugKey = phaser.input.keyboard.addKey(Keyboard.QUESTION_MARK)
  PhaserUtils.addToSignal(debugKey.onDown, { onInput(InputMessage.ToggleDebug) })

  val sandboxKey = phaser.input.keyboard.addKey(Keyboard.X)
  PhaserUtils.addToSignal(sandboxKey.onDown, { onInput(InputMessage.Sandbox) })

  val upKey = phaser.input.keyboard.addKey(Keyboard.UP)
  PhaserUtils.addToSignal(upKey.onDown, onInput(InputMessage.PreviousCard))
  val downKey = phaser.input.keyboard.addKey(Keyboard.DOWN)
  PhaserUtils.addToSignal(downKey.onDown, onInput(InputMessage.NextCard))
  val leftKey = phaser.input.keyboard.addKey(Keyboard.LEFT)
  PhaserUtils.addToSignal(leftKey.onDown, onInput(InputMessage.PreviousPile))
  val rightKey = phaser.input.keyboard.addKey(Keyboard.RIGHT)
  PhaserUtils.addToSignal(rightKey.onDown, onInput(InputMessage.NextPile))

  val spaceKey = phaser.input.keyboard.addKey(Keyboard.SPACEBAR)
  PhaserUtils.addToSignal(spaceKey.onDown, onInput(InputMessage.Select))
}
