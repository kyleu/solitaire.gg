package models.history

object GameStatistics {
  def empty(rules: String) = GameStatistics(rules, 0, 0, 0, None, None, 0L, None, None, 0, 0, 0, None, None)
}

case class GameStatistics(
  rules: String,
  played: Int,
  wins: Int,
  losses: Int,
  minDurationMs: Option[Long],
  maxDurationMs: Option[Long],
  totalDurationMs: Long,
  minMoves: Option[Int],
  maxMoves: Option[Int],
  totalMoves: Int,
  totalUndos: Int,
  totalRedos: Int,
  lastWin: Option[Long],
  lastLoss: Option[Long]
)
