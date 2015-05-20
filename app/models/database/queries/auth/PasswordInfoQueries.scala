package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime

object PasswordInfoQueries extends BaseQueries {
  override protected val tableName = "password_info"
  override protected val columns = Seq("provider", "key", "hasher", "password", "salt", "created")

  case class CreatePasswordInfo(l: LoginInfo, p: PasswordInfo) extends Statement {
    override val sql = insertSql
    override val values = Seq(l.providerID, l.providerKey, p.hasher, p.password, p.salt, new LocalDateTime())
  }

  case class UpdatePasswordInfo(l: LoginInfo, o: PasswordInfo) extends Statement {
    override val sql = s"update $tableName set hasher = ?, password = ?, salt = ?, created = ? where provider = ? and key = ?"
    override val values = Seq(o.hasher, o.password, o.salt, new LocalDateTime(), l.providerID, l.providerKey)
  }

  case class FindPasswordInfo(l: LoginInfo) extends FlatSingleRowQuery[PasswordInfo] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey)

    override def flatMap(row: RowData) = Some(PasswordInfo(
      hasher = row("hasher") match { case s: String => s },
      password = row("password") match { case s: String => s },
      salt = row("salt") match { case s: String => Some(s); case _ => None }
    ))
  }
}
