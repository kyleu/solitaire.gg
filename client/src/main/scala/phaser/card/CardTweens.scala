package phaser.card

import com.definitelyscala.phaser.Easing.Easing

import scala.scalajs.js

object CardTweens {
  def tweenCardTo(card: CardSprite, x: Double, y: Double, angle: Double, emitWhenComplete: Boolean = false) {
    val time = 500

    val scaleTween = card.game.add.tween(card.scale)
    val props = js.Dynamic.literal("x" -> 1.0, "y" -> 1.0)
    scaleTween.to(props, 200, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    scaleTween.start()

    if (x != card.x || y != card.y) {
      val xTween = card.game.add.tween(card)
      val xProps = js.Dynamic.literal("x" -> x, "angle" -> angle)
      xTween.to(xProps, time.toDouble, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      xTween.onComplete.add(() => {
        // TODO card.actualX = x;
        card.tweening = false
        if (emitWhenComplete) {
          card.phaser.getPlaymat.emitter.emitFor(card)
        }
        // TODO card.width = card.originalWidth;
      }, card, 0.0)
      xTween.start()

      val bounce = (y == card.y) && (Math.abs(card.x - x) > card.width)
      if (bounce) {
        var targetY = y - (card.height * 0.05)
        var yTween = card.game.add.tween(card)
        yTween.to(js.Dynamic.literal("y" -> targetY), time * 0.5, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
        yTween.to(js.Dynamic.literal("y" -> y), time * 0.5, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
        yTween.start()
      } else {
        card.game.add.tween(card).to(js.Dynamic.literal("y" -> y), time.toDouble, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      }

      card.tweening = true
    }
  }

  def tweenRemove(card: CardSprite) = {
    val tween = card.game.add.tween(card)
    tween.to(js.Dynamic.literal("alpha" -> 0), 400, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    tween.onComplete.add(() => {
      card.phaser.getPlaymat.remove(card)
    }, card, 0.0)
    card.tweening = true
    tween.start()
  }

  def tweenRestore(card: CardSprite) = {
    //card.game.playmat.add(card)
    var tween = card.game.add.tween(card)
    tween.to(js.Dynamic.literal("alpha" -> 1), 400, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    tween.onComplete.add(() => {
      card.destroy()
    }, card, 0.0)
    card.tweening = true
    tween.start()
  }

  def tweenPickUp(card: CardSprite) = {
    var tween = card.game.add.tween(card.scale)
    tween.to(js.Dynamic.literal("x" -> 1.05, "y" -> 1.05), 100, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    tween.start()
  }

  def tweenFlip(card: CardSprite, faceUp: Boolean) {
    var origWidth = card.width
    var hideTween = card.game.add.tween(card)
    hideTween.to(js.Dynamic.literal("width" -> origWidth / 5), 100, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
    hideTween.onComplete.add(() => {
      card.updateSprite(faceUp)
      var showTween = card.game.add.tween(card)
      showTween.to(js.Dynamic.literal("width" -> origWidth), 100, Easing.Default, autoStart = true, delay = 0.0, repeat = 0.0, yoyo = false)
      showTween.onComplete.add(() => {
        card.tweening = false
        card.width = origWidth
      }, card, 0.0)
      showTween.start()
    }, card, 0.0)
    hideTween.start()

    card.tweening = true
  }
}
