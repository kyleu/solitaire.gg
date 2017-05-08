package models.queries.user

import java.util.UUID

import models.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Row, SingleRowQuery, Statement}
import models.user.{User, UserPreferences}
import org.joda.time.LocalDateTime

import upickle.default._

object UserQueries extends BaseQueries[User] {
  override protected val tableName = "users"
  override protected val columns = Seq("id", "username", "email", "prefs", "created")
  override protected val searchColumns = Seq("id::text", "username", "email")

  val insert = Insert
  def getById(id: UUID) = getBySingleId(id)
  def searchCount(q: String, groupBy: Option[String] = None) = new SearchCount(q, groupBy)
  val search = Search
  val removeById = RemoveById

  case class DoesUserExist(id: UUID) extends SingleRowQuery[Boolean] {
    override def sql = s"select count(*) as c from users where id = ?"
    override def values = Seq(id)
    override def map(row: Row): Boolean = row.as[Long]("c") == 1L
  }

  case class UpdateUser(u: User) extends Statement {
    override val sql = updateSql(Seq("username", "email", "prefs"))
    override val values = Seq(u.username, u.email, write(u.preferences), u.id)
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

  case class SetPreferences(userId: UUID, userPreferences: UserPreferences) extends Statement {
    override val sql = updateSql(Seq("prefs"))
    override val values = Seq(write(userPreferences), userId)
  }

  case class FindUserByUsername(username: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("username = ?"))
    override val values = Seq(username)
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  override protected def fromRow(row: Row) = {
    val id = row.as[UUID]("id")
    val email = row.asOpt[String]("email")
    val username = row.asOpt[String]("username")
    val preferences = read[UserPreferences](row.as[String]("prefs"))
    val created = row.as[LocalDateTime]("created")
    User(id, email, username, preferences, created)
  }

  override protected def toDataSeq(u: User) = Seq(u.id, u.email, u.username, write(u.preferences), u.created)
}
