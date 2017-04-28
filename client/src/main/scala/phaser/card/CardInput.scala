package phaser.card

import com.definitelyscala.phaser.{Point, Pointer}
import phaser.pile.PileHelpers

import scala.scalajs.js

object CardInput extends CardInputHelper {
  val dragDeadZone = 10
  val doubleClickThresholdMs = 1000

  def startDrag(p: Pointer, dragIndex: Int, card: CardSprite) = {
    card.dragIndex = Some(dragIndex)
    card.inputOriginalPosition = Some(new Point(card.position.x, card.position.y))
    card.anchorPointX = ((p.positionDown.x - card.phaser.getPlaymat.x) / card.phaser.getPlaymat.scale.x) - card.x
    card.anchorPointY = ((p.positionDown.y - card.phaser.getPlaymat.y) / card.phaser.getPlaymat.scale.y) - card.y
    card.phaser.getPlaymat.emitter.bringToTop()
    card.bringToTop()
  }

  def onInputUp(e: js.Any, p: Pointer, card: CardSprite) = {
    if (card.dragIndex.isDefined) {
      PileHelpers.endDrag(card.pileGroup)
      card.phaser.getPlaymat.emitter.bringToTop()
    } else {
      val deltaX = Math.abs(p.positionDown.x - p.positionUp.x)
      val deltaY = Math.abs(p.positionDown.y - p.positionUp.y)
      if (deltaX < CardInput.dragDeadZone && deltaY < CardInput.dragDeadZone) {
        click(card)
      }
    }
  }

  def cancelDrag(card: CardSprite) = {
    card.inputOriginalPosition match {
      case Some(pos) =>
        val xDelta = Math.abs(card.x - pos.x)
        val yDelta = Math.abs(card.y - pos.y)
        if (xDelta < CardInput.dragDeadZone && yDelta < CardInput.dragDeadZone) {
          click(card)
        } else {
          CardTweens.tweenCardTo(card, pos.x, pos.y, 0)
        }
        card.dragIndex = None
        card.actualX = None
        card.inputOriginalPosition = None
      case None => throw new IllegalStateException("No inputOriginalPosition.")
    }
  }

  def update(card: CardSprite) = {
    card.animation match {
      case Some(a) => a()
      case None => card.dragIndex.foreach { di =>
        if (card.actualX.isEmpty) {
          card.actualX = Some(card.x)
        }
        val newX = ((card.game.input.x - card.phaser.getPlaymat.x) / card.phaser.getPlaymat.scale.x) - card.anchorPointX
        val xDelta = newX - card.actualX.get

        val angle = getAngle(card, xDelta)

        card.angle = angle
        card.actualX = Some(newX)

        val swayX = newX - (di * angle * 0.9)
        val newY = ((card.game.input.y - card.phaser.getPlaymat.y) / card.phaser.getPlaymat.scale.y) - card.anchorPointY

        card.x = swayX
        card.y = newY

        // if(card.phaser.getPlaymat.emitter !== undefined) {
        //   card.phaser.getPlaymat.emitter.on = true
        //   card.phaser.getPlaymat.emitter.emitX = card.x
        //   card.phaser.getPlaymat.emitter.emitY = card.y
        // }
      }
    }
  }
}
