import models.game.GameState
import navigation.NavigationService
import network.{MessageHandler, NetworkService}
import org.scalajs.dom
import org.scalajs.dom.BeforeUnloadEvent
import phaser.PhaserGame
import utils.{Logging, NullUtils}

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
  val phaser = new PhaserGame()

  var game: Option[GameState] = None

  init()

  private[this] def onStateChange(o: NavigationService.State, n: NavigationService.State) = n match {
    case _ => utils.Logging.warn(s"Unhandled state change to [$n].")
  }

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    dom.window.onbeforeunload = (e: BeforeUnloadEvent) => {
      game match {
        case Some(_) => "You're playing a game. Are you sure you'd like to resign?"
        case _ => NullUtils.inst
      }
    }

    testbed()
  }

  private[this] def testbed() = {
    navigation.navigate(NavigationService.State.Game)
    phaser.start()
  }
}
