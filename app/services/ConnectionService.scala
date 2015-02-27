package services

import java.util.UUID

import akka.actor.{Props, ActorRef}
import models._
import utils.{Logging, Config}
import utils.metrics.InstrumentedActor

object ConnectionService {
  def props(supervisor: ActorRef, out: ActorRef) = Props(new ConnectionService(supervisor, out))
}

class ConnectionService(supervisor: ActorRef, out: ActorRef) extends InstrumentedActor with Logging {
  val id = UUID.randomUUID.toString
  var username: Option[String] = None

  var activeGameId: Option[String] = None
  var activeGame: Option[ActorRef] = None

  override def preStart() = {
    supervisor ! ConnectionStarted(id, self)
  }

  override def receiveRequest = {
    // Incoming basic messages
    case mr: MalformedRequest => log.error("MalformedRequest:  [" + mr.reason + "]: [" + mr.content + "].")
    case p: Ping => out ! Pong(p.timestamp)
    case GetVersion => out ! VersionResponse(Config.version)

    // Incoming game messages
    case jg: JoinGame => handleJoinGame(jg.game, jg.name)
    case gm: GameMessage => handleGameMessage(gm)

    // Internal messages
    case gs: GameStarted => handleGameStarted(gs.id, gs.gameService)
    case gs: GameStopped => handleGameStopped(gs.id)

    // Outgoing messages
    case rm: ResponseMessage => handleResponseMessage(rm)

    case x => throw new IllegalArgumentException("Unhandled message [" + x.getClass.getSimpleName + "].")
  }

  override def postStop() = {
    activeGame.map(_ ! ConnectionStopped(id))
    supervisor ! ConnectionStopped(id)
  }

  private def handleJoinGame(gameType: String, username: String) {
    this.username = Some(username)
    supervisor ! CreateGame(gameType, id, username, self)
  }

  private def handleGameMessage(gm: GameMessage) = activeGame match {
    case Some(ag) => username match {
      case Some(un) => ag forward GameRequest(id, un, gm)
      case None => throw new IllegalArgumentException("Encountered game message [" + gm.getClass.getSimpleName + "] when not logged in.")
    }
    case None => throw new IllegalArgumentException("Unhandled game message [" + gm.getClass.getSimpleName + "].")
  }

  private def handleGameStarted(id: String, gameService: ActorRef) {
    activeGameId = Some(id)
    activeGame = Some(gameService)
  }

  private def handleGameStopped(id: String) {
    activeGameId = None
    activeGame = None
  }

  private def handleResponseMessage(rm: ResponseMessage) {
    out ! rm
  }
}
