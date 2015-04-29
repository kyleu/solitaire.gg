package models

import java.util.UUID

import akka.actor.ActorRef
import org.joda.time.LocalDateTime

sealed trait InternalMessage

case class ConnectionStarted(accountId: UUID, username: String, connectionId: UUID, conn: ActorRef) extends InternalMessage
case class ConnectionStopped(connectionId: UUID) extends InternalMessage

case class AddPlayer(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef) extends InternalMessage
case class AddObserver(accountId: UUID, name: String, connectionId: UUID, connectionActor: ActorRef, as: Option[UUID]) extends InternalMessage

case class CreateGame(rules: String, connectionId: UUID, seed: Option[Int]) extends InternalMessage
case class GameStarted(id: UUID, gameService: ActorRef, started: LocalDateTime) extends InternalMessage
case class ConnectionGameJoin(id: UUID, connectionId: UUID) extends InternalMessage
case class ConnectionGameObserve(id: UUID, connectionId: UUID, as: Option[UUID]) extends InternalMessage
case class GameStopped(id: UUID) extends InternalMessage

case class StopGame(reason: String) extends InternalMessage
case object StopGameIfEmpty extends InternalMessage

case class GameRequest(accountId: UUID, message: GameMessage) extends InternalMessage

case object GetSystemStatus extends InternalMessage
case class SystemStatus(games: Seq[(UUID, Seq[(UUID, String)])], connections: Seq[(UUID, String)]) extends InternalMessage

case class ConnectionTrace(id: UUID) extends InternalMessage
case class ClientTrace(id: UUID) extends InternalMessage
case class GameTrace(id: UUID) extends InternalMessage
case class TraceResponse(id: UUID, data: Seq[(String, Any)]) extends InternalMessage
