package services

import akka.actor.{Props, ActorRef}
import models._
import utils.Config
import utils.metrics.InstrumentedActor

object ConnectionService {
  def props(out: ActorRef) = Props(new ConnectionService(out))
}

class ConnectionService(out: ActorRef) extends InstrumentedActor {
  var activeGame: Option[ActorRef] = None

  override def receiveRequest = {
    case mr: MalformedRequest => out ! ServerError("MalformedRequest", mr.content)
    case p: Ping => out ! Pong(p.timestamp)
    case GetVersion => out ! VersionResponse(Config.version)
    case jg: JoinGame => activeGame = Some(context.actorOf(GameService.joinGame(out, jg)))
    case x => activeGame match {
      case Some(ag) => ag forward x
      case None => throw new IllegalArgumentException("Unhandled message [" + x.getClass.getSimpleName + "].")
    }
  }
}
