package services

import java.util.UUID

import akka.actor.{ Props, ActorRef }
import models._
import utils.{ Logging, Config }

object ConnectionService {
  def props(supervisor: ActorRef, accountId: UUID, name: String, out: ActorRef) = Props(new ConnectionService(supervisor, accountId, name, out))
}

class ConnectionService(val supervisor: ActorRef, val accountId: UUID, val name: String, val out: ActorRef) extends ConnectionServiceHelper with Logging {
  protected[this] val id = UUID.randomUUID

  protected[this] var activeGameId: Option[UUID] = None
  protected[this] var activeGame: Option[ActorRef] = None

  protected[this] var pendingDebugChannel: Option[ActorRef] = None

  override def preStart() = {
    supervisor ! ConnectionStarted(accountId, name, id, self)
  }

  override def receiveRequest = {
    // Incoming basic messages
    case mr: MalformedRequest => log.error("MalformedRequest:  [" + mr.reason + "]: [" + mr.content + "].")
    case p: Ping => out ! Pong(p.timestamp)
    case GetVersion => out ! VersionResponse(Config.version)
    case di: DebugInfo => handleDebugInfo(di.data)

    // Incoming game messages
    case sg: StartGame => handleStartGame(sg.variant, sg.seed)
    case jg: JoinGame => handleJoinGame(jg.id)
    case og: ObserveGame => handleObserveGame(og.id, og.as)
    case gm: GameMessage => handleGameMessage(gm)

    case im: InternalMessage => handleInternalMessage(im)

    // Outgoing messages
    case rm: ResponseMessage => handleResponseMessage(rm)

    case x => throw new IllegalArgumentException("Unhandled message [" + x.getClass.getSimpleName + "].")
  }

  override def postStop() = {
    activeGame.foreach(_ ! ConnectionStopped(id))
    supervisor ! ConnectionStopped(id)
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case gs: GameStarted => handleGameStarted(gs.id, gs.gameService)
    case gs: GameStopped => handleGameStopped(gs.id)
    case ct: ConnectionTrace => handleConnectionTrace()
    case ct: ClientTrace => handleClientTrace()
    case x => throw new IllegalArgumentException("Unhandled internal message [" + x.getClass.getSimpleName + "].")
  }
}
