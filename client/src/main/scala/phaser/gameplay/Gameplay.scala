package phaser.gameplay

import java.util.UUID

import com.definitelyscala.phaser.{PhysicsObj, State}
import models.game.GameState
import models.rules.{GameRules, GameRulesSet}
import models.{GameJoined, PossibleMove, PossibleMoves, ResponseMessage}
import phaser.PhaserGame
import phaser.card.CardImages
import phaser.playmat.Playmat
import settings.PlayerSettings

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class Gameplay(g: PhaserGame, settings: PlayerSettings, onLoadComplete: () => Unit) extends State {
  override def preload() = {
    game.physics.startSystem(PhysicsObj.ARCADE)
    g.setImages(new CardImages(g, settings))
  }

  override def create() = {
    onLoadComplete()
  }

  def handleResponseMessage(msg: ResponseMessage) = msg match {
    case gj: GameJoined => start(gj.id, gj.state, GameRulesSet.allByIdWithAliases(gj.state.rules), gj.moves)
    case pm: PossibleMoves => g.possibleMoves = pm.moves
    case _ => throw new IllegalStateException(s"Unhandled response message [$msg].")
  }

  def start(id: UUID, state: GameState, rules: GameRules, moves: Seq[PossibleMove]) = {
    val playmat = new Playmat(g, state.pileSets, rules.layout)
    g.setPlaymat(playmat)

    AssetLoader.loadPileSets(g, state.pileSets)

    val cards = AssetLoader.loadCards(g, state.pileSets, state.deck.originalOrder)
    playmat.setCards(cards)

    utils.Logging.info(s"Started game [$id] with rules [${rules.id}].")
  }
}
