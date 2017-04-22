package phaser.state

import com.definitelyscala.phaser.State
import phaser.CardSet
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class LoadingState extends State {
  override def preload() = {
    val assetRoot = "/assets/"
    val cardSet = CardSet.default

    game.load.spritesheet("empty-piles", assetRoot + "images/cards/empty-a.png", cardSet.cardWidth.toDouble, cardSet.cardHeight.toDouble)

    var settings = PlayerSettings.default

    game.load.image("card-blank", assetRoot + "images/cards/blank.png")
    game.load.image("card-back", assetRoot + "images/cards/back-" + "a" + ".png")
    game.load.spritesheet("card-suits", assetRoot + "images/cards/suits-" + "a" + ".png", 200, 200)
    game.load.spritesheet("card-ranks", assetRoot + "images/cards/ranks-" + "a" + ".png", 200, 200)
    game.load.spritesheet("card-faces", assetRoot + "images/cards/face-cards-" + "a" + ".png", 200, 300)
  }

  override def create() = {
    utils.Logging.info("Loaded!")
  }
}
