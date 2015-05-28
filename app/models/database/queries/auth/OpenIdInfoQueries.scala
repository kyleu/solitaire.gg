package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OpenIDInfo
import models.database.queries.BaseQueries
import models.database.{ FlatSingleRowQuery, Statement }
import org.joda.time.LocalDateTime
import play.api.libs.json.{ JsValue, Json }

object OpenIdInfoQueries extends BaseQueries[OpenIDInfo] {
  override protected val tableName = "openid_info"
  override protected val columns = Seq("provider", "key", "id", "attributes", "created")
  override protected val searchColumns = Seq("key")

  case class CreateOpenIdInfo(l: LoginInfo, o: OpenIDInfo) extends Statement {
    override val sql = insertSql
    val attributes = Json.prettyPrint(Json.toJson(o.attributes))
    override val values = Seq(l.providerID, l.providerKey, o.id, attributes, new LocalDateTime())
  }

  case class UpdateOpenIdInfo(l: LoginInfo, o: OpenIDInfo) extends Statement {
    override val sql = s"update $tableName set id = ?, attributes = ?, created = ? where provider = ? and key = ?"
    val attributes = Json.prettyPrint(Json.toJson(o.attributes))
    override val values = Seq(o.id, attributes, new LocalDateTime(), l.providerID, l.providerKey)
  }

  case class FindOpenIdInfo(l: LoginInfo) extends FlatSingleRowQuery[OpenIDInfo] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey)
    override def flatMap(row: RowData) = Some(fromRow(row))
  }

  override protected def fromRow(row: RowData) = {
    val id = row("id") match { case s: String => s }
    val attributesString = row("attributes") match { case s: String => s }
    val attributes = Json.parse(attributesString).as[Map[String, JsValue]].map(x => x._1 -> x._2.as[String])
    OpenIDInfo(id, attributes)
  }
}
