import models.user.UserStatistics
import org.scalajs.dom
import upickle.legacy._

object StatisticsHelper {
  lazy val defaultStats = UserStatistics(java.util.UUID.randomUUID(), 0L, UserStatistics.Games.empty)
}

trait StatisticsHelper {
  import StatisticsHelper._

  private var statistics = readStatistics()

  def registerGame(win: Boolean, rules: String, seed: Int, moves: Int, undos: Int, redos: Int, elapsedMs: Long, completionTime: Long) = {
    val currentWinStreak = if (win) { statistics.games.currentWinStreak + 1 } else { 0 }
    val currentLossStreak = if (win) { 0 } else { statistics.games.currentLossStreak + 1 }

    statistics = statistics.copy(
      games = statistics.games.copy(
        wins = statistics.games.wins + (if (win) { 1 } else { 0 }),
        losses = statistics.games.losses + (if (win) { 0 } else { 1 }),

        totalDurationMs = statistics.games.totalDurationMs + elapsedMs,
        totalMoves = statistics.games.totalMoves + moves,
        totalUndos = statistics.games.totalUndos + undos,
        totalRedos = statistics.games.totalRedos + redos,

        lastWin = if (win) { Some(completionTime) } else { statistics.games.lastWin },
        lastLoss = if (win) { statistics.games.lastLoss } else { Some(completionTime) },

        currentWinStreak = currentWinStreak,
        maxWinStreak = if (currentWinStreak > statistics.games.maxWinStreak) {
        currentWinStreak
      } else {
        statistics.games.maxWinStreak
      },
        currentLossStreak = currentLossStreak,
        maxLossStreak = if (currentLossStreak > statistics.games.maxLossStreak) {
        currentLossStreak
      } else {
        statistics.games.maxLossStreak
      }
      )
    )
    saveStatistics(statistics)
    statistics
  }

  def readStatistics() = {
    Option(dom.window.localStorage.getItem("user.statistics")) match {
      case Some(statsText) => try {
        read[UserStatistics](statsText)
      } catch {
        case x: Exception =>
          saveStatistics(defaultStats)
          defaultStats
      }
      case None =>
        saveStatistics(defaultStats)
        defaultStats
    }
  }

  def saveStatistics(s: UserStatistics) = {
    dom.window.localStorage.setItem("user.statistics", write(s))
  }
}
