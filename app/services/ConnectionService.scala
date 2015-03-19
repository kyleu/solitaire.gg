package services

import java.util.UUID

import akka.actor.{Props, ActorRef}
import models._
import play.api.libs.json.JsObject
import utils.{Logging, Config}
import utils.metrics.InstrumentedActor

object ConnectionService {
  def props(supervisor: ActorRef, username: String, out: ActorRef) = Props(new ConnectionService(supervisor, username, out))
}

class ConnectionService(supervisor: ActorRef, username: String, out: ActorRef) extends InstrumentedActor with Logging {
  val id = UUID.randomUUID

  var activeGameId: Option[UUID] = None
  var activeGame: Option[ActorRef] = None

  private var pendingDebugChannel: Option[ActorRef] = None

  override def preStart() = {
    supervisor ! ConnectionStarted(id, username, self)
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

    // Internal messages
    case gs: GameStarted => handleGameStarted(gs.id, gs.gameService)
    case gs: GameStopped => handleGameStopped(gs.id)
    case ct: ConnectionTrace => handleConnectionTrace()
    case ct: ClientTrace => handleClientTrace()

    // Outgoing messages
    case rm: ResponseMessage => handleResponseMessage(rm)

    case x => throw new IllegalArgumentException("Unhandled message [" + x.getClass.getSimpleName + "].")
  }

  override def postStop() = {
    activeGame.foreach(_ ! ConnectionStopped(id))
    supervisor ! ConnectionStopped(id)
  }

  private def handleStartGame(gameType: String, seed: Option[Int]) {
    supervisor ! CreateGame(gameType, id, seed)
  }

  private def handleJoinGame(gameId: UUID) = {
    supervisor ! ConnectionGameJoin(gameId, id)
  }

  private def handleObserveGame(gameId: UUID, as: Option[String]) = {
    supervisor ! ConnectionGameObserve(gameId, id, as)
  }

  private def handleGameMessage(gm: GameMessage) = activeGame match {
    case Some(ag) => ag forward GameRequest(id, username, gm)
    case None => throw new IllegalArgumentException("Received game message [" + gm.getClass.getSimpleName + "] while not in game.")
  }

  private def handleGameStarted(id: UUID, gameService: ActorRef) {
    activeGameId = Some(id)
    activeGame = Some(gameService)
  }

  private def handleGameStopped(id: UUID) {
    activeGameId = None
    activeGame = None
  }

  private def handleConnectionTrace() {
    val ret = TraceResponse(id, List(
      "username" -> username,
      "game" -> activeGameId.map( i => "<a href=\"" + controllers.routes.AdminController.traceGame(i) + "\" onclick=\"trace(this.href);return false;\">" + i + "</a>").getOrElse("Not in game")
    ))
    sender() ! ret
  }

  private def handleClientTrace() {
    pendingDebugChannel = Some(sender())
    out ! SendDebugInfo
  }

  private def handleDebugInfo(data: JsObject) = pendingDebugChannel match {
    case Some(dc) => dc ! TraceResponse(id, data.fields)
    case None => log.warn("Received unsolicited DebugInfo [" + data.toString + "] from [" + id + "].")
  }

  private def handleResponseMessage(rm: ResponseMessage) {
    out ! rm
  }
}
