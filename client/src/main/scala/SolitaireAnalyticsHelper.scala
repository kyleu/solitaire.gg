import java.util.UUID

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

  if(newAccount) {
    onInstall("{ \"occurred\": " + System.currentTimeMillis + " }")
  } else {
    onOpen("{ \"occurred\": " + System.currentTimeMillis + " }")
  }

  protected[this] def onInstall(jsonBody: String) = {
    sendNetworkPost("/a/install/" + deviceId, jsonBody)
  }

  protected[this] def onOpen(jsonBody: String) = {
    sendNetworkPost("/a/open/" + deviceId, jsonBody)
  }

  protected[this] def onGameStart(jsonBody: String) = {
    sendNetworkPost("/a/game-start/" + deviceId, jsonBody)
  }

  protected[this] def onGameWon(jsonBody: String) = {
    sendNetworkPost("/a/game-won/" + deviceId, jsonBody)
  }

  protected[this] def onGameResigned(jsonBody: String) = {
    sendNetworkPost("/a/game-resigned/" + deviceId, jsonBody)
  }
}
