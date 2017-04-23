package phaser

import com.definitelyscala.phaser._
import game.ActiveGame
import org.scalajs.dom
import org.scalajs.dom.raw.UIEvent
import phaser.card.CardImages
import phaser.playmat.Playmat
import phaser.state.{Gameplay, InitialState, LoadingState}
import settings.SettingsService
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
class PhaserGame(settingsService: SettingsService, onLoadComplete: () => Unit) extends Game(PhaserGame.options) {
  var initialized = false

  val gameplay = new Gameplay(this, settingsService.getSettings, onLoadComplete: () => Unit)
  var activeGame: Option[ActiveGame] = None

  private[this] var images: Option[CardImages] = None
  def setImages(i: CardImages) = images = Some(i)
  def getImages = images.getOrElse(throw new IllegalStateException("Images not loaded."))

  def getSettings = settingsService.getSettings

  private[this] var playmat: Option[Playmat] = None
  def getPlaymat = playmat.getOrElse(throw new IllegalStateException("Playmat not loaded."))

  def start() = {
    state.add("initialState", new InitialState())
    state.add("loading", new LoadingState())
    state.add("gameplay", gameplay)

    dom.window.onresize = (e: UIEvent) => utils.Logging.info("Resize!")

    state.start("initialState", clearWorld = false, clearCache = false)
  }

  def setActiveGame(ag: ActiveGame) = {
    utils.Logging.info(s"Game [${ag.gameRules.title}] started.")
    activeGame = Some(ag)
    playmat = Some(new Playmat(this, ag.gameState.pileSets, ag.gameRules.layout))
  }
}
