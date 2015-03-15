package models

import java.util.UUID

import akka.actor.ActorRef

sealed trait InternalMessage

case class ConnectionStarted(id: UUID, username: String, conn: ActorRef) extends InternalMessage
case class ConnectionStopped(id: UUID) extends InternalMessage

case class AddPlayer(id: UUID, name: String, actorRef: ActorRef) extends InternalMessage
case class AddObserver(id: UUID, name: String, as: Option[String], actorRef: ActorRef) extends InternalMessage

case class CreateGame(variant: String, connectionId: UUID, seed: Option[Int]) extends InternalMessage
case class GameStarted(id: UUID, gameService: ActorRef) extends InternalMessage
case class ConnectionGameJoin(id: UUID, connectionId: UUID) extends InternalMessage
case class ConnectionGameObserve(id: UUID, connectionId: UUID, as: Option[String]) extends InternalMessage
case class GameStopped(id: UUID) extends InternalMessage

case object StopGameIfEmpty extends InternalMessage
case class GameRequest(connectionId: UUID, username: String, message: GameMessage) extends InternalMessage

case object GetSystemStatus extends InternalMessage
case class SystemStatus(games: Seq[(UUID, Seq[(UUID, String)])], connections: Seq[(UUID, String)]) extends InternalMessage

case class ConnectionTrace(id: UUID) extends InternalMessage
case class ClientTrace(id: UUID) extends InternalMessage
case class GameTrace(id: UUID) extends InternalMessage
case class TraceResponse(id: UUID, data: Seq[(String, Any)]) extends InternalMessage
