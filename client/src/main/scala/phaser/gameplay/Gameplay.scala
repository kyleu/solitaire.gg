package phaser.gameplay

import java.util.UUID

import com.definitelyscala.phaser.{PhysicsObj, State}
import models.PossibleMoves
import models.game.{GameState, MoveHelper, RequestMessageHandler, UndoHelper}
import models.rules.{GameRules, GameRulesSet}
import phaser.PhaserGame
import phaser.card.CardImages
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

object Gameplay {
  case class GameServices(
    state: GameState, rules: GameRules, undo: UndoHelper, moves: MoveHelper, responses: ResponseMessageHandler, requests: RequestMessageHandler
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

  private[this] def postMove() = {
    utils.Logging.info("Post move!")
    if (!checkWinCondition()) {
      services.responses.handle(PossibleMoves(services.moves.possibleMoves(), services.undo.historyQueue.size, services.undo.undoneQueue.size))
    }
  }

  def start(id: UUID, state: GameState) = {
    val rules = GameRulesSet.allByIdWithAliases(state.rules)
    val moves = new MoveHelper(state, rules, postMove)
    val responses = new ResponseMessageHandler(g, debug)
    val requests = new RequestMessageHandler(state, responses.handle, moves.registerMove)
    activeServices = Some(Gameplay.GameServices(state, rules, new UndoHelper(), moves, responses, requests))

    val playmat = new Playmat(g, state.pileSets, rules.layout)
    g.setPlaymat(playmat)

    AssetLoader.loadPileSets(g, state.pileSets)

    val cards = AssetLoader.loadCards(g, state.pileSets, state.deck.originalOrder)
    playmat.setCards(cards)

    g.possibleMoves = services.moves.possibleMoves()

    utils.Logging.info(s"Started game [$id] with rules [${rules.id}].")
  }
}
