package phaser.state

import com.definitelyscala.phaser.{PhysicsObj, State}
import phaser.PhaserGame
import phaser.card.CardImages
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Gameplay(g: PhaserGame, settings: PlayerSettings, onLoadComplete: () => Unit) extends State {
  override def preload() = {
    game.physics.startSystem(PhysicsObj.ARCADE)

    g.setImages(new CardImages(g, settings))
  }

  override def create() = {
    onLoadComplete()
  }
}
