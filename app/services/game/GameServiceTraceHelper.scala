package services.game

import java.util.UUID

import models.TraceResponse
import org.joda.time.DateTime

trait GameServiceTraceHelper { this: GameService =>
  private val started = new DateTime()

  protected def handleGameTrace() {
    def connUrl(id: UUID) = controllers.routes.AdminController.traceConnection(id)
    val ret = TraceResponse(this.id, List(
      "variant" -> gameVariant.description.key,
      "seed" -> gameVariant.seed,
      "started" -> started,
      "connections" -> playerConnections.toList.map { x =>
        x._2.map(_._1) match {
          case Some(connId) =>
            connId + ":" + x._1 + "<br />" +
            " <a class=\"btn btn-default\" href=\"" + connUrl(connId) + "\" onclick=\"trace(this.href);return false;\">Trace</a>" +
            " <a class=\"btn btn-default\" href=\"" + connUrl(connId) + "\" target=\"_blank\">Observe as [" + x._1 + "]</a>"
          case None => x._1 + " (Disconnected)"
        }
      }.mkString("<br/>\n"),
      "observers" -> observerConnections.toList.map { x =>
        x._2.map(_._1) match {
          case Some(connId) => "<a href=\"" + connUrl(connId) + "\" onclick=\"trace(this.href);return false;\">" + connId + "</a> (" + x._1._1 + " as " + x._1._2.getOrElse("admin") + ")"
          case None => x._1 + " (Disconnected)"
        }
      }.mkString("<br/>\n"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }
}
