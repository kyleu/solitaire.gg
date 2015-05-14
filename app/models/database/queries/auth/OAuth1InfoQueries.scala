package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OAuth1Info
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime

object OAuth1InfoQueries extends BaseQueries {
  override protected val tableName = "oauth1_info"
  override protected val columns = Seq("provider", "key", "token", "secret", "created")

  case class CreateOAuth1Info(l: LoginInfo, o: OAuth1Info) extends Statement {
    override val sql = insertSql
    override val values = Seq(l.providerID, l.providerKey, o.token, o.secret, new LocalDateTime())
  }

  case class FindOAuth1Info(l: LoginInfo) extends FlatSingleRowQuery[OAuth1Info] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey)
    override def flatMap(row: RowData) = {
      val token = row("token") match { case s: String => s }
      val secret = row("secret") match { case s: String => s }
      Some(OAuth1Info(token, secret))
    }
  }
}
