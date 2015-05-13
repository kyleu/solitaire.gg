package models.database.queries.auth

import com.github.mauricio.async.db.RowData
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.OAuth2Info
import models.database.queries.BaseQueries
import models.database.{FlatSingleRowQuery, Statement}
import org.joda.time.LocalDateTime
import play.api.libs.json.{JsValue, Json}

object OAuth2InfoQueries extends BaseQueries {
   override protected val tableName = "oauth2_info"
   override protected val columns = Seq("provider", "key", "access_token", "token_type", "expires_in", "refresh_token", "params", "created")

   case class Create(l: LoginInfo, o: OAuth2Info) extends Statement {
     override val sql = insertSql
     override val values = {
       val params = o.params.map(p => Json.prettyPrint(Json.toJson(p)))
       Seq(l.providerID, l.providerKey, o.accessToken, o.tokenType, o.expiresIn, o.refreshToken, params, new LocalDateTime()): Seq[Any]
     }
   }

   case class Find(l: LoginInfo) extends FlatSingleRowQuery[OAuth2Info] {
     override val sql = getSql("provider = ? and key = ?")
     override val values = Seq(l.providerID, l.providerKey): Seq[Any]
     override def flatMap(row: RowData) = {
       val params = Option(row("params").asInstanceOf[String]).map { p =>
         Json.parse(p).as[Map[String, JsValue]].map(x => x._1 -> x._2.as[String])
       }
       Some(OAuth2Info(
         accessToken = row("access_token").asInstanceOf[String],
         tokenType = Option(row("token_type").asInstanceOf[String]),
         expiresIn = Option(row("expires_in").asInstanceOf[Int]),
         refreshToken = Option(row("refresh_token").asInstanceOf[String]),
         params = params
       ))
     }
   }
 }
