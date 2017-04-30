package phaser.gameplay

import java.util.UUID

import com.definitelyscala.phaser.{PhysicsObj, State}
import models.game.{GameState, MoveHelper, RequestMessageHandler}
import models.rules.GameRulesSet
import phaser.PhaserGame
import phaser.card.CardImages
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Gameplay(val g: PhaserGame, var settings: PlayerSettings, onLoadComplete: () => Unit) extends State {
  private[this] var gameState: Option[GameState] = None
  def getState = gameState.getOrElse(throw new IllegalStateException("No game state available."))

  private[this] var moveHelper: Option[MoveHelper] = None
  def getMoveHelper = moveHelper.getOrElse(throw new IllegalStateException("No move helper available."))

  private[this] var responseMessageHandler: Option[ResponseMessageHandler] = None
  def getResponseMessageHandler = responseMessageHandler.getOrElse(throw new IllegalStateException("No response message handler available."))

  private[this] var requestMessageHandler: Option[RequestMessageHandler] = None
  def getMessageHandler = requestMessageHandler.getOrElse(throw new IllegalStateException("No request message handler available."))

  override def preload() = {
    game.physics.startSystem(PhysicsObj.ARCADE)
    g.setImages(new CardImages(g, settings))
  }

  override def create() = {
    onLoadComplete()
  }

  private[this] def postMove() = {
    utils.Logging.info("Post move!")
    //if (!checkWinCondition()) {
    //  send(PossibleMoves(possibleMoves(), undoHelper.historyQueue.size, undoHelper.undoneQueue.size))
    //}
  }

  def start(id: UUID, state: GameState) = {
    gameState = Some(state)
    moveHelper = Some(new MoveHelper(state, postMove))
    responseMessageHandler = Some(new ResponseMessageHandler(g))
    requestMessageHandler = Some(new RequestMessageHandler(state, getResponseMessageHandler.handle, getMoveHelper.registerMove))

    val rules = GameRulesSet.allByIdWithAliases(state.rules)
    val playmat = new Playmat(g, state.pileSets, rules.layout)
    g.setPlaymat(playmat)

    AssetLoader.loadPileSets(g, state.pileSets)

    val cards = AssetLoader.loadCards(g, state.pileSets, state.deck.originalOrder)
    playmat.setCards(cards)

    g.possibleMoves = getMoveHelper.possibleMoves()

    utils.Logging.info(s"Started game [$id] with rules [${rules.id}].")
  }
}
