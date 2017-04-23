package phaser.pile

import com.definitelyscala.phaser.Group
import models.pile.options.PileOptions
import models.pile.set.PileSet
import phaser.PhaserGame

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class PileGroup(phaser: PhaserGame, id: String, pileSet: PileSet, pileSetIndex: Int, options: PileOptions) extends Group {

}
