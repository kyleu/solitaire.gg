package models.database.queries.auth

import java.util.UUID

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import models.database.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Statement}
import models.user.{Role, User}
import org.joda.time.LocalDateTime

object UserQueries extends BaseQueries {
  override protected val tableName = "users"
  override protected val columns = Seq(
    "id", "login_infos", "username", "email", "avatar_url", "first_name", "last_name", "full_name", "gender", "roles", "created"
  )

  case class Create(u: User) extends Statement {
    override val sql = insertSql
    override val values = {
      val loginInfos = u.loginInfos.map(l => l.providerID + ":" + l.providerKey).toArray
      val roles = u.roles.map(_.name).toArray
      Seq(u.id, loginInfos, u.username, u.email, u.avatarUrl, u.firstName, u.lastName, u.fullName, u.gender, roles, new LocalDateTime()): Seq[Any]
    }
  }

  case class AddRole(id: UUID, role: Role) extends Statement {
    override val sql = s"update $tableName set roles = array_append(roles, ?) where id = ?"
    override val values = Seq(role.name, id)
  }

  case class Find(id: UUID) extends FlatSingleRowQuery[User] {
    override val sql = getSql("id = ?")
    override val values = Seq(id): Seq[Any]
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindByLoginInfo(loginInfo: LoginInfo) extends FlatSingleRowQuery[User] {
    override val sql = getSql("login_infos @> ARRAY[?]::text[]")
    override val values = Seq(loginInfo.providerID + ":" + loginInfo.providerKey): Seq[Any]
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  private[this] def fromRow(row: RowData) = {
    val id = UUID.fromString(row("id").asInstanceOf[String])
    val loginInfos = row("login_infos").asInstanceOf[collection.mutable.ArrayBuffer[String]].map { l =>
      val provider = l.substring(0, l.indexOf(':'))
      val key = l.substring(l.indexOf(':') + 1)
      LoginInfo(provider, key)
    }
    val username = Option(row("username") match { case s: String => s; case null => null })
    val email = Option(row("email") match { case s: String => s; case null => null })
    val avatarUrl = Option(row("avatar_url") match { case s: String => s; case null => null })
    val firstName = Option(row("first_name") match { case s: String => s; case null => null })
    val lastName = Option(row("last_name") match { case s: String => s; case null => null })
    val fullName = Option(row("full_name") match { case s: String => s; case null => null })
    val gender = Option(row("gender") match { case s: String => s; case null => null })
    val roles = (row("roles") match { case ab: collection.mutable.ArrayBuffer[_] => ab }).map(x => Role(x.toString)).toSet
    User(id, loginInfos, username, email, avatarUrl, firstName, lastName, fullName, gender, roles)
  }
}
