package input

import com.definitelyscala.phaser.{Gamepad, SinglePad}
import phaser.PhaserGame
import utils.PhaserUtils

import scala.scalajs.js

class GamepadHandler(phaser: PhaserGame, onInput: InputMessage => Unit) {
  phaser.input.gamepad.start()

  private[this] def onConnect(pad: SinglePad) = {
    val buttonStart = pad.getButton(Gamepad.XBOX360_START)
    PhaserUtils.addToSignal(buttonStart.onDown, onInput(InputMessage.ToggleMenu))

    val buttonA = pad.getButton(Gamepad.XBOX360_A)
    PhaserUtils.addToSignal(buttonA.onDown, onInput(InputMessage.Select))

    val buttonB = pad.getButton(Gamepad.XBOX360_B)
    PhaserUtils.addToSignal(buttonB.onDown, onInput(InputMessage.Cancel))

    //val buttonX = pad.getButton(Gamepad.XBOX360_X)
    //PhaserUtils.addToSignal(buttonX.onDown, onInput(InputMessage.GamepadX))

    //val buttonY = pad.getButton(Gamepad.XBOX360_Y)
    //PhaserUtils.addToSignal(buttonY.onDown, onInput(InputMessage.GamepadY))

    val buttonDPadLeft = pad.getButton(Gamepad.XBOX360_DPAD_LEFT)
    PhaserUtils.addToSignal(buttonDPadLeft.onDown, onInput(InputMessage.PreviousPile))

    val buttonDPadRight = pad.getButton(Gamepad.XBOX360_DPAD_RIGHT)
    PhaserUtils.addToSignal(buttonDPadRight.onDown, onInput(InputMessage.NextPile))

    val buttonDPadUp = pad.getButton(Gamepad.XBOX360_DPAD_UP)
    PhaserUtils.addToSignal(buttonDPadUp.onDown, onInput(InputMessage.PreviousCard))

    val buttonDPadDown = pad.getButton(Gamepad.XBOX360_DPAD_DOWN)
    PhaserUtils.addToSignal(buttonDPadDown.onDown, onInput(InputMessage.NextCard))
  }

  val pad1 = phaser.input.gamepad.pad1
  pad1.addCallbacks(pad1, js.Dynamic.literal("onConnect" -> onConnect _))

  val pad2 = phaser.input.gamepad.pad2
  pad2.addCallbacks(pad1, js.Dynamic.literal("onConnect" -> onConnect _))
}
