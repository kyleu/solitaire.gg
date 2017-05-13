package phaser

import com.definitelyscala.phaser._
import game.SolitaireGG
import models.settings.SettingsService
import models.{PossibleMove, RequestMessage}
import org.scalajs.dom
import org.scalajs.dom.raw.UIEvent
import phaser.card.CardImages
import phaser.gameplay.Gameplay
import phaser.playmat.Playmat
import phaser.state.{InitialState, LoadingState}
import utils.JsUtils

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

object PhaserGame {
  protected val options = JsUtils.as[IGameConfig](js.Dynamic.literal(
    "width" -> "100%",
    "height" -> "100%",
    "renderer" -> Phaser.AUTO,
    "parent" -> "panel-play",
    "transparent" -> true,
    "resolution" -> dom.window.devicePixelRatio
  ))
}
@ScalaJSDefined
class PhaserGame(gg: SolitaireGG) extends Game(PhaserGame.options) {
  this.antialias = true

  val gameplay = new Gameplay(this, gg.settings.getSettings, gg.onPhaserLoadComplete, gg.debug)

  var possibleMoves: Seq[PossibleMove] = Nil

  private[this] var images: Option[CardImages] = None
  def setImages(i: CardImages) = images = Some(i)
  def getImages = images.getOrElse(throw new IllegalStateException("Images not loaded."))

  def getSettings = gg.settings.getSettings

  private[this] var playmat: Option[Playmat] = None
  def setPlaymat(p: Option[Playmat]) = playmat = p
  def getPlaymat = playmat.getOrElse(throw new IllegalStateException("Playmat not loaded."))

  def resize(e: UIEvent): Unit = playmat match {
    case Some(p) =>
      scale.setGameSize(dom.window.innerWidth, dom.window.innerHeight)
      p.resizer.resizeIfChanged(animate = true)
    case None => // noop
  }

  def start() = {
    state.add("initialState", new InitialState())
    state.add("loading", new LoadingState(getSettings))
    state.add("gameplay", gameplay)

    dom.window.onresize = resize _
    state.start("initialState", clearWorld = false, clearCache = false)
  }

  def sendMove(msg: RequestMessage) = {
    if (gg.debug) { utils.Logging.info(s"Sending request message [$msg].") }
    gameplay.services.requests.handle(SettingsService.userId, msg)
  }
}
