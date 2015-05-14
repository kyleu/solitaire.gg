package models.database.queries.auth

import java.util.UUID

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import models.database.queries.BaseQueries
import models.database.{ Query, FlatSingleRowQuery, Statement }
import models.user.{ Role, User }
import org.joda.time.LocalDateTime

object UserQueries extends BaseQueries {
  override protected val tableName = "users"
  override protected val columns = Seq(
    "id", "login_infos", "username", "email", "avatar_url", "first_name", "last_name", "full_name", "gender", "roles", "created"
  )

  case class CreateUser(u: User) extends Statement {
    override val sql = insertSql
    override val values = {
      val loginInfos = u.loginInfos.map(l => l.providerID + ":" + l.providerKey).toArray
      val roles = u.roles.map(_.name).toArray
      Seq(u.id, loginInfos, u.username, u.email, u.avatarUrl, u.firstName, u.lastName, u.fullName, u.gender, roles, u.created)
    }
  }

  case class AddRole(id: UUID, role: Role) extends Statement {
    override val sql = s"update $tableName set roles = array_append(roles, ?) where id = ?"
    override val values = Seq(role.name, id)
  }

  case class FindUser(id: UUID) extends FlatSingleRowQuery[User] {
    override val sql = getSql("id = ?")
    override val values = Seq(id)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindUserByLoginInfo(loginInfo: LoginInfo) extends FlatSingleRowQuery[User] {
    override val sql = getSql("login_infos @> ARRAY[?]::text[]")
    override val values = Seq(loginInfo.providerID + ":" + loginInfo.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class SearchUsers(q: String) extends Query[List[User]] {
    override val sql = getSql("id::character varying like lower(?) or lower(username) like lower(?) or lower(full_name) like lower(?)", Some("created desc"))
    override val values = Seq("%" + q + "%", "%" + q + "%", "%" + q + "%")
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class RemoveUser(id: UUID) extends Statement {
    override val sql = removeByIdSql
    override val values = Seq(id)
  }

  private[this] def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val loginInfos = row("login_infos") match {
      case arr: collection.mutable.ArrayBuffer[_] => arr.map { l =>
        val info = l.toString
        val delimiter = info.indexOf(':')
        val provider = info.substring(0, delimiter)
        val key = info.substring(delimiter + 1)
        LoginInfo(provider, key)
      }
    }
    val username = row("username") match { case s: String => Some(s); case _ => None }
    val email = row("email") match { case s: String => Some(s); case _ => None }
    val avatarUrl = row("avatar_url") match { case s: String => Some(s); case _ => None }
    val firstName = row("first_name") match { case s: String => Some(s); case _ => None }
    val lastName = row("last_name") match { case s: String => Some(s); case _ => None }
    val fullName = row("full_name") match { case s: String => Some(s); case _ => None }
    val gender = row("gender") match { case s: String => Some(s); case _ => None }
    val roles = (row("roles") match { case ab: collection.mutable.ArrayBuffer[_] => ab }).map(x => Role(x.toString)).toSet
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    User(id, loginInfos, username, email, avatarUrl, firstName, lastName, fullName, gender, roles, created)
  }
}
