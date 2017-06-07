package client

import game.{ActiveGame, GameListService, GameStartService}
import help.HelpService
import menu.MenuService
import msg.req.SaveSettings
import navigation.{NavigationService, NavigationState}
import network.NetworkService
import phaser.PhaserGame
import phaser.gameplay.InputHelper
import settings.{ProfileService, SettingsPanel, SettingsService}
import msg.rsp.{Profile, SocketResponseMessage}

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

class SolitaireGG(val debug: Boolean) extends InitHelper {
  protected[this] var game: Option[ActiveGame] = None
  def hasGame = game.isDefined
  def getGame = game.getOrElse(throw new IllegalStateException("No active game."))
  def clearGame() = game = None
  def setGame(g: ActiveGame) = game = Some(g)

  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService(debug, handleSocketResponseMessage)
  val settings = new SettingsService(onSave = s => network.sendMessage(SaveSettings(s)))
  val profile = new ProfileService(settings)
  val menu = new MenuService(settings, navigation)

  val phaser = new PhaserGame(this)

  init()

  private[this] def onStateChange(o: NavigationState, n: NavigationState, args: Seq[String]): Unit = {
    o match {
      case NavigationState.Loading => navigation.setMenuPosition(settings.getSettings.menuPosition)
      case NavigationState.Settings => settings.applyAndSave(SettingsPanel.getCurrentSettings)
      case _ => // noop
    }
    n match {
      case NavigationState.Games => GameListService.initIfNeeded(rules => {
        phaser.gameplay.activeGame.foreach { gameId =>
          GameStartService.endGame(this, gameId, win = false)
        }
        navigation.navigate(NavigationState.Play, rules)
      })
      case NavigationState.Settings => SettingsPanel.initIfNeeded(settings.getSettings)
      case NavigationState.Play => GameStartService.onGameStateChange(this, args)
      case NavigationState.Help => HelpService.show(args.headOption)
      case _ => // noop
    }
  }

  private[this] def handleSocketResponseMessage(msg: SocketResponseMessage) = msg match {
    case p: Profile => utils.Logging.info(s"Profile: $p")
    case _ => utils.Logging.info(s"Unhandled SocketResponseMessage: [$msg].")
  }

  def onSandbox() = {
    val seq = Seq("draw", "shuffle", "playcard")
    val key = seq(Random.nextInt(seq.length))
    phaser.playAudio(key)
  }

  def onPhaserLoadComplete(): Unit = {
    new InputHelper(this)
    phaser.initAudio()
    navigation.initialAction()
  }
}
