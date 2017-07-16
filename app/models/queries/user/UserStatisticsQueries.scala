package models.queries.user

import java.util.UUID

import models.database.{Row, Statement}
import models.history.GameHistory
import models.queries.BaseQueries
import models.user.UserStatistics
import org.joda.time.LocalDateTime
import util.DateUtils

object UserStatisticsQueries extends BaseQueries[UserStatistics] {
  override protected val tableName = "user_statistics"
  override protected val columns = Seq(
    "id", "joined",
    "played", "wins", "losses", "total_duration_ms", "total_moves", "total_undos", "total_redos",
    "last_win", "last_loss",
    "current_win_streak", "max_win_streak",
    "current_loss_streak", "max_loss_streak"
  )
  override protected val searchColumns = Seq("id::text", "username")

  val insert = Insert
  def getById(id: UUID) = getBySingleId(id)
  def removeById(userId: UUID) = RemoveById(Seq(userId))

  override protected def fromRow(row: Row) = UserStatistics(
    userId = row.as[UUID]("id"),
    joined = DateUtils.toMillis(row.as[LocalDateTime]("joined")),
    games = UserStatistics.Games(
      played = row.as[Int]("played"),
      wins = row.as[Int]("wins"),
      losses = row.as[Int]("losses"),
      totalDurationMs = row.as[Long]("total_duration_ms"),
      totalMoves = row.as[Int]("total_moves"),
      totalUndos = row.as[Int]("total_undos"),
      totalRedos = row.as[Int]("total_redos"),
      lastWin = row.asOpt[LocalDateTime]("last_win").map(DateUtils.toMillis),
      lastLoss = row.asOpt[LocalDateTime]("last_loss").map(DateUtils.toMillis),
      currentWinStreak = row.as[Int]("current_win_streak"),
      maxWinStreak = row.as[Int]("max_win_streak"),
      currentLossStreak = row.as[Int]("current_loss_streak"),
      maxLossStreak = row.as[Int]("max_loss_streak")
    )
  )

  override protected def toDataSeq(s: UserStatistics) = Seq(
    s.userId, DateUtils.fromMillis(s.joined),
    s.games.played, s.games.wins, s.games.losses, s.games.totalDurationMs, s.games.totalMoves, s.games.totalUndos, s.games.totalRedos,
    s.games.lastWin.map(DateUtils.fromMillis), s.games.lastLoss.map(DateUtils.fromMillis),
    s.games.currentWinStreak, s.games.maxWinStreak, s.games.currentLossStreak, s.games.maxLossStreak
  )

  case class Increment(userId: UUID, col: String, v: Int) extends Statement {
    override def sql = s"update $tableName set $col = $col + ? where id = ?"
    override val values = Seq[Any](v, userId)
  }

  case class OnFinish(gh: GameHistory) extends Statement {
    override def sql = updateSql(gh.isWon)
    override def values = Seq[Any](gh.duration, gh.moves, gh.undos, gh.redos, gh.completed, gh.player)
  }

  private[this] def updateSql(win: Boolean) = {
    val single = if (win) { "win" } else { "loss" }
    val multi = if (win) { "wins" } else { "losses" }
    val inverse = if (win) { "loss" } else { "win" }
    s"""
      update $tableName set
        total_duration_ms = total_duration_ms + ?,
        total_moves = total_moves + ?,
        total_undos = total_undos + ?,
        total_redos = total_redos + ?,
        $multi = $multi + 1,
        last_$single = ?,
        current_${single}_streak = current_${single}_streak + 1,
        max_${single}_streak = greatest(max_${single}_streak, current_${single}_streak + 1),
        current_${inverse}_streak = 0
      where id = ?
    """
  }
}
