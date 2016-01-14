package models

import java.util.UUID

import akka.actor.ActorRef
import models.user.UserPreferences
import org.joda.time.LocalDateTime

sealed trait InternalMessage

case class CreateGame(rules: String, connectionId: UUID, seed: Option[Int], testGame: Boolean, preferences: UserPreferences) extends InternalMessage
case class GameStarted(id: UUID, gameService: ActorRef, started: LocalDateTime) extends InternalMessage
case class ConnectionGameJoin(id: UUID, connectionId: UUID, preferences: UserPreferences) extends InternalMessage
case class ConnectionGameObserve(id: UUID, connectionId: UUID, as: Option[UUID]) extends InternalMessage
case class GameStopped(id: UUID) extends InternalMessage

case object StopGame extends InternalMessage
case object StopGameIfEmpty extends InternalMessage

case class GameRequest(userId: UUID, message: GameMessage) extends InternalMessage

case object GetSystemStatus extends InternalMessage
case class SystemStatus(games: Seq[(UUID, Seq[(UUID, String)])], connections: Seq[(UUID, String)]) extends InternalMessage

case class ConnectionTrace(id: UUID) extends InternalMessage
case class ClientTrace(id: UUID) extends InternalMessage
case class GameTrace(id: UUID) extends InternalMessage
case class TraceResponse(id: UUID, data: Seq[(String, Any)]) extends InternalMessage
