package network

import msg.{Hello, SocketMessage}
import utils.{JsonSerializers, Logging}

import scala.scalajs.js.timers.setTimeout

class NetworkService(debug: Boolean, handleMessage: (SocketMessage) => Unit) {
  private[this] val loc = org.scalajs.dom.document.location

  private[this] val socketUrl = {
    val wsProtocol = if (loc.protocol == "https:") { "wss" } else { "ws" }
    s"$wsProtocol://${loc.host}/socket"
  }

  private[this] var latencyMs: Option[Int] = None

  private[this] val socket = new NetworkSocket(onSocketConnect, onSocketMessage, onSocketError, onSocketClose)
  socket.open(socketUrl)

  private def sendPing(): Unit = {
    sendMessage(Hello("!"))
    setTimeout(10000)(sendPing())
  }

  setTimeout(1000)(sendPing())

  protected[this] def onSocketConnect(): Unit = {
    Logging.info(s"Socket [$socketUrl] connected.")
  }

  protected[this] def onSocketError(error: String): Unit = {
    Logging.error(s"Socket error [$error].")
  }

  protected[this] def onSocketClose(): Unit = {
    utils.Logging.info("Socket closed. Skipping reconnect.")
    /*
    val callback = () => {
      Logging.info("Attempting to reconnect Websocket.")
      socket.open(NavigationService.socketUrl)
    }
    ReconnectManager.show(callback, NotificationService.getLastError match {
      case Some(e) => s"${e._1}: ${e._2}"
      case None => "The connection to the server was closed."
    })
    */
  }

  def sendMessage(sm: SocketMessage): Unit = {
    if (socket.isConnected) {
      val json = JsonSerializers.writeMessage(sm, debug)
      socket.send(json)
    } else {
      utils.Logging.info(s"Not connected, skipping message [${sm.getClass.getSimpleName}] send.")
    }
  }

  protected[this] def onSocketMessage(json: String): Unit = {
    val msg = JsonSerializers.readMessage(json)
    handleMessage(msg)
  }
}
