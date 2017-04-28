package phaser.card

import com.definitelyscala.phaser.Easing.Easing
import models.{MoveCards, SelectCard}

import scala.scalajs.js
import scala.scalajs.js.Date

trait CardInputHelper {
  protected[this] def canSelectCard(card: CardSprite) = {
    var valid = false
    card.phaser.possibleMoves.moves.foreach { move =>
      if (move.moveType == "select-card" && move.sourcePile == card.pileGroup.id) {
        if (move.cards.length == 1 && move.cards.headOption.contains(card.id)) {
          valid = true
        }
      }
    }
    valid
  }

  protected[this] def getMoveTarget(card: CardSprite) = card.phaser.possibleMoves.moves.find { move =>
    move.moveType == "move-cards" && move.sourcePile == card.pileGroup.id && move.cards.length == 1 && move.cards.headOption.contains(card.id)
  }

  protected[this] def click(card: CardSprite) = {
    if (canSelectCard(card)) {
      card.phaser.sendMove(SelectCard(card = card.id, pile = card.pileGroup.id, auto = false))
    } else {
      var now = new Date().getTime()
      if (card.lastClicked.isDefined && (now - card.lastClicked.get) < CardInput.doubleClickThresholdMs) {
        getMoveTarget(card).foreach { moveTarget =>
          val tgt = moveTarget.targetPile.getOrElse(throw new IllegalStateException("Move has no target pile."))
          card.phaser.sendMove(MoveCards(cards = Seq(card.id), src = card.pileGroup.id, tgt = tgt, auto = false))
        }
        card.lastClicked = None
      } else {
        card.lastClicked = Some(now)
      }
      var scaleTween = card.game.add.tween(card.scale)
      val props = js.Dynamic.literal("x" -> 1.0, "y" -> 1.0)
      scaleTween.to(props, 200, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      scaleTween.start()
    }
  }

  protected[this] def getAngle(card: CardSprite, xDelta: Double) = {
    val threshold = card.game.time.now - 300
    card.inertiaHistory = card.inertiaHistory.filter(_._1 >= threshold) :+ (card.game.time.now -> xDelta)

    var totalDelta = 0.0
    card.inertiaHistory.foreach { inertiaHist =>
      totalDelta += inertiaHist._2
    }
    var angle = totalDelta / card.inertiaHistory.length / 2.0
    val maxAngle = 15.0 + (3.0 * card.dragIndex.getOrElse(0))
    if (angle > maxAngle) {
      angle = maxAngle
    }
    if (angle < -maxAngle) {
      angle = -maxAngle
    }
    if (card.anchorPointY > 0) {
      angle = -angle
    }
    angle
  }
}
