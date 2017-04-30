package game

import java.util.UUID

import client.user.DataHelper
import input.{GamepadHandler, KeyboardHandler}
import models.rules.moves.InitialMoves
import msg.SocketMessage
import navigation.{MenuService, NavigationService}
import network.{MessageHandler, NetworkService}
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

  val navigation = new NavigationService(onStateChange)
  val network = new NetworkService(debug, handleSocketMessage)
  val messageHandler = new MessageHandler()
  val settings = new SettingsService()

  val menu = new MenuService(settings, navigation)
  val phaser = new PhaserGame(this)

  init()

  private[this] def onStateChange(o: NavigationService.State, n: NavigationService.State) = n match {
    case NavigationService.State.List => GameListService.initIfNeeded()
    case _ => utils.Logging.warn(s"Unhandled state change to [$n].")
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

  private[this] def startGame(id: UUID = UUID.randomUUID, rulesId: String = "klondike", seed: Int = Random.nextInt) = {
    val ag = ActiveGame(id = id, rulesId = rulesId, seed = seed)
    ag.state.addPlayer(DataHelper.deviceId, "Offline Player", autoFlipOption = /* TODO */ true)
    InitialMoves.performInitialMoves(ag.rules, ag.state)
    game = Some(ag)
    phaser.gameplay.start(ag.id, ag.state)
  }

  def onPhaserLoadComplete(): Unit = {
    utils.Logging.info("Load complete")

    new KeyboardHandler(phaser, onInput)
    new GamepadHandler(phaser, onInput)

    navigation.navigate(NavigationService.State.Game)

    val act = navigation.initialAction()
    act match {
      case ("play", rules) => startGame(rulesId = rules)
      case (x, y) => throw new IllegalStateException(s"Unhandled initital action [$x:$y].")
    }
  }

  def onInput(i: String) = utils.Logging.info(s"Input message [$i].")
}
