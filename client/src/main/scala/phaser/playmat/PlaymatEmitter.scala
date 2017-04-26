package phaser.playmat

import com.definitelyscala.phaser.Particles.Arcade.Emitter
import phaser.card.CardSprite

class PlaymatEmitter(playmat: Playmat) {
  val suitEmitter = Seq(makeEmitter(0), makeEmitter(1), makeEmitter(2), makeEmitter(3))

  def makeEmitter(frame: Int) = {
    var ret = new Emitter(playmat.game, 0, 0, 50)
    ret.makeParticles("card-suits", frame)
    ret.gravity = 0
    ret.minParticleSpeed.setTo(-400, -400)
    ret.maxParticleSpeed.setTo(400, 400)
    ret.setAlpha(1, 0, 1000)
    ret.setScale(0.8, 1, 0.8, 1, 1000)
    this.playmat.add(ret)
    ret
  }

  def emitFor(card: CardSprite) = {
    var e = this.suitEmitter(card.suit.index)
    e.emitX = card.x
    e.emitY = card.y
    e.start(explode = true, 1000, 0, 40)
  }

  def bringToTop() = suitEmitter.foreach(playmat.bringToTop)
}
