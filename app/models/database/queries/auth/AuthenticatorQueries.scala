package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import models.database.queries.BaseQueries
import models.database.{ Statement, FlatSingleRowQuery }
import org.joda.time.LocalDateTime

object AuthenticatorQueries extends BaseQueries[CookieAuthenticator] {
  override protected val tableName = "session_info"
  override protected val columns = Seq("id", "provider", "key", "last_used", "expiration", "fingerprint", "created")
  override protected val searchColumns = Seq("id::text", "key")

  val insert = Insert
  val getById = GetById
  val removeById = RemoveById

  case class FindSessionInfoByLoginInfo(l: LoginInfo) extends FlatSingleRowQuery[CookieAuthenticator] {
    override val sql = getSql(Some("provider = ? and key = ?"))
    override val values = Seq(l.providerID, l.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  case class UpdateAuthenticator(ca: CookieAuthenticator) extends Statement {
    override val sql = updateSql(Seq("provider", "key", "last_used", "expiration", "fingerprint"))
    override val values = Seq(
      ca.loginInfo.providerID,
      ca.loginInfo.providerKey,
      ca.lastUsedDate.toLocalDateTime,
      ca.expirationDate.toLocalDateTime,
      ca.fingerprint,
      ca.id
    )
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => s }
    val provider = row("provider") match { case s: String => s }
    val key = row("key") match { case s: String => s }
    val lastUsed = row("last_used") match { case ldt: LocalDateTime => ldt.toDateTime }
    val expiration = row("expiration") match { case ldt: LocalDateTime => ldt.toDateTime }
    val fingerprint = row("fingerprint") match { case s: String => Some(s); case _ => None }
    val created = row("created") match { case ldt: LocalDateTime => ldt }
    CookieAuthenticator(id, LoginInfo(provider, key), lastUsed, expiration, None, fingerprint)
  }

  override protected def toDataSeq(ca: CookieAuthenticator) = Seq(
    ca.id,
    ca.loginInfo.providerID,
    ca.loginInfo.providerKey,
    ca.lastUsedDate.toLocalDateTime,
    ca.expirationDate.toLocalDateTime,
    ca.fingerprint,
    new LocalDateTime()
  )
}
