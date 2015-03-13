package services.game

import models.TraceResponse
import org.joda.time.DateTime

trait GameServiceTraceHelper { this: GameService =>
  private val started = new DateTime()


  protected def handleGameTrace() {
    def connUrl(id: String) = controllers.routes.AdminController.traceConnection(id)
    val ret = TraceResponse(this.id, List(
      "variant" -> gameVariant.description.id,
      "seed" -> gameVariant.seed,
      "started" -> started,
      "connections" -> playerConnections.toList.sortBy(_._2._1).map { x =>
        "<a href=\"" + connUrl(x._1) + "\" onclick=\"trace(this.href);return false;\">" + x._1 + "</a> (" + x._2._1 + ")"
      }.mkString("<br/>\n"),
      "observers" -> observerConnections.toList.sortBy(_._2._1).map { x =>
        "<a href=\"" + connUrl(x._1) + "\" onclick=\"trace(this.href);return false;\">" + x._1 + "</a> (" + x._2._1 + " as " + x._2._2.getOrElse("admin") + ")"
      }.mkString("<br/>\n"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }
}
