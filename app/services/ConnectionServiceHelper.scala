package services

import java.util.UUID

import akka.actor.ActorRef
import models._
import org.joda.time.LocalDateTime
import play.api.libs.json.{ Json, JsObject }
import utils.metrics.InstrumentedActor

trait ConnectionServiceHelper extends InstrumentedActor { this: ConnectionService =>
  protected[this] def handleStartGame(gameType: String, seed: Option[Int]) {
    supervisor ! CreateGame(gameType, id, seed)
  }

  protected[this] def handleJoinGame(gameId: UUID) = {
    supervisor ! ConnectionGameJoin(gameId, id)
  }

  protected[this] def handleObserveGame(gameId: UUID, as: Option[UUID]) = {
    supervisor ! ConnectionGameObserve(gameId, id, as)
  }

  protected[this] def handleGameMessage(gm: GameMessage) = activeGame match {
    case Some(ag) => ag forward GameRequest(userId, gm)
    case None => throw new IllegalArgumentException(s"Received game message [${gm.getClass.getSimpleName}] while not in game.")
  }

  protected[this] def handleGameStarted(id: UUID, gameService: ActorRef, started: LocalDateTime) {
    activeGameId = Some(id)
    activeGame = Some(gameService)
  }

  protected[this] def handleGameStopped(id: UUID) {
    if (!activeGameId.contains(id)) {
      throw new IllegalStateException(s"Provided game [$id] is not the active game.")
    }
    activeGameId = None
    activeGame = None
  }

  protected[this] def handleConnectionTrace() {
    val ret = TraceResponse(id, List(
      "userId" -> userId,
      "name" -> name,
      "game" -> activeGameId.map { i =>
        s"""<a href="${controllers.admin.routes.TraceController.traceGame(i)}" class="trace-link">$i</a>"""
      }.getOrElse("Not in game")
    ))
    sender() ! ret
  }

  protected[this] def handleClientTrace() {
    pendingDebugChannel = Some(sender())
    out ! SendDebugInfo
  }

  protected[this] def handleDebugInfo(data: String) = pendingDebugChannel match {
    case Some(dc) => dc ! TraceResponse(id, Json.parse(data).as[JsObject].fields)
    case None => log.warn(s"Received unsolicited DebugInfo [$data] from [$id].")
  }

  protected[this] def handleResponseMessage(rm: ResponseMessage) {
    out ! rm
  }
}
