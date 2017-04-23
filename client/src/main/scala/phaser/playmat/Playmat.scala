package phaser.playmat

import com.definitelyscala.phaser.Group
import models.pile.set.PileSet
import phaser.PhaserGame
import phaser.playmat.layout.PlaymatResizer
import utils.NullUtils

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Playmat(val phaser: PhaserGame, val pileSets: Seq[PileSet], val layoutString: String) extends Group(
  game = phaser, parent = NullUtils.inst, name = "playmat"
) {
  var layout: (Double, Double, Map[String, (Double, Double)]) = (0.0, 0.0, Map.empty)

  val resizer = new PlaymatResizer(this)
  val emitter = new PlaymatEmitter(this)

  var w: Double = 0.0
  var h: Double = 0.0

  phaser.add.existing(this)
}
