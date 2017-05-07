package game

import java.util.UUID

import client.user.DataHelper
import help.HelpService
import models.game.GameStateDebug
import models.rules.moves.InitialMoves
import msg.SocketMessage
import navigation.{MenuService, NavigationService, NavigationState}
import network.NetworkService
import org.scalajs.dom
import org.scalajs.dom.raw.BeforeUnloadEvent
import phaser.PhaserGame
import settings.SettingsService
import utils.{Logging, NullUtils}

import scala.scalajs.js
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

class SolitaireGG(val debug: Boolean) {
  private[this] var game: Option[ActiveGame] = None
  def getGame = game.getOrElse(throw new IllegalStateException("No active game."))

  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService(debug, handleSocketMessage)
  val settings = new SettingsService()
  val menu = new MenuService(settings, navigation)

  val phaser = new PhaserGame(this)

  init()

  private[this] def onStateChange(o: NavigationState, n: NavigationState, args: Seq[String]): Unit = {
    o match {
      case NavigationState.Loading => navigation.setNavPosition(shown = true, top = true)
      case _ => // noop
    }
    n match {
      case NavigationState.List => GameListService.initIfNeeded()
      case NavigationState.Game => onGameStateChange(args)
      case NavigationState.Help => HelpService.show("klondike")
      case _ => // noop
    }
  }

  private[this] def init() = {
    utils.Logging.info("Solitaire.gg, v2.0.0")
    Logging.installErrorHandler()
    js.Dynamic.global.PhaserGlobal = js.Dynamic.literal("hideBanner" -> true)

    dom.window.onbeforeunload = (e: BeforeUnloadEvent) => {
      game match {
        case Some(_) => NullUtils.inst //"You're playing a game. Are you sure you'd like to resign?"
        case _ => NullUtils.inst
      }
    }

    phaser.start()
  }

  private[this] def handleSocketMessage(msg: SocketMessage) = {
    utils.Logging.info(s"SocketMessage: [$msg].")
  }

  private[this] def onGameStateChange(args: Seq[String]) = args.toList match {
    case Nil if game.isDefined => // noop
    case Nil => startGame(UUID.randomUUID, "klondike", Math.abs(Random.nextInt))
    case r :: Nil => startGame(UUID.randomUUID, r, Math.abs(Random.nextInt))
    case r :: s :: Nil => startGame(UUID.randomUUID, r, Math.abs(s.toInt))
    case _ => throw new IllegalStateException(s"Unhandled initial arguments [${args.mkString(", ")}].")
  }

  private[this] def startGame(id: UUID, rulesId: String, seed: Int) = {
    game.foreach(g => throw new IllegalStateException(s"Called [startGame] before destroying active [${g.rulesId}] game [${g.id}]."))
    val ag = ActiveGame(id = id, rulesId = rulesId, seed = seed)
    ag.state.addPlayer(DataHelper.deviceId, "Offline Player", autoFlipOption = /* TODO */ true)
    InitialMoves.performInitialMoves(ag.rules, ag.state)
    game = Some(ag)
    phaser.gameplay.start(ag.id, ag.state)
  }

  private[this] def endGame(id: UUID, win: Boolean) = {
    phaser.gameplay.stop(() => {
      game = None
      startGame(UUID.randomUUID, "klondike", 0)
    })
  }

  def onSandbox() = {
    utils.Logging.info(GameStateDebug.toString(phaser.gameplay.services.state))
    endGame(phaser.gameplay.services.state.gameId, win = true)
  }

  def onPhaserLoadComplete(): Unit = navigation.initialAction()
}
