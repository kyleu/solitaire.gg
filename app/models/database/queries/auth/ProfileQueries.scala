package models.database.queries.auth

import java.util.UUID

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.CommonSocialProfile
import models.database.queries.BaseQueries
import models.database.{ Query, FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime

object ProfileQueries extends BaseQueries[CommonSocialProfile] {
  override protected val tableName = "user_profiles"
  override protected val columns = Seq("provider", "key", "email", "first_name", "last_name", "full_name", "avatar_url", "created")
  override protected val searchColumns = Seq("key", "email", "first_name", "last_name", "full_name")

  case class CreateProfile(p: CommonSocialProfile) extends Statement {
    override val sql = insertSql
    override val values = Seq(p.loginInfo.providerID, p.loginInfo.providerKey, p.email, p.firstName, p.lastName, p.fullName, p.avatarURL, new LocalDateTime())
  }

  case class FindProfile(provider: String, key: String) extends FlatSingleRowQuery[CommonSocialProfile] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(provider, key)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindProfilesByUser(id: UUID) extends Query[List[CommonSocialProfile]] {
    override val sql = s"select ${columns.mkString(", ")} from $tableName p " +
      "where (p.provider || ':' || p.key) in (select unnest(profiles) from users where users.id = ?)"
    override val values = Seq(id)
    override def reduce(rows: Iterator[RowData]) = rows.map(fromRow).toList
  }

  case class RemoveProfile(provider: String, key: String) extends Statement {
    override val sql = s"delete from $tableName where provider = ? and key = ?"
    override val values = Seq(provider, key)
  }

  override protected def fromRow(row: RowData) = {
    val loginInfo = LoginInfo(
      providerID = row("provider") match { case s: String => s },
      providerKey = row("key") match { case s: String => s }
    )
    val firstName = row("first_name") match { case s: String => Some(s); case _ => None }
    val lastName = row("last_name") match { case s: String => Some(s); case _ => None }
    val fullName = row("full_name") match { case s: String => Some(s); case _ => None }
    val email = row("email") match { case s: String => Some(s); case _ => None }
    val avatarUrl = row("avatar_url") match { case s: String => Some(s); case _ => None }

    CommonSocialProfile(loginInfo, firstName, lastName, fullName, email, avatarUrl)
  }
}
