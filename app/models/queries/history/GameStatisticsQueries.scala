package models.queries.history

import models.database.{Row, Statement}
import models.history.{GameHistory, GameStatistics}
import models.queries.BaseQueries
import java.time.LocalDateTime
import util.DateUtils

object GameStatisticsQueries extends BaseQueries[GameStatistics] {
  override protected val tableName = "game_statistics"
  override protected val columns = Seq(
    "rules", "played", "wins", "losses", "min_duration_ms", "max_duration_ms", "total_duration_ms",
    "min_moves", "max_moves", "total_moves", "total_undos", "total_redos", "last_win", "last_loss"
  )
  override protected val searchColumns = Seq("rules")
  override protected def idColumns = Seq("rules")

  val insert = Insert
  def getById(rules: String) = getBySingleId(rules)
  def removeById(rules: String) = RemoveById(Seq(rules))
  val truncate = Truncate

  override protected def fromRow(row: Row) = GameStatistics(
    rules = row.as[String]("rules"),
    played = row.as[Int]("played"),
    wins = row.as[Int]("wins"),
    losses = row.as[Int]("losses"),
    minDurationMs = row.asOpt[Long]("min_duration_ms"),
    maxDurationMs = row.asOpt[Long]("max_duration_ms"),
    totalDurationMs = row.as[Long]("total_duration_ms"),
    minMoves = row.asOpt[Int]("min_moves"),
    maxMoves = row.asOpt[Int]("max_moves"),
    totalMoves = row.as[Int]("total_moves"),
    totalUndos = row.as[Int]("total_undos"),
    totalRedos = row.as[Int]("total_redos"),
    lastWin = row.asOpt[LocalDateTime]("last_win").map(DateUtils.toMillis),
    lastLoss = row.asOpt[LocalDateTime]("last_loss").map(DateUtils.toMillis)
  )

  override protected def toDataSeq(s: GameStatistics) = Seq(
    s.rules, s.played, s.wins, s.losses, s.minDurationMs, s.maxDurationMs, s.totalDurationMs,
    s.minMoves, s.maxMoves, s.totalMoves, s.totalUndos, s.totalRedos,
    s.lastWin.map(DateUtils.fromMillis), s.lastLoss.map(DateUtils.fromMillis)
  )

  case class OnFinish(gh: GameHistory) extends Statement {
    override def sql = updateSql(gh.isWon)
    override def values = Seq[Any](gh.duration, gh.duration, gh.duration, gh.moves, gh.moves, gh.moves, gh.undos, gh.redos, gh.completed, gh.rules)
  }

  case object Refresh extends Statement {
    override def sql = """insert into game_statistics (select
      rules,
      count(*) played,
      sum(case when status = 'won' or status = 'w' then 1 else 0 end) won,
      sum(case when status = 'lost' or status = 'l' then 1 else 0 end) lost,
      min(case when duration_ms = 0 then null else duration_ms end) min_duration,
      max(case when duration_ms = 0 then null else duration_ms end) max_duration,
      sum(duration_ms) duration,
      min(case when moves = 0 then null else moves end) min_moves,
      max(case when moves = 0 then null else moves end) max_moves,
      sum(moves) moves,
      sum(undos) undos,
      sum(redos) redos,
      min(case when status = 'won' or status = 'w' then completed else null end) last_win,
      min(case when status = 'loss' or status = 'l' then completed else null end) last_loss
    from games
    group by rules
    order by rules desc)"""
  }

  private[this] def updateSql(win: Boolean) = {
    val single = if (win) { "win" } else { "loss" }
    val multi = if (win) { "wins" } else { "losses" }
    s"""
      update $tableName set
        max_duration_ms = greatest(max_duration_ms, ?),
        min_duration_ms = least(min_duration_ms, ?),
        total_duration_ms = total_duration_ms + ?,
        min_moves = least(min_moves, ?),
        max_moves = greatest(max_moves, ?),
        total_moves = total_moves + ?,
        total_undos = total_undos + ?,
        total_redos = total_redos + ?,
        $multi = $multi + 1,
        last_$single = ?
      where rules = ?
    """
  }

  case class Increment(rules: String, col: String, v: Int) extends Statement {
    override def sql = s"update $tableName set $col = $col + ? where rules = ?"
    override val values = Seq[Any](v, rules)
  }
}
