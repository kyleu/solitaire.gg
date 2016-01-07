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

  protected def sendPendingEvents() = getPendingEvents.headOption.foreach { pe =>
    sendNetworkPost(pe.path, pe.body, Some(pe.id))
  }

  protected[this] def sendNetworkPost(path: String, body: String, savedId: Option[UUID] = None): Unit = {
    val protocol = "http"
    val domain = "solitaire.dev"
    val url = protocol + "://" + domain + path
    val timeout = 20 * 1000 // ms
    val headers = Map(
      "Content-Type" -> "application/json",
      "Accept" -> "application/json"
    )

    val f = Ajax("post", url, body, timeout, headers, withCredentials = true, "json")
    f.onSuccess {
      case x => savedId.foreach { id =>
        val newPendingEvents = removePendingEvent(id)
        newPendingEvents.headOption.foreach(pe => sendNetworkPost(pe.path, pe.body, Some(pe.id)))
      }
    }
    f.onFailure {
      case x => savedId match {
        case Some(id) => incrementFailureCount(id)
        case None => savePendingEvent(path, body)
      }
    }
  }

  protected[this] def logPendingEvent() = {
    val evts = getPendingEvents
    evts.headOption.foreach { evt =>
      sendNetworkPost(evt.path, evt.body, Some(evt.id))
    }
  }

  private[this] def savePendingEvent(path: String, body: String) = {
    savePendingEvents(getPendingEvents :+ PendingEvent(id = UUID.randomUUID, path = path, body = body))
  }

  private[this] def incrementFailureCount(id: UUID) = savePendingEvents(getPendingEvents.map {
    case pe if pe.id == id => pe.copy(failureCount = pe.failureCount + 1)
    case pe => pe
  })

  private[this] def removePendingEvent(id: UUID) = {
    val newPendingEvents = getPendingEvents.filter(_.id == id)
    savePendingEvents(newPendingEvents)
    newPendingEvents
  }

  private[this] def getPendingEvents = Option(st.getItem("pending-events")) match {
    case Some(pe) => read[Seq[PendingEvent]](pe)
    case None => Seq.empty[PendingEvent]
  }

  private[this] def savePendingEvents(events: Seq[PendingEvent]) = st.setItem("pending-events", write(events))
}
