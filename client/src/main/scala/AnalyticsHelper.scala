import java.util.UUID

import json.BaseSerializers
import models.{ GameLost, GameWon }
import models.analytics._
import upickle.Js

import upickle.legacy._

import json.BaseSerializers._

trait AnalyticsHelper extends AjaxHelper {
  protected var gameStatus = "init"

  private[this] val sessionId = UUID.randomUUID

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

  private[this] def trimMessage(key: String, json: Js.Value) = json match {
    case o: Js.Obj => Js.Obj(o.value.map { x =>
      if (x._1 == key) {
        x._1 -> (x._2 match {
          case a: Js.Arr if a.value.length == 2 => a.value(1)
          case _ => throw new IllegalStateException(x.toString)
        })
      } else {
        x
      }
    }: _*)
    case _ => throw new IllegalStateException(json.toString)
  }

  protected[this] def onGameWon(message: GameWon, occurred: Long) = {
    val event = GameWonEvent(
      deviceId = deviceId,
      sessionId = sessionId,
      message = message,
      occurred = occurred
    )
    val json = BaseSerializers.write(trimMessage("message", writeJs(event)))
    sendNetworkPost("/a/game-won/" + deviceId, json)
  }

  protected[this] def onGameResigned(message: GameLost, occurred: Long) = {
    val event = GameResignedEvent(
      deviceId = deviceId,
      sessionId = sessionId,
      message = message,
      occurred = occurred
    )
    val json = BaseSerializers.write(trimMessage("message", writeJs(event)))
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
