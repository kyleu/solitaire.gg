package phaser.card

import utils.PhaserUtils

import scala.scalajs.js

object CardTweens {
  def tweenCardTo(card: CardSprite, x: Double, y: Double, angle: Double, emitWhenComplete: Boolean = false) {
    val time = 500

    val scaleTween = card.game.add.tween(card.scale)
    val props = js.Dynamic.literal("x" -> 1.0, "y" -> 1.0)
    scaleTween.to(props, 200, "Linear", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    scaleTween.start()

    if (x != card.x || y != card.y) {
      val xTween = card.game.add.tween(card)
      val xProps = js.Dynamic.literal("x" -> x, "angle" -> angle)
      xTween.to(xProps, time.toDouble, "Cubic.easeOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      PhaserUtils.addToSignal(xTween.onComplete, {
        card.actualX = Some(x)
        card.tweening = false
        if (emitWhenComplete) {
          card.phaser.getPlaymat.emitter.emitFor(card)
        }
        card.width = card.originalWidth
      })
      xTween.start()

      val bounce = (y == card.y) && (Math.abs(card.x - x) > card.width)
      if (bounce) {
        val targetY = y - (card.height * 0.05)
        val yTween = card.game.add.tween(card)
        yTween.to(js.Dynamic.literal("y" -> targetY), time * 0.5, "Cubic.easeOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
        PhaserUtils.addToSignal(yTween.onComplete, {
          val yTween2 = card.game.add.tween(card)
          yTween2.to(js.Dynamic.literal("y" -> y), time * 0.5, "Cubic.easeIn", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
          yTween2.start()
        })
        yTween.start()
      } else {
        card.game.add.tween(card).to(js.Dynamic.literal("y" -> y), time.toDouble, "Cubic.easeOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      }

      card.tweening = true
    }
  }

  def tweenRemove(card: CardSprite) = {
    val tween = card.game.add.tween(card)
    tween.to(js.Dynamic.literal("alpha" -> 0), 400, "Linear", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    PhaserUtils.addToSignal(tween.onComplete, card.phaser.getPlaymat.remove(card))
    card.tweening = true
    tween.start()
  }

  def tweenRestore(card: CardSprite) = {
    //card.game.playmat.add(card)
    val tween = card.game.add.tween(card)
    tween.to(js.Dynamic.literal("alpha" -> 1), 400, "Linear", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    PhaserUtils.addToSignal(tween.onComplete, card.destroy())
    card.tweening = true
    tween.start()
  }

  def tweenPickUp(card: CardSprite) = {
    val tween = card.game.add.tween(card.scale)
    tween.to(js.Dynamic.literal("x" -> 1.05, "y" -> 1.05), 100, "Linear", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    tween.start()
  }

  def tweenFlip(card: CardSprite, faceUp: Boolean) {
    val origWidth = card.width
    val hideTween = card.game.add.tween(card)
    hideTween.to(js.Dynamic.literal("width" -> origWidth / 5), 100, "Cubic.easeOut", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    PhaserUtils.addToSignal(hideTween.onComplete, {
      card.updateSprite(faceUp)
      val showTween = card.game.add.tween(card)
      showTween.to(js.Dynamic.literal("width" -> origWidth), 100, "Cubic.easeIn", autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      PhaserUtils.addToSignal(showTween.onComplete, {
        card.tweening = false
        card.width = origWidth
      })
      showTween.start()
    })
    hideTween.start()

    card.tweening = true
  }
}
