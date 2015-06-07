package models.database.queries.auth

import java.util.UUID

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Statement }
import models.user.{ Role, User }
import org.joda.time.LocalDateTime

object UserQueries extends BaseQueries[User] {
  override protected val tableName = "users"
  override protected val columns = Seq("id", "username", "avatar", "color", "profiles", "roles", "created")
  override protected val searchColumns = Seq("id::text", "username")

  case class UpdateUser(u: User) extends Statement {
    override val sql = updateSql(Seq("username", "avatar", "color", "profiles", "roles"))
    override val values = {
      val profiles = u.profiles.map(l => l.providerID + ":" + l.providerKey).toArray
      val roles = u.roles.map(_.name).toArray
      Seq(u.username, u.avatar, u.color, profiles, roles, u.id)
    }
  }

  case class SetAvatarUrl(userId: UUID, url: String) extends Statement {
    override val sql = updateSql(Seq("avatar"))
    override val values = Seq(url, userId)
  }

  case class SetColor(userId: UUID, color: String) extends Statement {
    override val sql = updateSql(Seq("color"))
    override val values = Seq(color, userId)
  }

  case class AddRole(id: UUID, role: Role) extends Statement {
    override val sql = s"update $tableName set roles = array_append(roles, ?) where id = ?"
    override val values = Seq(role.name, id)
  }

  case class FindUserByUsername(username: String) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("username = ?"))
    override val values = Seq(username)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindUserByProfile(loginInfo: LoginInfo) extends FlatSingleRowQuery[User] {
    override val sql = getSql(Some("profiles @> ARRAY[?]::text[]"))
    override val values = Seq(loginInfo.providerID + ":" + loginInfo.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  override protected def fromRow(row: RowData) = {
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
    val color = row("color") match { case s: String => s }
    val roles = (row("roles") match { case ab: collection.mutable.ArrayBuffer[_] => ab }).map(x => Role(x.toString)).toSet
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    User(id, username, avatar, color, profiles, roles, created)
  }

  override protected def toDataSeq(u: User) = {
    val profiles = u.profiles.map(l => l.providerID + ":" + l.providerKey).toArray
    val roles = u.roles.map(_.name).toArray
    Seq(u.id, u.username, u.avatar, u.color, profiles, roles, u.created)
  }
}
