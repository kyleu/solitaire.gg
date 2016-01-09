import json.{ BaseSerializers, ResponseMessageSerializers }
import models._
import utils.NetworkSocket

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.timers._

trait NetworkHelper {
  val start = false

  protected[this] val socket = if (start) {
    val s = new NetworkSocket(onSocketConnect, onSocketMessage, onSocketError, onSocketClose)
    s.open()
    Some(s)
  } else {
    None
  }

  protected[this] var sendCallback: js.Function1[String, Unit] = _

  private def sendPing(): Unit = {
    socket.foreach { s =>
      if (s.connected) {
        s.send(s"""{ "c": "Ping", "v": { "timestamp": ${System.currentTimeMillis} } }""")
      }
    }
    setTimeout(10000)(sendPing())
  }

  setTimeout(1000)(sendPing())

  protected[this] def send(rm: ResponseMessage): Unit = {
    val json = ResponseMessageSerializers.write(rm)
    val s = BaseSerializers.write(json)
    sendCallback(s)
  }

  protected[this] def onSocketConnect(): Unit = {
  }

  protected[this] def onSocketError(error: String): Unit = {
  }

  protected[this] def onSocketClose(): Unit = {
  }

  protected[this] def onSocketMessage(s: String): Unit = {
    scala.scalajs.js.Dynamic.global.console.log(s"Message [$s] received from socket.") // TODO
  }
}
