package services.game

import models.TraceResponse
import org.joda.time.DateTime

trait GameServiceTraceHelper { this: GameService =>
  private val started = new DateTime()


  protected def handleGameTrace() {
    val ret = TraceResponse(this.id, List(
      "variant" -> gameVariant.description.id,
      "seed" -> gameVariant.seed,
      "started" -> started,
      "connections" -> connections.toList.sortBy(_._2._1).map(x => "[" + x._1 + ": " + x._2._1 + "]"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }
}
