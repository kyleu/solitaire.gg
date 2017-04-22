package phaser

import com.definitelyscala.phaser.{Game, IGameConfig, Phaser}
import utils.JsUtils

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object PhaserGame {
  protected val options = JsUtils.as[IGameConfig](js.Dynamic.literal(
    "width" -> "100%",
    "height" -> "100%",
    "renderer" -> Phaser.AUTO,
    "parent" -> "game-container",
    //"state" -> "initialState",
    "transparent" -> true,
    "resolution" -> 2
  ))
}
@ScalaJSDefined
class PhaserGame extends Game(PhaserGame.options) {
  println("Phaser is phasering.")
}
