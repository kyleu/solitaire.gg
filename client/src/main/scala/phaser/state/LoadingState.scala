package phaser.state

import com.definitelyscala.phaser.State
import models.settings.Settings

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class LoadingState(settings: Settings) extends State {
  override def preload() = {
    val assetRoot = "/assets/"

    val size = "hd"

    game.load.audio("audio", assetRoot + "audio/audio.ogg")

    game.load.image("card-blank", assetRoot + s"images/cards/$size/blank/${settings.cardBlank.value}.png")
    game.load.image("card-back", assetRoot + s"images/cards/$size/back/${settings.cardBack.value}.png")

    val stencilSize = size match {
      case "hd" => 200.0 -> 200.0
      case _ => throw new IllegalStateException(s"Unhandled size [$size].")
    }

    game.load.spritesheet("card-suits", assetRoot + s"images/cards/$size/suits/${settings.cardSuits.value}.png", stencilSize._1, stencilSize._2)
    game.load.spritesheet("card-ranks", assetRoot + s"images/cards/$size/ranks/${settings.cardRanks.value}.png", stencilSize._1, stencilSize._2)

    val faceSize = size match {
      case "hd" => 200.0 -> 300.0
      case _ => throw new IllegalStateException(s"Unhandled size [$size].")
    }

    game.load.spritesheet("card-faces", assetRoot + s"images/cards/$size/faces/${settings.cardFaces.value}.png", faceSize._1, faceSize._2)
  }

  override def create() = {
    game.state.start("gameplay", clearWorld = false, clearCache = false)
  }
}
