package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OpenIDInfo
import models.database.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Statement}
import org.joda.time.LocalDateTime
import play.api.libs.json.{JsValue, Json}

object OpenIdInfoQueries extends BaseQueries {
  override protected val tableName = "openid_info"
  override protected val columns = Seq("provider", "key", "id", "attributes", "created")

  case class CreateOpenIdInfo(l: LoginInfo, o: OpenIDInfo) extends Statement {
    override val sql = insertSql
    val attributes = Json.prettyPrint(Json.toJson(o.attributes))
    override val values = Seq(l.providerID, l.providerKey, o.id, attributes, new LocalDateTime())
  }

  case class FindOpenIdInfo(l: LoginInfo) extends FlatSingleRowQuery[OpenIDInfo] {
    override val sql = getSql("provider = ? and key = ?")
    override val values = Seq(l.providerID, l.providerKey)

    override def flatMap(row: RowData) = {
      val id = row("id") match { case s: String => s }
      val attributesString = row("attributes") match { case s: String => s }
      val attributes = Json.parse(attributesString).as[Map[String, JsValue]].map(x => x._1 -> x._2.as[String])
      Some(OpenIDInfo(id, attributes))
    }
  }
}
