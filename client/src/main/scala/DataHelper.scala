import java.util.UUID

import scala.util.control.NonFatal

object DataHelper {
  val sessionId = UUID.randomUUID

  private[this] val st = org.scalajs.dom.window.localStorage

  def newId = {
    val id = UUID.randomUUID
    st.setItem("device", id.toString)
    id -> true
  }

  val (deviceId, newAccount) = Option(st.getItem("device")) match {
    case Some(deviceIdString) => try {
      UUID.fromString(deviceIdString) -> false
    } catch {
      case NonFatal(x) => newId
    }
    case None => newId
  }

  lazy val deviceInfo = {
    import scala.scalajs.js.Dynamic.global

    val device = global.Phaser.Device.asInstanceOf[scalajs.js.Dictionary[Any]]

    device.keys.flatMap { k =>
      device.get(k) match {
        case Some(x: Boolean) => Some(k -> x)
        case _ => None
      }
    }.toMap
  }
}
