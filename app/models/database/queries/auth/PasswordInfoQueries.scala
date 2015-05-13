package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import models.database.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Statement}
import org.joda.time.LocalDateTime

object PasswordInfoQueries extends BaseQueries {
  override protected val tableName = "password_info"
  override protected val columns = Seq("provider", "key", "hasher", "password", "salt", "created")

  case class Create(l: LoginInfo, p: PasswordInfo) extends Statement {
    override val sql = insertSql
    override val values = Seq(l.providerID, l.providerKey, p.hasher, p.password, p.salt, new LocalDateTime()): Seq[Any]
  }

  case class Find(l: LoginInfo) extends FlatSingleRowQuery[PasswordInfo] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey): Seq[Any]

    override def flatMap(row: RowData) = Some(PasswordInfo(
      hasher = row("hasher").asInstanceOf[String],
      password = row("password").asInstanceOf[String],
      salt = Option(row("salt").asInstanceOf[String])
    ))
  }
}
