package network

import msg.req.{Ping, SocketRequestMessage}
import msg.rsp.{Pong, SocketResponseMessage}
import util.{JsonSerializers, Logging}

import scala.scalajs.js.timers.setTimeout

class NetworkService(debug: Boolean, handleMessage: (SocketResponseMessage) => Unit) {
  private[this] val loc = org.scalajs.dom.document.location

  private[this] val socketUrl = if (loc.host == "localhost:5000") {
    "ws://localhost:5000/socket"
  } else {
    "wss://solitaire.gg/socket"
  }

  private[this] var latencyMs: Option[Int] = None

  private[this] val socket = new NetworkSocket(onSocketConnect _, onSocketResponseMessage, onSocketError, onSocketClose _)
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
    NetworkMessageCache.flush(sendMessage)
  }

  protected[this] def onSocketError(error: String): Unit = {
    Logging.error(s"Socket error [$error].")
  }

  protected[this] def onSocketClose(): Unit = {
    Logging.info("Socket closed. Skipping reconnect.")
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
    val json = JsonSerializers.writeSocketRequestMessage(sm, debug)
    socket.send(json)
  } else {
    Logging.info(s"Not connected, skipping message [${sm.getClass.getSimpleName}] send.")
    NetworkMessageCache.cache(sm)
  }

  protected[this] def onSocketResponseMessage(json: String): Unit = JsonSerializers.readSocketResponseMessage(json) match {
    case p: Pong => latencyMs = Some((System.currentTimeMillis - p.ts).toInt)
    case msg => handleMessage(msg)
  }
}
