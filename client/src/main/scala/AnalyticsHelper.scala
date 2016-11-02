import java.util.UUID

import json.BaseSerializers
import models.{GameLost, GameWon}
import models.analytics._
import upickle.Js

import upickle.legacy._

import json.BaseSerializers._

import scala.scalajs.js

trait AnalyticsHelper extends AjaxHelper {
  protected var gameStatus = "init"
  protected def getRequests: Seq[Seq[String]]

  private[this] val sessionId = UUID.randomUUID
  private[this] val st = org.scalajs.dom.window.localStorage
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

  protected[this] def onError(error: js.Dynamic, occurred: Long) = {
    val event = ErrorEvent(
      deviceId = deviceId,
      sessionId = sessionId,
      message = error.message.toString,
      url = error.url.toString,
      line = try { error.line.toString.toInt } catch { case x: Exception => -1 },
      col = try { error.col.toString.toInt } catch { case x: Exception => -1 },
      stack = Option(error.stack).map(_.toString),
      deviceInfo = deviceInfo,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/error/" + deviceId, json)
  }

  protected[this] def onInstall(occurred: Long) = {
    val event = InstallEvent(deviceId = deviceId, sessionId = sessionId, deviceInfo = deviceInfo, occurred = occurred)
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/install/" + deviceId, json)
  }

  protected[this] def onOpen(occurred: Long) = {
    val event = OpenEvent(deviceId = deviceId, sessionId = sessionId, deviceInfo = deviceInfo, occurred = occurred)
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/open/" + deviceId, json)
  }

  protected[this] def onGameStart(gameId: UUID, rules: String, seed: Int, occurred: Long) = {
    val event = GameStartEvent(
      deviceId = deviceId,
      sessionId = sessionId,
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
      sessionId = sessionId,
      message = message,
      requests = getRequests,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/game-won/" + deviceId, json)
  }

  protected[this] def onGameResigned(message: GameLost, occurred: Long) = {
    val event = GameResignedEvent(
      deviceId = deviceId,
      sessionId = sessionId,
      message = message,
      requests = getRequests,
      occurred = occurred
    )
    val json = BaseSerializers.write(writeJs(event))
    sendNetworkPost("/a/game-resigned/" + deviceId, json)
  }

  private[this] lazy val deviceInfo = {
    import scala.scalajs.js.Dynamic.global

    val device = global.Phaser.Device.asInstanceOf[scalajs.js.Dictionary[Any]]

    device.keys.flatMap { k =>
      device.get(k) match {
        case Some(x: Boolean) => Some(k -> x)
        case _ => None
      }
    }.toMap
  }
}
