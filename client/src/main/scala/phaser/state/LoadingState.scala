package phaser.state

import com.definitelyscala.phaser.State
import models.settings.CardSet

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class LoadingState extends State {
  override def preload() = {
    val assetRoot = "/assets/"
    val cardSet = CardSet.Default

    game.load.spritesheet("empty-piles", assetRoot + "images/cards/empty-a.png", cardSet.w.toDouble, cardSet.h.toDouble)

    game.load.image("card-blank", assetRoot + "images/cards/blank.png")
    game.load.image("card-back", assetRoot + "images/cards/back-" + "a" + ".png")
    game.load.spritesheet("card-suits", assetRoot + "images/cards/suits-" + "a" + ".png", 200, 200)
    game.load.spritesheet("card-ranks", assetRoot + "images/cards/ranks-" + "a" + ".png", 200, 200)
    game.load.spritesheet("card-faces", assetRoot + "images/cards/face-cards-" + "a" + ".png", 200, 300)
  }

  override def create() = {
    game.state.start("gameplay", clearWorld = false, clearCache = false)
  }
}
