package models.database.queries.leaderboard

import java.util.UUID

import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Query, Row, Statement }
import models.leaderboard.GameSeed
import org.joda.time.LocalDateTime

object GameSeedQueries extends BaseQueries[GameSeed] {
  override protected val tableName = "game_seeds"
  override protected val columns = Seq("rules", "seed", "games", "wins", "player", "moves", "elapsed_ms", "completed")
  override protected val idColumns = Seq("rules", "seed")
  override protected val searchColumns = Seq("rules", "seed::text", "rules", "player::text")

  val insert = Insert
  val getById = GetById
  val search = Search

  case class GetCounts(whereClause: Option[String]) extends Query[Map[String, GameSeed.SeedCount]] {
    override val sql = s"""
      select
        rules,
        count(*) as seeds,
        sum(games) as games,
        sum(wins) as wins,
        max(moves) as maxmoves,
        avg(moves)::int as avgmoves,
        min(moves) as minmoves
      from $tableName
      ${whereClause.map("where " + _).getOrElse("")}
      group by rules
    """
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      val count = GameSeed.SeedCount(
        seeds = row.as[Long]("seeds").toInt,
        games = row.as[Long]("games").toInt,
        wins = row.as[Long]("wins").toInt,
        minMoves = row.asOpt[Int]("minmoves"),
        avgMoves = row.asOpt[Int]("avgmoves"),
        maxMoves = row.asOpt[Int]("maxmoves")
      )
      (row.as[String]("rules"), count)
    }.toMap
  }

  case class WinCount(rules: String) extends FlatSingleRowQuery[Int] {
    override def sql = "select sum(wins) as c from game_seeds where rules = ?"
    override def values = Seq(rules)
    override def flatMap(row: Row) = row.asOpt[Long]("c").map(_.toInt)
  }

  case class RandomWinnableSeed(rules: String) extends FlatSingleRowQuery[Int] {
    override def sql = s"select seed from $tableName where rules = ? order by random() limit 1"
    override def values = Seq(rules)
    override def flatMap(row: Row) = Some(row.as[Int]("seed"))
  }

  case class UpdateGameSeedWin(rules: String, seed: Int, player: UUID, moves: Int, elapsedMs: Int, completed: LocalDateTime) extends Statement {
    override val sql = s"""
      update $tableName
      set games = games + 1, wins = wins + 1, player = ?, moves = ?, elapsed_ms = ?, completed = ?
      where rules = ? and seed = ?
    """
    override val values = Seq[Any](player, moves, elapsedMs, completed, rules, seed)
  }

  case class UpdateGameSeedLoss(rules: String, seed: Int) extends Statement {
    override val sql = s"update $tableName set games = games + 1 where rules = ? and seed = ?"
    override val values = Seq[Any](rules, seed)
  }

  override protected def fromRow(row: Row) = {
    val rules = row.as[String]("rules")
    val seed = row.as[Int]("seed")
    val games = row.as[Int]("games")
    val wins = row.as[Int]("wins")
    val player = row.asOpt[UUID]("player")
    val moves = row.asOpt[Int]("moves")
    val elapsed = row.asOpt[Int]("elapsed_ms")
    val completed = row.asOpt[LocalDateTime]("completed")
    GameSeed(rules, seed, games, wins, player, moves, elapsed, completed)
  }

  override protected def toDataSeq(gs: GameSeed) = Seq[Any](
    gs.rules, gs.seed, gs.games, gs.wins, gs.player, gs.moves, gs.elapsed, gs.completed
  )
}
