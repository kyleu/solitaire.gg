package models.queries.history

import java.util.UUID

import models.database.{Query, Row, Statement}
import models.history.{GameHistory, GameSeed}
import models.queries.BaseQueries
import models.rules.{GameRules, GameRulesSet}
import org.joda.time.LocalDateTime
import services.test.GameSolver
import utils.DateUtils

object GameSeedQueries extends BaseQueries[GameSeed] {
  override protected val tableName = "game_seeds"
  override protected val columns = Seq(
    "rules", "seed", "games", "wins", "moves",
    "first_player", "first_moves", "first_elapsed_ms", "first_occurred",
    "fastest_player", "fastest_moves", "fastest_elapsed_ms", "fastest_occurred"
  )
  override protected val searchColumns = Seq("rules", "seed::text", "first_player::text", "fastest_player::text")

  def getByKey(rules: String, seed: Int) = GetById(Seq(rules, seed))
  def getAll(limit: Option[Int], offset: Option[Int]) = GetAll(orderBy = Some("first_occurred desc"), limit, offset)

  val insert = Insert

  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search

  def removeByKey(rules: String, seed: Int) = RemoveById(Seq(rules, seed))
  case class RemoveByRules(rules: String) extends Statement {
    override val sql = s"delete from $tableName where rules = ?"
    override val values = Seq(rules)
  }

  case object SummaryReport extends Query[Seq[(GameRules, Int)]] {
    override val sql = s"select rules, count(wins) c from $tableName group by rules"
    override def reduce(rows: Iterator[Row]) = {
      val r = rows.map(row => row.as[String]("rules") -> row.as[Long]("c").toInt).toMap
      GameRulesSet.all.map(rules => rules -> r.getOrElse(rules.id, 0))
    }
  }

  val truncate = Truncate

  def getCountForUser(userId: UUID) = Count(s"select count(*) as c from $tableName where player = ?", Seq(userId))

  case class OnComplete(gh: GameHistory) extends Statement {
    private[this] val player = if (gh.player == GameSolver.userId) { None } else { Some(gh.player) }

    private[this] val firstPlayerClause = "first_player = case when first_player is null then ? else first_player end"
    private[this] val firstMovesClause = "first_moves = case when first_moves is null then ? else first_moves end"
    private[this] val firstElapsedClause = "first_elapsed_ms = case when first_elapsed_ms is null then ? else first_elapsed_ms end"
    private[this] val firstOccurredClause = "first_occurred = case when first_occurred is null then ? else first_occurred end"
    private[this] val firstSql = Seq(firstPlayerClause, firstMovesClause, firstElapsedClause, firstOccurredClause).mkString(", ")

    private[this] val fastestClause = s"(fastest_elapsed_ms is null or fastest_elapsed_ms > ${gh.duration.getOrElse(Int.MaxValue)})"
    private[this] val fastestPlayerClause = s"fastest_player = case when $fastestClause then ? else fastest_player end"
    private[this] val fastestMovesClause = s"fastest_moves = case when $fastestClause then ? else fastest_moves end"
    private[this] val fastestElapsedClause = s"fastest_elapsed_ms = case when $fastestClause then ? else fastest_elapsed_ms end"
    private[this] val fastestOccurredClause = s"fastest_occurred = case when $fastestClause then ? else fastest_occurred end"
    private[this] val fastestSql = Seq(fastestPlayerClause, fastestMovesClause, fastestElapsedClause, fastestOccurredClause).mkString(", ")

    override val sql = s"update $tableName set games = games + 1, wins = wins + ?, moves = moves + ?, $firstSql, $fastestSql where rules = ? and seed = ?"

    override val values = Seq[Any](
      if (gh.isWon) { 1 } else { 0 }, gh.moves,
      player, gh.moves, gh.duration, gh.completed,
      player, gh.moves, gh.duration, gh.completed,
      gh.rules, gh.seed
    )
  }

  override protected def fromRow(row: Row) = {
    val rules = row.as[String]("rules")
    val seed = row.as[Int]("seed")
    val games = row.as[Int]("games")
    val wins = row.as[Int]("wins")
    val moves = row.as[Int]("moves")
    def recordForRow(prefix: String) = row.asOpt[UUID](prefix + "player").map { p =>
      GameSeed.Record(
        player = p,
        moves = row.as[Int](prefix + "moves"),
        elapsed = row.as[Int](prefix + "elapsed_ms"),
        occurred = row.asOpt[LocalDateTime](prefix + "occurred").getOrElse(DateUtils.now)
      )
    }
    GameSeed(rules, seed, games, wins, moves, recordForRow("first_"), recordForRow("fastest_"))
  }

  override protected def toDataSeq(gs: GameSeed) = Seq[Any](
    gs.rules, gs.seed, gs.games, gs.wins, gs.moves,
    gs.first.map(_.player), gs.first.map(_.moves), gs.first.map(_.elapsed), gs.first.map(_.occurred),
    gs.fastest.map(_.player), gs.fastest.map(_.moves), gs.fastest.map(_.elapsed), gs.fastest.map(_.occurred)
  )
}
