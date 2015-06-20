package services.game

import java.util.UUID

import models.TraceResponse
import org.joda.time.DateTime

trait GameServiceTraceHelper { this: GameService =>
  private[this] val started = new DateTime()

  protected[this] def handleGameTrace() {
    def connUrl(id: UUID) = controllers.admin.routes.TraceController.traceConnection(id).url

    val playerString = player.connectionId match {
      case Some(cId) =>
        val observeUrl = controllers.admin.routes.AdminController.observeGameAs(id, player.userId).url
        val traceLink = s"""<a class="btn btn-default" href="${connUrl(cId)}" class="trace-link">Trace Connection</a>"""
        val observeLink = s"""<a class="btn btn-default" href="$observeUrl" target="_blank">Observe game as [${player.name}]</a>"""
        s"User ID:${player.userId}<br />Connection ID: $cId<br />$traceLink$observeLink"
      case None => s"${player.userId.toString} (Disconnected)"
    }

    val ret = TraceResponse(this.id, List(
      "rules" -> gameRules.id,
      "seed" -> gameState.seed,
      "started" -> started,
      "player" -> playerString,
      "observers" -> observerConnections.map { x =>
        x._1.connectionId match {
          case Some(connId) => s"""<a href="${connUrl(connId)}" class="trace-link">$connId</a> (${x._1.name} as ${x._2.getOrElse("admin")})"""
          case None => s"${x._1.toString} (Disconnected)"
        }
      }.mkString("<br/>\n"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString()).getOrElse("none")
    ))
    sender() ! ret
  }
}
