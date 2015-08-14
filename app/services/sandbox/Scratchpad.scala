package services.sandbox

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.history.GameHistoryService
import services.user.UserStatisticsService

import scala.concurrent.Future

object Scratchpad {
  def run() = {
    GameHistoryService.getAll.map { games =>
      val gamesFuture = games.filter(_.isCompleted).foldLeft(Future.successful(Unit)) { (f, g) =>
        f.flatMap { unused =>
          UserStatisticsService.registerGame(g)
        }
      }

      gamesFuture.map { unused =>
        s"Ok: [${games.size}] games loaded."
      }
    }
  }
}
