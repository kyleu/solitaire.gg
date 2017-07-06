package services.audit

import utils.FutureUtils.defaultContext
import play.api.libs.ws.WSClient
import utils.{Config, Logging}

import io.circe._

import scala.concurrent.Future

@javax.inject.Singleton
class NotificationService @javax.inject.Inject() (ws: WSClient, config: Config) extends Logging {
  private[this] val defaultIcon = "http://solitaire.gg/assets/images/ui/favicon/favicon.png"

  def alert(msg: String, channel: String = "#general", username: String = "Solitaire.gg", iconUrl: String = defaultIcon) = if (config.slackEnabled) {
    val body = Json.fromFields(Seq(
      "channel" -> Json.fromString(channel),
      "username" -> Json.fromString(username),
      "icon_url" -> Json.fromString(iconUrl),
      "text" -> Json.fromString(msg)
    ))

    val call = ws.url(config.slackUrl).withHttpHeaders("Accept" -> "application/json")
    val f = call.post(body.spaces2)
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
