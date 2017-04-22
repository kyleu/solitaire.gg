package phaser.playmat

import com.definitelyscala.phaser.{Game, Group}
import utils.NullUtils

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Playmat(game: Game, val pileSets: Seq[String], val layoutString: String) extends Group(
  game = game, parent = NullUtils.inst, name = "playmat"
)
