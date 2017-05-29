package phaser

import audio.AudioService
import client.SolitaireGG
import com.definitelyscala.phaser._
import models.{RequestMessage, SelectPile}
import models.game.PossibleMove
import org.scalajs.dom
import org.scalajs.dom.raw.UIEvent
import phaser.card.CardImages
import phaser.gameplay.Gameplay
import phaser.playmat.Playmat
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

  val gameplay = new Gameplay(this, gg.settings.getSettings, gg.onPhaserLoadComplete)

  var possibleMoves: Seq[PossibleMove] = Nil

  private[this] var images: Option[CardImages] = None
  def setImages(i: CardImages) = images = Some(i)
  def getImages = images.getOrElse(throw new IllegalStateException("Images not loaded."))

  def getUserId = gg.profile.getUserId
  def getDeviceId = gg.profile.deviceId
  def getSettings = gg.settings.getSettings

  private[this] var audio: Option[AudioService] = None
  def initAudio() = audio = Some(new AudioService(this))
  def playAudio(key: String) = if (getSettings.audio) {
    utils.Logging.info(s"Playing audio [$key].")
    audio.map(_.play(key))
  }

  private[this] var playmat: Option[Playmat] = None
  def setPlaymat(p: Option[Playmat]) = playmat = p
  def getPlaymat = playmat.getOrElse(throw new IllegalStateException("Playmat not loaded."))

  def resize(e: UIEvent): Unit = {
    scale.setGameSize(dom.window.innerWidth, dom.window.innerHeight)
    playmat.foreach(_.resizer.resizeIfChanged(animate = true))
  }

  def start() = PhaserLifecycle.start(this)
  def onWin() = gg.network.sendMessage(PhaserLifecycle.onWin(this))
  def onLoss() = gg.network.sendMessage(PhaserLifecycle.onLoss(this))

  def sendMove(msg: RequestMessage) = {
    audio.map(_.onMove(msg))
    gameplay.services.requests.handle(msg)
  }
}
