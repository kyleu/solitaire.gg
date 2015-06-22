package models.database.queries.leaderboard

import java.util.UUID

import models.database.queries.BaseQueries
import models.database.{ Query, Row, Statement }
import models.leaderboard.GameSeed
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameSeedQueries extends BaseQueries[GameSeed] {
  override protected val tableName = "game_seeds"
  override protected val columns = Seq("rules", "seed", "player", "moves", "elapsed_ms", "completed")
  override protected val idColumns = Seq("rules", "seed")
  override protected val searchColumns = Seq("rules", "seed::text", "rules", "player::text")

  val insert = Insert
  val getById = GetById
  val search = Search

  case class GetCounts(whereClause: Option[String]) extends Query[Map[String, (Int, Int, Int, Int)]] {
    override val sql = """
      select
        rules,
        count(*) as seeds,
        max(moves) as maxmoves,
        avg(moves)::int as avgmoves,
        min(moves) as minmoves
      from game_seeds group by rules
    """
    override def reduce(rows: Iterator[Row]) = rows.map { row =>
      val stats = (
        row.as[Long]("seeds").toInt,
        row.as[Int]("maxmoves"),
        row.as[Int]("avgmoves"),
        row.as[Int]("minmoves")
      )
      (row.as[String]("rules"), stats)
    }.toMap
  }

  case class UpdateGameSeed(gs: GameSeed) extends Statement {
    override val sql = updateSql(Seq("player", "moves", "elapsed_ms", "completed"))
    override val values = Seq[Any](gs.player, gs.moves, gs.elapsed, DateUtils.toSqlTimestamp(gs.completed), gs.rules, gs.seed)
  }

  override protected def fromRow(row: Row) = {
    val rules = row.as[String]("rules")
    val seed = row.as[Int]("seed")
    val player = UUID.fromString(row.as[String]("player"))
    val moves = row.as[Int]("moves")
    val elapsed = row.as[Int]("elapsed_ms")
    val completed = row.as[LocalDateTime]("completed")
    GameSeed(rules, seed, player, moves, elapsed, completed)
  }

  override protected def toDataSeq(gs: GameSeed) = Seq[Any](
    gs.rules, gs.seed, gs.player, gs.moves, gs.elapsed, gs.completed
  )
}
