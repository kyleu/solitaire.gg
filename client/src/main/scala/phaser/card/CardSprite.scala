package phaser.card

import com.definitelyscala.phaser.{Point, Pointer, Sprite}
import models.card.{Rank, Suit}
import phaser.PhaserGame
import phaser.pile.{PileGroup, PileDragHelper}
import util.NullUtils

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class CardSprite(
    val phaser: PhaserGame, val id: Int, initialRank: Rank, initialSuit: Suit, initialFaceUp: Boolean = false, initialX: Int, initialY: Int
) extends Sprite(phaser, initialX.toDouble, initialY.toDouble) {
  private[this] var rank = initialRank
  def getRank = rank
  private[this] var suit = initialSuit
  def getSuit = suit
  private[this] var faceUp = initialFaceUp
  def isFaceUp = faceUp

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

  inputEnabled = true

  anchor.x = 0.5
  anchor.y = 0.5

  updateSprite()

  val originalWidth = width

  events.onInputDown.add(onInputDown _, this, 0.0)
  events.onInputUp.add(onInputUp _, this, 0.0)

  def highlight() = tint = 0xffffff * 0.8
  def removeHighlight() = tint = 0xffffff

  def reveal(r: Rank, s: Suit, u: Boolean) = {
    rank = r
    suit = s
    if (u && (!faceUp)) {
      turnFaceUp()
    } else {
      util.Logging.warn(s"Reveal received for already revealed card [$id].")
    }
    updateSprite()
  }

  def updateSprite(fu: Boolean = faceUp) = {
    faceUp = fu
    val tex = if (faceUp) {
      phaser.getImages.textures(rank.value.toString + suit.value)
    } else {
      phaser.getImages.cardBack
    }
    loadTexture(tex)
    name = s"$id:${rank.value}${suit.value}${if (faceUp) { "+" } else { "-" }}"
  }

  def onInputDown(e: Any, p: Pointer) = if (Option(p.button).isEmpty || p.button.toString.toInt == 0) {
    if ((!tweening) && pileGroup.canDragFrom(this)) {
      PileDragHelper.startDrag(pileGroup, this, p)
    }
  }

  def onInputUp(e: js.Any, p: Pointer) = if (Option(p.button).isEmpty || p.button.toString.toInt == 0) {
    CardInput.onInputUp(e, p, this)
  }

  def startDrag(p: Pointer, dragIndex: Int) = CardInput.startDrag(p, dragIndex, this)
  def cancelDrag() = CardInput.cancelDrag(this)
  override def update() = CardInput.update(this)
  def turnFaceUp() = CardTweens.tweenFlip(this, faceUp = true)
  def turnFaceDown() = CardTweens.tweenFlip(this, faceUp = false)
}
