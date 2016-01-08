import java.util.UUID

import json.BaseSerializers
import models.{ GameLost, GameWon }
import models.analytics._

import upickle.legacy._

import json.BaseSerializers._

trait AnalyticsHelper extends AjaxHelper {
  protected var gameStatus = "init"

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

  private[this] def getDeviceInfo = Map.empty[String, String]

  protected[this] def onInstall(occurred: Long) = {
    val event = InstallEvent(deviceId = deviceId, deviceInfo = getDeviceInfo, occurred = occurred)
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/install/" + deviceId, json)
  }

  protected[this] def onOpen(occurred: Long) = {
    val event = OpenEvent(deviceId = deviceId, deviceInfo = getDeviceInfo, occurred = occurred)
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/open/" + deviceId, json)
  }

  protected[this] def onGameStart(gameId: UUID, rules: String, seed: Int, occurred: Long) = {
    val event = GameStartEvent(
      deviceId = deviceId,
      deviceInfo = getDeviceInfo,
      gameId = gameId,
      rules = rules,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/game-start/" + deviceId, json)
  }

  protected[this] def onGameWon(message: GameWon, occurred: Long) = {
    val event = GameWonEvent(
      deviceId = deviceId,
      message = message,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/game-won/" + deviceId, json)
  }

  protected[this] def onGameResigned(message: GameLost, occurred: Long) = {
    val event = GameResignedEvent(
      deviceId = deviceId,
      message = message,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/game-resigned/" + deviceId, json)
  }
}
