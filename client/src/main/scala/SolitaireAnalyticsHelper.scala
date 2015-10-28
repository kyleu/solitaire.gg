import java.util.UUID

import scala.scalajs.js.JSON

trait SolitaireAnalyticsHelper extends SolitaireNetworkHelper {
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

  protected[this] def onGameWon(gameId: UUID, occurred: Long) = {
    val event = scalajs.js.Dynamic.literal(
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/game-won/" + deviceId, JSON.stringify(event))
  }

  protected[this] def onGameResigned(gameId: UUID, occurred: Long) = {
    val event = scalajs.js.Dynamic.literal(
      "occurred" -> System.currentTimeMillis
    )
    sendNetworkPost("/a/game-resigned/" + deviceId, JSON.stringify(event))
  }
}
