package client

import game.ActiveGame
import menu.MenuService
import msg.req.SaveSettings
import navigation.NavigationService
import network.NetworkService
import phaser.PhaserGame
import phaser.gameplay.InputHelper
import settings.{ProfileService, SettingsService}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.util.Random

@JSExportTopLevel("SolitaireGG")
object SolitaireGG {
  private[this] var active: Option[SolitaireGG] = None
  def getActive = active.getOrElse(throw new IllegalStateException("No active application."))

  @JSExport
  def go(debug: Boolean): Unit = active match {
    case None => active = Some(new SolitaireGG(debug))
    case _ => throw new IllegalStateException("Already initialized.")
  }
}

class SolitaireGG(val debug: Boolean) extends InitHelper with NavigationHelper with MessageHelper {
  protected[this] var game: Option[ActiveGame] = None
  def hasGame = game.isDefined
  def getGame = game.getOrElse(throw new IllegalStateException("No active game."))
  def clearGame() = game = None
  def setGame(g: ActiveGame) = game = Some(g)

  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService(debug, handleSocketResponseMessage)
  val settings = new SettingsService(onSave = s => network.sendMessage(SaveSettings(s)))
  val profile = new ProfileService(settings)
  val menu = new MenuService(navigation)

  val phaser = new PhaserGame(this)

  init()

  def onSandbox() = {
    val seq = IndexedSeq("draw", "shuffle", "playcard")
    val key = seq(Random.nextInt(seq.length))
    phaser.playAudio(key)
  }

  def onPhaserLoadComplete(): Unit = {
    new InputHelper(this)
    phaser.initAudio()
    navigation.initialAction()
  }
}
