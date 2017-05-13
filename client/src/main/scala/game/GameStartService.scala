package game

import java.util.UUID

import client.SolitaireGG
import models.rules.moves.InitialMoves

import scala.util.Random

object GameStartService {
  def onGameStateChange(gg: SolitaireGG, args: Seq[String]) = args.toList match {
    case Nil if gg.hasGame => gg.phaser.getPlaymat.resizer.resizeIfChanged(false)
    case Nil => startGame(gg, UUID.randomUUID, "klondike", Math.abs(Random.nextInt))
    case r :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(Random.nextInt))
    case r :: s :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(s.toInt))
    case _ => throw new IllegalStateException(s"Unhandled initial arguments [${args.mkString(", ")}].")
  }

  def endGame(gg: SolitaireGG, id: UUID, win: Boolean) = gg.phaser.gameplay.stop(id, win, () => gg.clearGame())

  def startGame(gg: SolitaireGG, id: UUID, rulesId: String, seed: Int) = {
    if (gg.hasGame) { throw new IllegalStateException(s"Called [startGame] before destroying active [${gg.getGame.rulesId}] game [${gg.getGame.id}].") }
    val ag = ActiveGame(id = id, rulesId = rulesId, seed = seed)
    ag.state.addPlayer(gg.userId, "Offline Player", autoFlipOption = gg.settings.getSettings.autoFlip)
    InitialMoves.performInitialMoves(ag.rules, ag.state)
    gg.setGame(ag)
    gg.phaser.gameplay.start(ag.id, ag.state)
  }
}
