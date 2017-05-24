package network

import msg.req.{ClientError, SocketRequestMessage}
import org.scalajs.dom
import utils.JsonSerializers

import scala.util.control.NonFatal

object NetworkMessageCache {
  private[this] val storageKey = "solitaire.gg.messages.pending"

  def flush(send: SocketRequestMessage => Unit) = {
    load().foreach(send)
    save(Nil)
  }

  def cache(msg: SocketRequestMessage) = {
    utils.Logging.info(s"Caching message [$msg].")
    save(load() :+ msg)
  }

  private[this] def save(msgs: Seq[SocketRequestMessage]) = if (msgs.isEmpty) {
    dom.window.localStorage.removeItem(storageKey)
  } else {
    val content = msgs.take(1000).map(msg => JsonSerializers.writeSocketRequestMessage(msg)).mkString("\n")
    dom.window.localStorage.setItem(storageKey, content)
  }

  private[this] def load() = Option(dom.window.localStorage.getItem(storageKey)) match {
    case Some(s) => s.split('\n').filterNot(_.isEmpty).map(json => try {
      JsonSerializers.readSocketRequestMessage(json)
    } catch {
      case NonFatal(x) => ClientError("parse", "Could not parse SocketRequestMessage json.", json)
    }).toSeq
    case None => Nil
  }
}
