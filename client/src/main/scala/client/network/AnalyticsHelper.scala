package client.network

import java.util.UUID

import models.analytics._
import models.{GameLost, GameWon}
import upickle.legacy._
import client.user.DataHelper

import scala.scalajs.js
import scala.util.control.NonFatal

trait AnalyticsHelper extends AjaxHelper {
  protected var gameStatus = "init"
  protected def getRequests: Seq[Seq[String]]

  if (DataHelper.newAccount) {
    onInstall(System.currentTimeMillis)
  } else {
    onOpen(System.currentTimeMillis)
  }

  protected[this] def onError(error: js.Dynamic, occurred: Long) = {
    val event = ErrorEvent(
      deviceId = DataHelper.deviceId,
      sessionId = DataHelper.sessionId,
      message = error.message.toString,
      url = error.url.toString,
      line = try { error.line.toString.toInt } catch { case NonFatal(x) => -1 },
      col = try { error.col.toString.toInt } catch { case NonFatal(x) => -1 },
      stack = Option(error.stack).map(_.toString),
      deviceInfo = DataHelper.deviceInfo,
      occurred = occurred
    )
    val json = write(writeJs(event))
    sendNetworkPost("/a/error/" + DataHelper.deviceId, json)
  }

  protected[this] def onInstall(occurred: Long) = {
    val event = InstallEvent(deviceId = DataHelper.deviceId, sessionId = DataHelper.sessionId, deviceInfo = DataHelper.deviceInfo, occurred = occurred)
    val json = write(writeJs(event))
    sendNetworkPost("/a/install/" + DataHelper.deviceId, json)
  }

  protected[this] def onOpen(occurred: Long) = {
    val event = OpenEvent(deviceId = DataHelper.deviceId, sessionId = DataHelper.sessionId, deviceInfo = DataHelper.deviceInfo, occurred = occurred)
    val json = write(writeJs(event))
    sendNetworkPost("/a/open/" + DataHelper.deviceId, json)
  }

  protected[this] def onGameStart(gameId: UUID, rules: String, seed: Int, occurred: Long) = {
    val event = GameStartEvent(
      deviceId = DataHelper.deviceId,
      sessionId = DataHelper.sessionId,
      gameId = gameId,
      rules = rules,
      occurred = occurred
    )
    val json = write(writeJs(event))
    sendNetworkPost("/a/game-start/" + DataHelper.deviceId, json)
  }

  protected[this] def onGameWon(message: GameWon, occurred: Long) = {
    val event = GameWonEvent(
      deviceId = DataHelper.deviceId,
      sessionId = DataHelper.sessionId,
      message = message,
      requests = getRequests,
      occurred = occurred
    )
    val json = write(writeJs(event))
    sendNetworkPost("/a/game-won/" + DataHelper.deviceId, json)
  }

  protected[this] def onGameResigned(message: GameLost, occurred: Long) = {
    val event = GameResignedEvent(
      deviceId = DataHelper.deviceId,
      sessionId = DataHelper.sessionId,
      message = message,
      requests = getRequests,
      occurred = occurred
    )
    val json = write(writeJs(event))
    sendNetworkPost("/a/game-resigned/" + DataHelper.deviceId, json)
  }
}
