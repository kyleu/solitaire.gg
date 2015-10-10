import java.util.UUID

trait SolitaireAnalyticsHelper extends SolitaireNetworkHelper {
  protected[this] def onInstall(deviceId: UUID, jsonBody: String) = {
    sendNetworkPost("/a/install/" + deviceId, jsonBody)
  }

  protected[this] def onGameStart(deviceId: UUID, jsonBody: String) = {
    sendNetworkPost("/a/game-start/" + deviceId, jsonBody)
  }

  protected[this] def onGameWon(deviceId: UUID, jsonBody: String) = {
    sendNetworkPost("/a/game-won/" + deviceId, jsonBody)
  }

  protected[this] def onGameResigned(deviceId: UUID, jsonBody: String) = {
    sendNetworkPost("/a/game-resigned/" + deviceId, jsonBody)
  }
}
