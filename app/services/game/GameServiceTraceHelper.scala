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
      "connections" -> playerConnections.toList.sortBy(_._2._1).map(x => "[" + x._1 + ": " + x._2._1 + "]"),
      "observers" -> observerConnections.toList.sortBy(_._2._1).map(x => "[" + x._1 + ": " + x._2._1 + " (as " + x._2._2.getOrElse("admin") + ")]"),
      "gameMessageCount" -> gameMessages.size,
      "lastMessage" -> gameMessages.lastOption.map(_.toString).getOrElse("none")
    ))
    sender() ! ret
  }
}
