package services.audit

import utils.FutureUtils.defaultContext
import play.api.libs.json.{JsObject, JsString, Json}
import play.api.libs.ws.WSClient
import utils.{Config, Logging}

import scala.concurrent.Future

@javax.inject.Singleton
class NotificationService @javax.inject.Inject() (ws: WSClient, config: Config) extends Logging {
  private[this] val defaultIcon = "http://solitaire.gg/assets/images/ui/favicon/favicon.png"

  def alert(msg: String, channel: String = "#general", username: String = "Solitaire.gg", iconUrl: String = defaultIcon) = if (config.slackEnabled) {
    val body = JsObject(Seq(
      "channel" -> JsString(channel),
      "username" -> JsString(username),
      "icon_url" -> JsString(iconUrl),
      "text" -> JsString(msg)
    ))

    val call = ws.url(config.slackUrl).withHttpHeaders("Accept" -> "application/json")
    val f = call.post(Json.prettyPrint(body))
    val ret = f.map { x =>
      if (x.status == 200) {
        "OK"
      } else {
        log.warn("Unable to post to Slack (" + x.status + "): [" + x.body + "].")
        "ERROR"
      }
    }
    ret.failed.foreach(x => log.warn("Unable to post to Slack.", x))
    ret
  } else {
    Future.successful("Slack is not enabled.")
  }
}
