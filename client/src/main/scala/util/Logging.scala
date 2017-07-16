package util

import org.scalajs.dom
import org.scalajs.dom.raw.Event

import scala.scalajs.js.Dynamic.global

object Logging {
  private[this] var showDebug = false

  def setDebugEnabled(b: Boolean) = showDebug = b

  def logJs(o: scalajs.js.Any) = {
    global.window.debug = o
    global.console.log(o)
  }

  def debug(msg: String): Unit = if (showDebug) {
    global.console.debug(msg)
  }
  def info(msg: String): Unit = {
    global.console.info(msg)
  }
  def warn(msg: String): Unit = {
    global.console.warn(msg)
  }
  def error(msg: String): Unit = {
    global.console.error(msg)
  }

  def installErrorHandler() = {
    if (showDebug) {
      dom.window.onerror = (e: Event, source: String, lineno: Int, colno: Int) => {
        info(s"Script error [$e] encountered in [$source:$lineno:$colno]")
        error(e.toString)
      }
    }
  }
}
