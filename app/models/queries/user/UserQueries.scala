package models.queries.user

import java.util.UUID

import com.mohiva.play.silhouette.api.LoginInfo
import models.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Row, SingleRowQuery, Statement }
import models.user.{ Role, User, UserPreferences }
import org.joda.time.LocalDateTime
import play.api.libs.json.Json
import utils.json.UserSerializers._

object UserQueries extends BaseQueries[User] {
  override protected val tableName = "users"
  override protected val columns = Seq("id", "username", "prefs", "profiles", "roles", "created")
  override protected val searchColumns = Seq("id::text", "username")

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
    override val sql = updateSql(Seq("username", "prefs", "profiles", "roles"))
    override val values = {
      val profiles = u.profiles.map(l => s"${l.providerID}:${l.providerKey}").toArray
      val roles = u.roles.map(_.name).toArray
      val prefs = Json.toJson(u.preferences).toString()
      Seq(u.username, prefs, profiles, roles, u.id)
    }
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
    override val values = Seq(Json.toJson(userPreferences).toString(), userId)
  }

  case class AddRole(id: UUID, role: Role) extends Statement {
    override val sql = s"update $tableName set roles = array_append(roles, ?) where id = ?"
    override val values = Seq(role.name, id)
  }

  case class RemoveRole(id: UUID, role: Role) extends Statement {
    override val sql = s"update $tableName set roles = array_remove(roles, ?) where id = ?"
    override val values = Seq(role.name, id)
  }

  case class FindUserByUsername(username: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("username = ?"))
    override val values = Seq(username)
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  case class FindUserByProfile(loginInfo: LoginInfo) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("profiles @> ARRAY[?]::text[]"))
    override val values = Seq(s"${loginInfo.providerID}:${loginInfo.providerKey}")
    override def flatMap(row: Row) = Some(fromRow(row))
  }

  case object CountAdmins extends SingleRowQuery[Int]() {
    override def sql = "select count(*) as c from users where 'admin' = any(roles)"
    override def map(row: Row) = row.as[Long]("c").toInt
  }

  override protected def fromRow(row: Row) = UserQuerySerializers.fromRow(row)
  override protected def toDataSeq(u: User) = UserQuerySerializers.toDataSeq(u)
}
