package network

import msg.req.{Ping, SocketRequestMessage}
import msg.rsp.{Pong, SocketResponseMessage}
import utils.{JsonMessageSerializers, Logging}

import scala.scalajs.js.timers.setTimeout

class NetworkService(debug: Boolean, handleMessage: (SocketResponseMessage) => Unit) {
  private[this] val loc = org.scalajs.dom.document.location

  private[this] val socketUrl = {
    val wsProtocol = if (loc.protocol == "https:") { "wss" } else { "ws" }
    s"$wsProtocol://${loc.host}/socket"
  }

  private[this] var latencyMs: Option[Int] = None

  private[this] val socket = new NetworkSocket(onSocketConnect, onSocketResponseMessage, onSocketError, onSocketClose)
  socket.open(socketUrl)

  private def sendPing(): Unit = {
    if (socket.isConnected) {
      sendMessage(Ping(System.currentTimeMillis))
    }
    setTimeout(10000)(sendPing())
  }

  setTimeout(10000)(sendPing())

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

  def sendMessage(sm: SocketRequestMessage): Unit = if (socket.isConnected) {
    val json = JsonMessageSerializers.writeRequestMessage(sm, debug)
    socket.send(json)
  } else {
    utils.Logging.info(s"Not connected, skipping message [${sm.getClass.getSimpleName}] send.")
  }

  protected[this] def onSocketResponseMessage(json: String): Unit = JsonMessageSerializers.readResponseMessage(json) match {
    case p: Pong => latencyMs = Some((System.currentTimeMillis - p.ts).toInt)
    case msg => handleMessage(msg)
  }
}
