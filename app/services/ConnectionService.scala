package services

import akka.actor.{Props, ActorRef}
import models._
import utils.{Logging, Config}
import utils.metrics.InstrumentedActor

object ConnectionService {
  def props(out: ActorRef) = Props(new ConnectionService(out))
}

class ConnectionService(out: ActorRef) extends InstrumentedActor with Logging {
  var username = "unknown"
  var activeGame: Option[ActorRef] = None

  override def receiveRequest = {
    case mr: MalformedRequest => log.error("MalformedRequest:  [" + mr.reason + "]: [" + mr.content + "].")
    case p: Ping => out ! Pong(p.timestamp)
    case GetVersion => out ! VersionResponse(Config.version)
    case jg: JoinGame => handleJoinGame(jg.game, jg.name)
    case x: RequestMessage => activeGame match {
      case Some(ag) => ag forward GameRequest(username, x)
      case None => throw new IllegalArgumentException("Unhandled message [" + x.getClass.getSimpleName + "].")
    }
  }

  def handleJoinGame(gameId: String, username: String) = {
    this.username = username
    activeGame = Some(context.actorOf(GameService.joinGame(out, gameId, username)))
  }
}
