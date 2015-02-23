package services

import akka.actor.{Props, ActorRef}
import models._
import play.api.Logger
import utils.Config
import utils.metrics.InstrumentedActor

object ConnectionService {
  def props(out: ActorRef) = Props(new ConnectionService(out))
}

class ConnectionService(out: ActorRef) extends InstrumentedActor {
  var username = "unknown"
  var activeGame: Option[ActorRef] = None

  override def receiveRequest = {
    case mr: MalformedRequest => Logger.error("Error parsing json [" + mr.reason + "]: " + mr.content + ".")
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
