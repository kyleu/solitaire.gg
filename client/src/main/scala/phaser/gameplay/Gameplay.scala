package phaser.gameplay

import java.util.UUID

import client.user.DataHelper
import com.definitelyscala.phaser.{PhysicsObj, State}
import models.PossibleMoves
import models.game._
import models.rules.{GameRules, GameRulesSet}
import phaser.PhaserGame
import phaser.card.CardImages
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

object Gameplay {
  case class GameServices(
    state: GameState,
    rules: GameRules,
    moves: MoveHelper,
    undo: UndoHelper,
    input: InputHelper,
    responses: ResponseMessageHandler,
    requests: RequestMessageHandler
  )
}

@ScalaJSDefined
class Gameplay(val g: PhaserGame, var settings: PlayerSettings, onLoadComplete: () => Unit, debug: Boolean) extends State {
  private[this] var activeServices: Option[Gameplay.GameServices] = None
  def services = activeServices.getOrElse(throw new IllegalStateException("No game services available."))

  override def preload() = {
    game.physics.startSystem(PhysicsObj.ARCADE)
    g.setImages(new CardImages(g, settings))
  }

  override def create() = {
    onLoadComplete()
  }

  def checkWinCondition() = services.rules.victoryCondition.check(services.rules, services.state)

  private[this] def postMove() = if (checkWinCondition()) {
    utils.Logging.info("Winner!")
  } else {
    services.responses.handle(PossibleMoves(services.moves.possibleMoves(), services.undo.historyQueue.size, services.undo.undoneQueue.size))
  }

  def start(id: UUID, coreState: GameState) = {
    activeServices.foreach(_ => throw new IllegalStateException(s"Game [${services.state.gameId}] already active. Stop it first."))
    val state = coreState.view(DataHelper.deviceId)
    val rules = GameRulesSet.allByIdWithAliases(state.rules)
    val moves = new MoveHelper(state, rules, postMove)
    val undo = new UndoHelper()
    val input = new InputHelper(g)
    val responses = new ResponseMessageHandler(g, undo, debug)
    val requests = new RequestMessageHandler(coreState, responses.handle, moves.registerMove)
    activeServices = Some(Gameplay.GameServices(state, rules, moves, undo, input, responses, requests))

    val playmat = new Playmat(g, state.pileSets, rules.layout)
    g.setPlaymat(Some(playmat))

    AssetLoader.loadPileSets(g, state.pileSets)

    val cards = AssetLoader.loadCards(g, state.pileSets, state.deck.originalOrder)
    playmat.setCards(cards)

    g.possibleMoves = services.moves.possibleMoves()

    utils.Logging.info(s"Started game [$id] with rules [${rules.id}].")
  }

  def stop() = {
    activeServices.getOrElse(throw new IllegalStateException(s"Called [stop] with no active game."))
    g.getPlaymat.destroy(destroyChildren = true)
    g.setPlaymat(None)
    activeServices = None
  }

  def onSandbox() = {
    utils.Logging.info(GameStateDebug.toString(services.state))
    stop()
  }
}
