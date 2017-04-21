package gg

import gg.phaser.PhaserGame

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  @JSExport
  def init(): Unit = {
    println("Solitaire.gg, v2.0.0")
    new PhaserGame()
  }
}
