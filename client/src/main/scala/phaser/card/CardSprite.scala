package phaser.card

import com.definitelyscala.phaser.{Point, Pointer, Sprite}
import models.card.Card
import phaser.PhaserGame
import phaser.pile.PileGroup
import utils.NullUtils

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class CardSprite(
    val phaser: PhaserGame,
    val card: Card,
    initialX: Int,
    initialY: Int
) extends Sprite(phaser, initialX.toDouble, initialY.toDouble) {
  val (id, rank, suit) = (card.id, card.r, card.s)

  name = card.toString

  inputEnabled = true

  anchor.x = 0.5
  anchor.y = 0.5

  var anchorPointX = 0.0
  var anchorPointY = 0.0

  var actualX: Option[Double] = None
  var lastClicked: Option[Double] = None

  var tweening = false
  var dragIndex: Option[Int] = None

  var inertiaHistory = Seq.empty[(Double, Double)]
  var inputOriginalPosition: Option[Point] = None

  var animation: Option[() => Unit] = None

  var pileOption: Option[PileGroup] = None
  def pileGroup = pileOption.getOrElse(throw new IllegalStateException("Card [] is missing a pile assignment."))
  var pileIndex = -1

  if (card.u) {
    val tex = phaser.getImages.textures(rank.toChar.toString + suit.toChar)
    loadTexture(tex)
  } else {
    loadTexture("card-back")
  }

  events.onInputDown.add(onInputDown _, this, 0.0)
  events.onInputUp.add(onInputUp _, this, 0.0)

  def updateSprite(fu: Boolean = card.u) {
    card.u = fu
    if (card.u) {
      val tex = phaser.getImages.textures(rank.toChar.toString + suit.toChar)
      loadTexture(tex)
    } else {
      loadTexture("card-back")
    }
  }

  def onInputDown(e: Any, p: Pointer) = {
    if (p.button == NullUtils.inst || p.button.toString.toInt == 0) {
      if (!tweening) {
        if (pileGroup.canDragFrom(this)) {
          pileGroup.startDrag(this, p)
        }
      }
    }
  }

  def startDrag(p: Pointer, dragIndex: Int) = {
    CardInput.startDrag(p, dragIndex, this)
  }

  def onInputUp(e: js.Any, p: Pointer) = {
    if (p.button == NullUtils.inst || p.button.toString.toInt == 0) {
      CardInput.onInputUp(e, p, this)
    }
  }

  def cancelDrag() = {
    CardInput.cancelDrag(this)
  }

  override def update() = {
    CardInput.update(this)
  }

  def turnFaceUp() = {
    CardTweens.tweenFlip(this, faceUp = true)
  }

  def turnFaceDown() = {
    CardTweens.tweenFlip(this, faceUp = false)
  }
}
