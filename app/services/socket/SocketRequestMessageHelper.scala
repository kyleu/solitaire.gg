package services.socket

import models.InternalMessage
import models.history.GameHistory
import models.rules.GameRulesSet
import models.rules.impl.Sandbox
import msg.req._
import msg.rsp.Pong
import services.audit.GameHistoryService
import services.user.{UserService, UserStatisticsService}
import utils.metrics.InstrumentedActor

trait SocketRequestMessageHelper extends InstrumentedActor { this: SocketService =>
  override def receiveRequest = {
    case p: Ping => out ! Pong(p.ts)
    case gs: OnGameStart => onGameStart(gs)
    case gw: OnGameWon => onGameWon(gw)
    case gl: OnGameLost => onGameLost(gl)
    case ss: SaveSettings => UserService.saveSettings(user.id, ss.settings)
    case im: InternalMessage => handleInternalMessage(im)
    case x => throw new IllegalArgumentException(s"Unhandled SocketRequestMessage [${x.getClass.getSimpleName}].")
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case x => throw new IllegalArgumentException(s"Unhandled InternalMessage [${x.getClass.getSimpleName}].")
  }

  private[this] def onGameStart(gs: OnGameStart) = {
    val rules = GameRulesSet.allByIdWithAliases.getOrElse(gs.rules, Sandbox)
    val gh = GameHistory(gs.id, gs.rules, gs.seed, GameHistory.Status.Started, user.id, rules.deckOptions.cardCount)
    GameHistoryService.insert(gh)
    UserStatisticsService.gameStarted(gs.id, gs.rules, gs.seed, user.id)
  }

  private[this] def onGameWon(gw: OnGameWon) = {
    log.info(" ::: " + gw)
  }

  private[this] def onGameLost(gl: OnGameLost) = {
    log.info(" ::: " + gl)
  }
}
