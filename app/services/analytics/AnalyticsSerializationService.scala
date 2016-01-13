package services.analytics

import play.api.libs.json.{ JsString, JsObject, Json }
import utils.Logging

object AnalyticsSerializationService extends Logging {
  def loadOpen(s: String) = try {
    getJson(s)
  } catch {
    case x: Throwable =>
      val msg = s"Cannot parse json: [${utils.Formatter.className(x)}]"
      log.error(msg, x)
      JsObject(Seq("error" -> JsString(msg)))
  }

  private[this] def getJson(s: String) = Json.toJson(s)
}
