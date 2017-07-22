package models.queries.user

import java.util.UUID

import models.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Row, SingleRowQuery, Statement}
import models.settings.Settings
import models.user.User
import org.joda.time.LocalDateTime
import util.JsonSerializers

object UserQueries extends BaseQueries[User] {
  override protected val tableName = "users"
  override protected val columns = Seq("id", "username", "email", "prefs", "created")
  override protected val searchColumns = Seq("id::text", "username", "email")

  val insert = Insert
  def getById(id: UUID) = getBySingleId(id)
  def getByIds(ids: Seq[UUID]) = new ColSeqQuery("id", ids)

  def getAll(limit: Option[Int], offset: Option[Int]) = GetAll(orderBy = Some("id desc"), limit, offset)

  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val removeById = RemoveById
  def count(id: UUID) = Count(s"select count(*) c from $tableName where id = ?", Seq(id))

  case class DoesUserExist(id: UUID) extends SingleRowQuery[Boolean] {
    override def sql = s"select count(*) as c from $tableName where id = ?"
    override def values = Seq(id)
    override def map(row: Row): Boolean = row.as[Long]("c") == 1L
  }

  case class UpdateUser(u: User) extends Statement {
    override val sql = updateSql(Seq("username", "email", "prefs"))
    override val values = Seq(u.username, u.email, JsonSerializers.writeSettings(u.settings), u.id)
  }

  case class GetCreatedDate(id: UUID) extends FlatSingleRowQuery[LocalDateTime] {
    override def sql = s"select created from $tableName where id = ?"
    override def values = Seq(id)
    override def flatMap(row: Row) = row.asOpt[LocalDateTime]("created")
  }

  case class SetUsername(userId: UUID, username: Option[String]) extends Statement {
    override val sql = updateSql(Seq("username"))
    override val values = Seq(username, userId)
  }

  case class SetSettings(userId: UUID, settings: Settings) extends Statement {
    override val sql = updateSql(Seq("prefs"))
    override val values = Seq(JsonSerializers.writeSettings(settings), userId)
  }

  case class GetByUsername(username: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("username = ?"))
    override val values = Seq(username)
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  case class GetByEmail(email: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("email = ?"))
    override val values = Seq(email)
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val email = row.asOpt[String]("email")
    val username = row.asOpt[String]("username")
    val settings = JsonSerializers.readSettings(row.as[String]("prefs"))
    val created = row.as[LocalDateTime]("created")
    User(id, email, username, settings, created)
  }

  override protected def toDataSeq(u: User) = Seq(u.id, u.email, u.username, JsonSerializers.writeSettings(u.settings), u.created)
}
