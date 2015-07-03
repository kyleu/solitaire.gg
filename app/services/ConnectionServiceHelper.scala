package services

import java.util.UUID

import akka.actor.ActorRef
import models._
import org.joda.time.LocalDateTime
import play.api.libs.json.{ Json, JsObject }
import services.audit.ClientTraceService
import utils.metrics.InstrumentedActor

trait ConnectionServiceHelper extends InstrumentedActor { this: ConnectionService =>
  protected[this] def handleStartGame(gameType: String, seed: Option[Int], testGame: Boolean) {
    supervisor ! CreateGame(gameType, id, seed, testGame = testGame)
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

  protected[this] def handleDebugInfo(data: String) = if(data.startsWith("cheat")) {
    activeGame match {
      case Some(g) => g forward DebugInfo(data)
      case None => log.warn(s"Received DebugInfo [$data] from [$id], but no game exists.")
    }
  } else {
    pendingDebugChannel match {
      case Some(dc) =>
        val json = Json.parse(data).as[JsObject]
        ClientTraceService.persistTrace(userId, json)
        dc ! TraceResponse(id, json.fields)
      case None => log.warn(s"Received unsolicited DebugInfo [$data] from [$id].")
    }
  }

  protected[this] def handleResponseMessage(rm: ResponseMessage) {
    out ! rm
  }
}
