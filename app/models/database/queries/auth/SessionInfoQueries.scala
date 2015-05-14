package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime

object SessionInfoQueries extends BaseQueries {
  override protected val tableName = "session_info"
  override protected val columns = Seq("id", "provider", "key", "last_used", "expiration", "fingerprint", "created")

  case class CreateSessionInfo(s: CookieAuthenticator) extends Statement {
    override val sql = insertSql
    override val values = Seq(
      s.id,
      s.loginInfo.providerID,
      s.loginInfo.providerKey,
      s.lastUsedDate.toLocalDateTime,
      s.expirationDate.toLocalDateTime,
      s.fingerprint,
      new LocalDateTime()
    )
  }

  case class RemoveSessionInfo(id: String) extends Statement {
    override val sql = removeByIdSql
    override val values = Seq(id)
  }

  case class FindSessionInfo(id: String) extends FlatSingleRowQuery[CookieAuthenticator] {
    override val sql = getByIdSql
    override val values = Seq(id)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class FindSessionInfoByLoginInfo(l: LoginInfo) extends FlatSingleRowQuery[CookieAuthenticator] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  private[this] def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => s }
    val provider = row("provider") match { case s: String => s }
    val key = row("key") match { case s: String => s }
    val lastUsed = row("last_used") match { case ldt: LocalDateTime => ldt.toDateTime }
    val expiration = row("expiration") match { case ldt: LocalDateTime => ldt.toDateTime }
    val fingerprint = row("fingerprint") match { case s: String => Some(s); case _ => None }
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    CookieAuthenticator(id, LoginInfo(provider, key), lastUsed, expiration, None, fingerprint)
  }
}
