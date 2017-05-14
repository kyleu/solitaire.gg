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

case object GetSystemStatus extends InternalMessage
case class SystemStatus(connections: Seq[(UUID, Option[String])]) extends InternalMessage
