package game

import java.util.UUID

import client.SolitaireGG
import menu.MenuService
import models.rules.GameRulesSet
import models.rules.moves.InitialMoves
import msg.req.OnGameStart

import scala.util.Random

object GameStartService {
  def onGameStateChange(gg: SolitaireGG, args: Seq[String], menu: MenuService) = {
    if (gg.hasGame) {
      val g = gg.getGame
      args.toList match {
        case Nil => gg.phaser.getPlaymat.resizer.resizeIfChanged(false)
        case r :: Nil if r == g.rulesId => gg.phaser.getPlaymat.resizer.resizeIfChanged(false)
        case r :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(Random.nextInt))
        case r :: s :: Nil if r == g.rulesId && s.toInt == g.seed => gg.phaser.getPlaymat.resizer.resizeIfChanged(false)
        case r :: s :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(s.toInt))
        case _ => throw new IllegalStateException(s"Unhandled initial arguments [${args.mkString(", ")}].")
      }
    } else {
      args.toList match {
        case Nil => startGame(gg, UUID.randomUUID, "klondike", Math.abs(Random.nextInt))
        case r :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(Random.nextInt))
        case r :: s :: Nil => startGame(gg, UUID.randomUUID, r, Math.abs(s.toInt))
        case _ => throw new IllegalStateException(s"Unhandled initial arguments [${args.mkString(", ")}].")
      }
    }
    menu.options.setOptionsForGame()
  }

  def endGame(gg: SolitaireGG, id: UUID, win: Boolean) = gg.phaser.gameplay.stop(id, win, () => gg.clearGame())

  def startGame(gg: SolitaireGG, id: UUID, rulesId: String, seed: Int) = {
    if (gg.hasGame) { throw new IllegalStateException(s"Called [startGame] before destroying active [${gg.getGame.rulesId}] game [${gg.getGame.id}].") }
    val rulesTranslated = GameRulesSet.allByIdWithAliases.get(rulesId).map(_.id).getOrElse("klondike")
    val ag = ActiveGame(id = id, rulesId = rulesTranslated, seed = seed)
    ag.state.addPlayer(gg.profile.getUserId, gg.profile.getUsername.getOrElse("Guest"), autoFlipOption = gg.settings.getSettings.autoFlip)
    InitialMoves.performInitialMoves(ag.rules, ag.state)
    gg.setGame(ag)
    gg.phaser.gameplay.start(ag.id, ag.state)
    // Disabled networking
    // gg.network.sendMessage(OnGameStart(ag.id, ag.rules.id, ag.seed, System.currentTimeMillis))
  }
}
