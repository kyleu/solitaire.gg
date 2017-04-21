package gg

import gg.network.{MessageHandler, NetworkService}
import utils.Logging

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  @JSExport
  def go(debug: Boolean): Unit = {
    new SolitaireGG(debug)
  }
}

class SolitaireGG(val debug: Boolean) {
  val network = new NetworkService()
  val messageHandler = new MessageHandler()
  val game = new PhaserGame()

  init()

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    testbed()
  }

  private[this] def testbed() = {
  }
}
