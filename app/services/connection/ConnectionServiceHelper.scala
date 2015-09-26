package services.connection

import java.util.UUID

import akka.actor.ActorRef
import models._
import org.joda.time.LocalDateTime
import utils.Logging
import utils.metrics.InstrumentedActor

trait ConnectionServiceHelper
    extends InstrumentedActor
    with ConnectionServicePreferenceHelper
    with ConnectionServiceTraceHelper
    with Logging { this: ConnectionService =>
  protected[this] def handleStartGame(gameType: String, seed: Option[Int], testGame: Boolean) {
    supervisor ! CreateGame(gameType, id, seed, testGame = testGame, preferences = user.preferences)
  }

  protected[this] def handleJoinGame(gameId: UUID) = {
    supervisor ! ConnectionGameJoin(gameId, id, user.preferences)
  }

  protected[this] def handleObserveGame(gameId: UUID, as: Option[UUID]) = {
    supervisor ! ConnectionGameObserve(gameId, id, as)
  }

  protected[this] def handleGameMessage(gm: GameMessage) = activeGame match {
    case Some(ag) => ag forward GameRequest(user.id, gm)
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

  protected[this] def handleResponseMessage(rm: ResponseMessage) {
    out ! rm
  }
}
