package models.queries.history

import java.util.UUID

import models.database.{Row, Statement}
import models.history.{GameHistory, GameSeed}
import models.queries.BaseQueries
import org.joda.time.LocalDateTime
import services.test.GameSolver

object GameSeedQueries extends BaseQueries[GameSeed] {
  override protected val tableName = "game_seeds"
  override protected val columns = Seq("rules", "seed", "games", "wins", "player", "moves", "elapsed_ms", "completed")
  override protected val searchColumns = Seq("rules", "seed::text", "player::text")

  def getByKey(rules: String, seed: Int) = GetById(Seq(rules, seed))

  val insert = Insert

  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search

  def removeByKey(rules: String, seed: Int) = RemoveById(Seq(rules, seed))
  case class RemoveByRules(rules: String) extends Statement {
    override val sql = s"delete from $tableName where rules = ?"
    override val values = Seq(rules)
  }

  val truncate = Truncate

  def getCountForUser(userId: UUID) = Count(s"select count(*) as c from $tableName where player = ?", Seq(userId))

  case class OnComplete(gh: GameHistory) extends Statement {
    private[this] val pClause = "case when player is null then ? else player end"
    private[this] val player = if (gh.player == GameSolver.userId) { None } else { Some(gh.player) }
    private[this] val eClause = "case when elapsed_ms is null then ? else elapsed_ms end"
    private[this] val cClause = "case when completed is null then ? else completed end"

    override val sql = s"""update $tableName
      set games = games + 1, wins = wins + ?, player = $pClause, moves = moves + ?, elapsed_ms = $eClause, completed = $cClause
      where rules = ? and seed = ?"""
    override val values = Seq[Any](if (gh.isWon) { 1 } else { 0 }, player, gh.moves, gh.duration, gh.completed, gh.rules, gh.seed)
  }

  override protected def fromRow(row: Row) = {
    val rules = row.as[String]("rules")
    val seed = row.as[Int]("seed")
    val games = row.as[Int]("games")
    val wins = row.as[Int]("wins")
    val player = row.asOpt[UUID]("player")
    val moves = row.as[Int]("moves")
    val elapsedMs = row.as[Int]("elapsed_ms")
    val completed = row.asOpt[LocalDateTime]("completed")
    GameSeed(rules, seed, games, wins, player, moves, elapsedMs, completed)
  }

  override protected def toDataSeq(gs: GameSeed) = Seq[Any](gs.rules, gs.seed, gs.games, gs.wins, gs.player, gs.moves, gs.elapsedMs, gs.completed)
}
