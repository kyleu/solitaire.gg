package services.sandbox

import models.queries.history.GameStatisticsQueries
import services.database.Database
import util.Application
import util.FutureUtils.defaultContext

trait GameStatsResetLogic {
  def call(ctx: Application) = {
    Database.execute(GameStatisticsQueries.truncate).flatMap { _ =>
      Database.execute(GameStatisticsQueries.Refresh).map { _ =>
        "Game stats reset!"
      }
    }
  }
}

