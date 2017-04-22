package phaser.card

import com.definitelyscala.phaser.Sprite
import models.card.{Rank, Suit}
import phaser.PhaserGame

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class CardSprite(
    phaser: PhaserGame,
    id: Int,
    var rank: Rank,
    var suit: Suit,
    var faceUp: Boolean,
    initialX: Int,
    initialY: Int
) extends Sprite(phaser, initialX.toDouble, initialY.toDouble) {
  if (faceUp) {
    val tex = phaser.getImages.textures(rank.toChar.toString + suit.toChar)
    loadTexture(tex)
  } else {
    loadTexture("card-back")
  }

  anchor.x = 0.5
  anchor.y = 0.5

  inputEnabled = true

  private[this] var dragging = false
  private[this] var inertiaHistory = Seq.empty[Double]

}
