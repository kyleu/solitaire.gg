package models

import java.util.UUID

import akka.actor.ActorRef
import models.settings.Settings
import org.joda.time.LocalDateTime

sealed trait InternalMessage

case class SocketStarted(userId: UUID, username: Option[String], socketId: UUID, conn: ActorRef) extends InternalMessage
case class SocketStopped(socketId: UUID) extends InternalMessage

case class CreateGame(rules: String, connectionId: UUID, seed: Option[Int], testGame: Boolean, settings: Settings) extends InternalMessage
case class GameStarted(id: UUID, gameService: ActorRef, started: LocalDateTime) extends InternalMessage
case class ConnectionGameJoin(id: UUID, connectionId: UUID, settings: Settings) extends InternalMessage
case class ConnectionGameObserve(id: UUID, connectionId: UUID, as: Option[UUID]) extends InternalMessage
case class GameStopped(id: UUID) extends InternalMessage

case object StopGame extends InternalMessage
case object StopGameIfEmpty extends InternalMessage

case class GameRequest(userId: UUID, message: GameMessage) extends InternalMessage

case object GetSystemStatus extends InternalMessage
case class SystemStatus(games: Seq[(UUID, Seq[(UUID, String)])], connections: Seq[(UUID, Option[String])]) extends InternalMessage

case class SendConnectionTrace(id: UUID) extends InternalMessage
case class SendClientTrace(id: UUID) extends InternalMessage
case class SendSocketTrace(id: UUID) extends InternalMessage
case class TraceResponse(id: UUID, data: Seq[(String, Any)]) extends InternalMessage
