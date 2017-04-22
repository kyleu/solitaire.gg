package phaser

import com.definitelyscala.phaser._
import org.scalajs.dom
import org.scalajs.dom.raw.UIEvent
import phaser.state.{InitialState, LoadingState}
import utils.JsUtils

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object PhaserGame {
  protected val options = JsUtils.as[IGameConfig](js.Dynamic.literal(
    "width" -> "100%",
    "height" -> "100%",
    "renderer" -> Phaser.AUTO,
    "parent" -> "panel-game",
    //"state" -> "initialState",
    "transparent" -> true,
    "resolution" -> 2
  ))
}
@ScalaJSDefined
class PhaserGame extends Game(PhaserGame.options) {
  def start() = {
    state.add("initialState", new InitialState(), autoStart = false)
    state.add("loading", new LoadingState(), autoStart = false)

    dom.window.onresize = (e: UIEvent) => {
      utils.Logging.info("Resize!")
    }

    state.start("initialState", clearWorld = false, clearCache = false)
  }
}
