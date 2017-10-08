package models.queries.history

import java.util.UUID

import models.queries.BaseQueries
import models.database.{Query, Row, Statement}
import models.history.GameHistory
import java.time.{LocalDate, LocalDateTime}

object GameHistoryQueries extends BaseQueries[GameHistory] {
  override protected val tableName = "games"
  override protected val columns = Seq(
    "id", "rules", "seed", "status", "player",
    "cards", "moves", "undos", "redos", "score",
    "created", "first_move", "completed"
  )
  override protected val searchColumns = Seq("id::text", "rules", "seed::text", "status", "player::text")

  def getById(id: UUID) = getBySingleId(id)
  case class GetByIds(ids: Seq[UUID]) extends SeqQuery(s"where id in (${ids.map(_ => "?").mkString(", ")})", ids)
  def getAll(limit: Option[Int], offset: Option[Int]) = GetAll(orderBy = Some("id desc"), limit, offset)

  val insert = Insert
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  def removeById(id: UUID) = RemoveById(Seq(id))
  val truncate = Truncate

  case class OnComplete(gh: GameHistory) extends Statement {
    override val sql = updateSql(Seq("moves", "undos", "redos", "score", "first_move", "completed", "status"))
    override val values = Seq[Any](gh.moves, gh.undos, gh.redos, gh.score, gh.firstMove, gh.completed, gh.status.value, gh.id)
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

  def getCountForUser(userId: UUID) = Count(s"select count(*) as c from $tableName where player = ?", Seq(userId))

  case class GetByUser(userId: UUID, limit: Option[Int], offset: Option[Int]) extends SeqQuery(
    additionalSql = s"where player = ? order by created desc${limit.map(" limit " + _).getOrElse("")}${offset.map(" offset " + _).getOrElse("")}",
    values = Seq(userId)
  )
  case class GetByUserIds(userIds: Seq[UUID]) extends SeqQuery(s"where player in (${userIds.map(_ => "?").mkString(", ")})", userIds)

  case class GetBySeed(rules: String, seed: Int, limit: Option[Int], offset: Option[Int]) extends SeqQuery(
    additionalSql = s"where rules = ? and seed = ? order by created desc${limit.map(" limit " + _).getOrElse("")}${offset.map(" offset " + _).getOrElse("")}",
    values = Seq(rules, seed)
  )

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val rules = row.as[String]("rules")
    val seed = row.as[Int]("seed")
    val status = GameHistory.Status.withValue(row.as[String]("status").head.toString)
    val player = row.as[UUID]("player")
    val cards = row.as[Int]("cards")
    val moves = row.as[Int]("moves")
    val undos = row.as[Int]("undos")
    val redos = row.as[Int]("redos")
    val score = row.as[Int]("score")
    val created = row.as[LocalDateTime]("created")
    val firstMove = row.asOpt[LocalDateTime]("first_move")
    val completed = row.asOpt[LocalDateTime]("completed")
    GameHistory(id, rules, seed, status, player, cards, moves, undos, redos, score, created, firstMove, completed)
  }

  override protected def toDataSeq(gh: GameHistory) = Seq[Any](
    gh.id, gh.rules, gh.seed, gh.status.value, gh.player, gh.cards, gh.moves, gh.undos, gh.redos, gh.score, gh.created, gh.firstMove, gh.completed
  )
}
