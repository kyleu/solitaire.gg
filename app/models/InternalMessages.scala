package models

import akka.actor.ActorRef

sealed trait InternalMessage

case class ConnectionStarted(id: String, conn: ActorRef) extends InternalMessage
case class ConnectionStopped(id: String) extends InternalMessage

case class CreateGame(gameType: String, session: String, username: String, conn: ActorRef) extends InternalMessage
case class GameStarted(id: String, gameService: ActorRef) extends InternalMessage
case class GameStopped(id: String) extends InternalMessage

case object StopGameIfEmpty extends InternalMessage
case class GameRequest(session: String, username: String, message: GameMessage) extends InternalMessage

case object GetSystemStatus extends InternalMessage
case class SystemStatus(games: List[(String, List[String])], connections: List[(String, String)]) extends InternalMessage
