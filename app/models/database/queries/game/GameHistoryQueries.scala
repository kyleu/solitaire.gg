package models.database.queries.game

import java.util.UUID

import models.audit.GameHistory
import models.database.queries.BaseQueries
import models.database.{ Row, Query, Statement }
import org.joda.time.LocalDateTime
import utils.DateUtils

object GameHistoryQueries extends BaseQueries[GameHistory] {
  override protected val tableName = "games"
  override protected val columns = Seq("id", "seed", "rules", "status", "player", "moves", "undos", "redos", "created", "completed")
  override protected val searchColumns = Seq("id::text", "seed::text", "rules", "status", "player::text")

  val insert = Insert
  val count = Count
  val search = Search
  val removeById = RemoveById

  case class UpdateGameHistory(id: UUID, status: String, moves: Int, undos: Int, redos: Int, completed: Option[LocalDateTime]) extends Statement {
    override val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed"))
    override val values = Seq[Any](status, moves, undos, redos, completed.map(DateUtils.toSqlTimestamp), id)
  }

  case class GetGameHistoriesByUser(userId: UUID, sortBy: String) extends Query[List[GameHistory]] {
    override val sql = getSql(Some("player = ?"), orderBy = Some("?"))
    override val values = Seq(userId, sortBy)
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  override protected def fromRow(row: Row) = {
    val id = UUID.fromString(row.as[String]("id"))
    val seed = row.as[Int]("seed")
    val rules = row.as[String]("rules")
    val status = row.as[String]("status")
    val player = UUID.fromString(row.as[String]("player"))
    val moves = row.as[Int]("moves")
    val undos = row.as[Int]("undos")
    val redos = row.as[Int]("redos")
    val created = row.as[LocalDateTime]("created")
    val complete = row.asOpt[LocalDateTime]("completed")
    GameHistory(id, seed, rules, status, player, moves, undos, redos, created, complete)
  }

  override protected def toDataSeq(gh: GameHistory) = Seq[Any](
    gh.id, gh.seed, gh.rules, gh.status, gh.player, gh.moves, gh.undos, gh.redos, gh.created, gh.completed
  )
}
