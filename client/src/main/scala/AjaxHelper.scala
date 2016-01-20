import java.util.UUID

import org.scalajs.dom.ext.Ajax

import scala.concurrent.ExecutionContext.Implicits.global

object AjaxHelper {
  case class PendingEvent(id: UUID, path: String, body: String, failureCount: Int = 1)
}

trait AjaxHelper {
  import AjaxHelper.PendingEvent
  import upickle.default._

  private[this] val st = org.scalajs.dom.localStorage
  private[this] var pendingEvents = Option(st.getItem("pending-events")) match {
    case Some(pe) => read[Seq[PendingEvent]](pe)
    case None => Seq.empty[PendingEvent]
  }

  private[this] var requestInFlight = false

  protected def sendPendingEvents() = pendingEvents.headOption.foreach { pe =>
    sendNetworkPost(pe.path, pe.body, Some(pe.id))
  }

  private[this] val urlBase = {
    val loc = org.scalajs.dom.document.location
    loc.protocol + "//" + loc.host
  }

  protected[this] def sendNetworkPost(path: String, body: String, savedId: Option[UUID] = None): Unit = {
    if (requestInFlight) {
      savePendingEvent(path, body)
    } else {
      val url = urlBase + path
      val timeout = 20 * 1000 // ms
      val headers = Map(
        "Content-Type" -> "application/json",
        "Accept" -> "application/json"
      )

      requestInFlight = true
      val f = Ajax("post", url, body, timeout, headers, withCredentials = true, "json")
      f.onSuccess {
        case x =>
          requestInFlight = false
          savedId.foreach { id =>
            pendingEvents = pendingEvents.filterNot(_.id == id)
            savePendingEvents()
          }
          sendPendingEvents()
      }
      f.onFailure {
        case x =>
          requestInFlight = false
          savedId match {
            case Some(id) => incrementFailureCount(id)
            case None => savePendingEvent(path, body)
          }
          sendPendingEvents()
      }
    }
  }

  private[this] def savePendingEvent(path: String, body: String) = {
    pendingEvents = pendingEvents :+ PendingEvent(id = UUID.randomUUID, path = path, body = body)
    savePendingEvents()
  }

  private[this] def incrementFailureCount(id: UUID) = {
    pendingEvents = pendingEvents.map {
      case pe if pe.id == id => pe.copy(failureCount = pe.failureCount + 1)
      case pe => pe
    }
    savePendingEvents()
  }

  private[this] def savePendingEvents() = st.setItem("pending-events", write(pendingEvents))
}
