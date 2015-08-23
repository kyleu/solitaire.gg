package services.game

import org.joda.time.{ LocalDateTime, Seconds }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService
import services.leaderboard.GameSeedService
import services.user.UserStatisticsService
import utils.DateUtils

trait GameServiceCompletionHelper { this: GameService =>
  protected[this] var completed: Option[LocalDateTime] = None

  protected[this] def completeGame(win: Boolean) = {
    if(completed.isDefined) {
      throw new IllegalStateException(s"Attempt to complete already-completed game [$id].")
    }

    status = if(win) { "win" } else { "abandoned" }

    val completionTime = lastMoveMade.getOrElse(DateUtils.now)
    completed = Some(completionTime)
    val elapsed = firstMoveMade.flatMap { first =>
      lastMoveMade.map { last =>
        Seconds.secondsBetween(first, last).getSeconds
      }
    }

    GameHistoryService.setCompleted(id, completionTime, status).flatMap { completed =>
      update().flatMap { updated =>
        UserStatisticsService.registerGame(gameHistory.get).flatMap { stats =>
          if (win) {
            GameSeedService.registerWin(rules, seed, player.userId, moveCount, elapsed.getOrElse(0), completionTime)
          } else {
            GameSeedService.registerLoss(rules, seed).map(_ => (false, false))
          }
        }
      }
    }
  }
}
