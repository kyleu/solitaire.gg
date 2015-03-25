package services.game

import java.util.UUID

import models.TraceResponse
import org.joda.time.DateTime

trait GameServiceTraceHelper { this: GameService =>
  private val started = new DateTime()

  protected def handleGameTrace() {
    def connUrl(id: UUID) = controllers.routes.AdminController.traceConnection(id).url
    val ret = TraceResponse(this.id, List(
      "variant" -> gameVariant.description.key,
      "seed" -> gameVariant.seed,
      "started" -> started,
      "connections" -> playerConnections.map { x =>
        x.connectionId match {
          case Some(connId) =>
            "Connection ID: " + connId + "<br>" +
            "Account ID:" + x.accountID + "<br />" +
            " <a class=\"btn btn-default\" href=\"" + connUrl(connId) + "\" class=\"trace-link\">Trace Connection</a>" +
            " <a class=\"btn btn-default\" href=\"" + controllers.routes.AdminController.observeGameAs(id, x.accountID).url + "\" target=\"_blank\">Observe game as [" + x.name + "]</a>"
          case None => x.accountID + " (Disconnected)"
        }
      }.mkString("<br/>\n"),
      "observers" -> observerConnections.map { x =>
        x._1.connectionId match {
          case Some(connId) => "<a href=\"" + connUrl(connId) + "\" class=\"trace-link\">" + connId + "</a> (" + x._1.name + " as " + x._2.getOrElse("admin") + ")"
          case None => x._1 + " (Disconnected)"
        }
      }.mkString("<br/>\n"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }
}
