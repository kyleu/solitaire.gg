import java.util.UUID

import json.{ BaseSerializers, ResponseMessageSerializers }
import models.GameWon

import scala.scalajs.js.JSON

trait AnalyticsHelper extends AjaxHelper {
  private[this] val st = org.scalajs.dom.localStorage

  private[this] def newId = {
    val id = UUID.randomUUID
    st.setItem("device", id.toString)
    id -> true
  }

  protected[this] val (deviceId, newAccount) = Option(st.getItem("device")) match {
    case Some(deviceIdString) => try {
      UUID.fromString(deviceIdString) -> false
    } catch {
      case x: Exception => newId
    }
    case None => newId
  }

  if (newAccount) {
    onInstall(System.currentTimeMillis)
  } else {
    onOpen(System.currentTimeMillis)
  }

  protected[this] def onInstall(occurred: Long) = {
    val event = scalajs.js.Dynamic.literal(
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/install/" + deviceId, JSON.stringify(event))
  }

  protected[this] def onOpen(occurred: Long) = {
    val event = scalajs.js.Dynamic.literal(
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/open/" + deviceId, JSON.stringify(event))
  }

  protected[this] def onGameStart(gameId: UUID, rules: String, seed: Int, occurred: Long) = {
    val event = scalajs.js.Dynamic.literal(
      "gameId" -> gameId.toString,
      "rules" -> rules,
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/game-start/" + deviceId, JSON.stringify(event))
  }

  protected[this] def onGameWon(resultJson: String) = {
    sendNetworkPost("/a/game-won/" + deviceId, resultJson)
  }

  protected[this] def onGameResigned(resultJson: String) = {
    val event = scalajs.js.Dynamic.literal(
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/game-resigned/" + deviceId, JSON.stringify(event))
  }
}
