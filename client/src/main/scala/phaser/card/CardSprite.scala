package phaser.card

import com.definitelyscala.phaser.{Pointer, Sprite}
import models.card.{Rank, Suit}
import phaser.PhaserGame
import phaser.pile.PileGroup
import utils.NullUtils

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class CardSprite(
    val phaser: PhaserGame,
    val id: Int,
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

  var dragging = false
  var tweening = false
  var inertiaHistory = Seq.empty[Double]

  var pile: PileGroup = NullUtils.inst
  var pileIndex = -1

  events.onInputDown.add(onInputDown _, this, 0.0)
  events.onInputUp.add(onInputUp _, this, 0.0)

  def updateSprite(fu: Boolean = faceUp) {
    this.faceUp = fu
    if (this.faceUp) {
      val tex = phaser.getImages.textures(rank.toChar.toString + suit.toChar)
      this.loadTexture(tex)
    } else {
      this.loadTexture("card-back")
    }
  }

  def onInputDown(e: Any, p: Pointer) = {
    if (p.button == NullUtils.inst || p.button.toString.toInt == 0) {
      if (!tweening) {
        // if(pile.canDragFrom(this)) {
        // pile.startDrag(this, p)
        // }
      }
    }
  }

  def startDrag(p: Pointer, dragIndex: Int) = {
    //CardInput.startDrag(p, dragIndex, this)
  }

  def onInputUp(e: Any, p: Pointer) = {
    if (p.button == NullUtils.inst || p.button.toString.toInt == 0) {
      //CardInput.onInputUp(e, p, this)
    }
  }

  def cancelDrag() = {
    //CardInput.cancelDrag(this)
  }

  override def update() = {
    //CardInput.update(this)
  }

  def turnFaceUp() = {
    //Tweens.tweenFlip(this, true)
  }

  def turnFaceDown() = {
    //Tweens.tweenFlip(this, false)
  }
}
