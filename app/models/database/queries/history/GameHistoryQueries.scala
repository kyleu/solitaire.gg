package models.database.queries.history

import java.util.UUID

import models.database.queries.BaseQueries
import models.database.{ Query, Row, Statement }
import models.history.GameHistory
import org.joda.time.{ LocalDate, LocalDateTime }

object GameHistoryQueries extends BaseQueries[GameHistory] {
  override protected val tableName = "games"
  override protected val columns = Seq(
    "id", "seed", "rules", "status", "player",
    "cards", "moves", "undos", "redos",
    "created", "completed", "logged"
  )
  override protected val searchColumns = Seq("id::text", "seed::text", "rules", "status", "player::text")

  def getById(id: UUID) = getBySingleId(id)
  val insert = Insert
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val removeById = RemoveById

  case class UpdateGameHistory(
      id: UUID, status: String, moves: Int, undos: Int, redos: Int,
      completed: Option[LocalDateTime], logged: Option[LocalDateTime]
  ) extends Statement {
    override val sql = updateSql(Seq("status", "moves", "undos", "redos", "completed", "logged"))
    override val values = Seq[Any](status, moves, undos, redos, completed, logged, id)
  }

  case class UpdateGameHistoryLoggedTime(id: UUID, logged: LocalDateTime) extends Statement {
    override val sql = updateSql(Seq("logged"))
    override val values = Seq[Any](logged, id)
  }

  case class GetGameHistoriesByDayAndStatus(d: LocalDate, status: String) extends Query[Seq[GameHistory]] {
    override def sql = getSql(whereClause = Some("completed >= ? and completed < ? and status = ?"), orderBy = Some("completed"))
    override def values = Seq(d, d.plusDays(1), status)
    override def reduce(rows: Iterator[Row]) = rows.map(fromRow).toList
  }

  case class GetGameHistoryIdsForUser(userId: UUID) extends Query[List[UUID]] {
    override val sql = s"select id from $tableName where player = ?"
    override val values = Seq(userId)
    override def reduce(rows: Iterator[Row]) = rows.map(_.as[UUID]("id")).toList
  }

  def getGameHistoryCountForUser(userId: UUID) = new Count(s"select count(*) as c from $tableName where player = ?", Seq(userId))

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val seed = row.as[Int]("seed")
    val rules = row.as[String]("rules")
    val status = row.as[String]("status")
    val player = row.as[UUID]("player")
    val cards = row.as[Int]("cards")
    val moves = row.as[Int]("moves")
    val undos = row.as[Int]("undos")
    val redos = row.as[Int]("redos")
    val created = row.as[LocalDateTime]("created")
    val completed = row.asOpt[LocalDateTime]("completed")
    val logged = row.asOpt[LocalDateTime]("logged")
    GameHistory(id, seed, rules, status, player, cards, moves, undos, redos, created, completed, logged)
  }

  override protected def toDataSeq(gh: GameHistory) = Seq[Any](
    gh.id, gh.seed, gh.rules, gh.status, gh.player,
    gh.cards, gh.moves, gh.undos, gh.redos,
    gh.created, gh.completed, gh.logged
  )
}
