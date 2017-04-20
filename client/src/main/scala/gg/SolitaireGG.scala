package gg

import models.RequestMessage
import utils.{Logging, NetworkMessage, NullUtils}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  @JSExport
  def init(): Unit = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    new PhaserGame()
  }
}
