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
  override protected val columns = Seq("id", "username", "avatar", "profiles", "roles", "created")

  case class CreateUser(u: User) extends Statement {
    override val sql = insertSql
    override val values = {
      val profiles = u.profiles.map(l => l.providerID + ":" + l.providerKey).toArray
      val roles = u.roles.map(_.name).toArray
      Seq(u.id, u.username, u.avatar, profiles, roles, u.created)
    }
  }

  case class UpdateUser(u: User) extends Statement {
    override val sql = updateSql(Seq("username", "avatar", "profiles", "roles"))
    override val values = {
      val profiles = u.profiles.map(l => l.providerID + ":" + l.providerKey).toArray
      val roles = u.roles.map(_.name).toArray
      Seq(u.username, u.avatar, profiles, roles, u.id)
    }
  }

  case class SetAvatarUrl(userId: UUID, url: String) extends Statement {
    override val sql = updateSql(Seq("avatar"))
    override val values = {
      Seq(url, userId)
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

  case class FindUserByUsername(username: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql("username = ?")
    override val values = Seq(username)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindUserByProfile(loginInfo: LoginInfo) extends FlatSingleRowQuery[User] {
    override val sql = getSql("profiles @> ARRAY[?]::text[]")
    override val values = Seq(loginInfo.providerID + ":" + loginInfo.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class SearchUsers(q: String) extends Query[List[User]] {
    override val sql = getSql("id::character varying like lower(?) or lower(username) like lower(?)", Some("created desc"))
    override val values = Seq("%" + q + "%", "%" + q + "%")
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class RemoveUser(id: UUID) extends Statement {
    override val sql = removeByIdSql
    override val values = Seq(id)
  }

  private[this] def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => UUID.fromString(s) }
    val profiles = row("profiles") match {
      case arr: collection.mutable.ArrayBuffer[_] => arr.map { l =>
        val info = l.toString
        val delimiter = info.indexOf(':')
        val provider = info.substring(0, delimiter)
        val key = info.substring(delimiter + 1)
        LoginInfo(provider, key)
      }
    }
    val username = row("username") match { case s: String => Some(s); case _ => None }
    val avatar = row("avatar") match { case s: String => s }
    val roles = (row("roles") match { case ab: collection.mutable.ArrayBuffer[_] => ab }).map(x => Role(x.toString)).toSet
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    User(id, username, avatar, profiles, roles, created)
  }
}
