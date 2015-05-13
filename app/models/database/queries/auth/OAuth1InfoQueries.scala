package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OAuth1Info
import models.database.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Statement}
import org.joda.time.LocalDateTime

object OAuth1InfoQueries extends BaseQueries {
  override protected val tableName = "oauth1_info"
  override protected val columns = Seq("provider", "key", "token", "secret", "created")

  case class Create(l: LoginInfo, o: OAuth1Info) extends Statement {
    override val sql = insertSql
    override val values = Seq(l.providerID, l.providerKey, o.token, o.secret, new LocalDateTime()): Seq[Any]
  }

  case class Find(l: LoginInfo) extends FlatSingleRowQuery[OAuth1Info] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey): Seq[Any]
    override def flatMap(row: RowData) = Some(OAuth1Info(row("token").asInstanceOf[String], row("secret").asInstanceOf[String]))
  }
}
