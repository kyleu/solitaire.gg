package services.socket

import models.InternalMessage
import models.history.GameHistory
import models.rules.GameRulesSet
import models.rules.impl.Sandbox
import msg.req._
import msg.rsp.Pong
import services.history.GameHistoryService
import services.user.{UserService, UserStatisticsService}
import utils.DateUtils
import utils.metrics.InstrumentedActor

trait SocketRequestMessageHelper extends InstrumentedActor { this: SocketService =>
  override def receiveRequest = {
    case p: Ping => out ! Pong(p.ts)
    case gs: OnGameStart => onGameStart(gs)
    case gc: OnGameComplete => onGameComplete(gc)
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

  private[this] def onGameComplete(gc: OnGameComplete) = {
    val gh = GameHistory(
      id = gc.id,
      rules = "",
      seed = 0,
      status = GameHistory.Status.withValue(gc.status),
      player = user.id,
      cards = 0,
      moves = gc.moves,
      undos = gc.undos,
      redos = gc.redos,
      score = gc.score,
      firstMove = Some(DateUtils.fromMillis(gc.firstMove)),
      completed = Some(DateUtils.fromMillis(gc.occurred))
    )

    GameHistoryService.onComplete(gh)
    UserStatisticsService.registerGame(gh)
  }
}
