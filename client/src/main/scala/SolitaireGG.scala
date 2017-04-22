import navigation.NavigationService
import network.{MessageHandler, NetworkService}
import phaser.PhaserGame
import utils.Logging

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  private[this] var active: Option[SolitaireGG] = None

  @JSExport
  def go(debug: Boolean): Unit = active match {
    case None => active = Some(new SolitaireGG(debug))
    case _ => throw new IllegalStateException("Already initialized.")
  }
}

class SolitaireGG(val debug: Boolean) {
  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService()
  val messageHandler = new MessageHandler()
  val game = new PhaserGame()

  init()

  private[this] def onStateChange(o: NavigationService.State, n: NavigationService.State) = {
    utils.Logging.info("OK")
  }

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    navigation.navigate(NavigationService.State.Menu)

    testbed()
  }

  private[this] def testbed() = {
  }
}
