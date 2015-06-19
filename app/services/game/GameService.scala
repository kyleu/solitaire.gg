package services.game

import java.util.UUID

import akka.actor.ActorRef
import models._
import models.game.rules.GameRulesSet
import models.game.rules.moves.InitialMoves
import org.joda.time.LocalDateTime

object GameService {
  case class PlayerRecord(userId: UUID, name: String, var connectionId: Option[UUID], var connectionActor: Option[ActorRef])
}

class GameService(
  val id: UUID, val rules: String, val seed: Int, val started: LocalDateTime, protected val player: GameService.PlayerRecord
) extends GameServiceHelper {
  log.info("Started game [" + rules + "] for user [" + player.userId + ": " + player.name + "] with seed [" + seed + "].")

  protected[this] val observerConnections = collection.mutable.ArrayBuffer.empty[(GameService.PlayerRecord, Option[UUID])]

  protected[this] val gameRules = GameRulesSet.allById(rules)
  protected[this] val gameState = gameRules.newGame(id, seed)

  gameState.addPlayer(player.userId, player.name)

  protected[this] val gameMessages = collection.mutable.ArrayBuffer.empty[(GameMessage, UUID, LocalDateTime)]
  protected[this] var moveCount = 0
  protected[this] var lastMoveMade: Option[LocalDateTime] = None
  protected[this] var gameWon = false

  private[this] var _status = "started"
  protected[this] def getStatus = _status
  protected[this] def setStatus(s: String) {
    _status = s
    this.update()
  }

  override def preStart() {
    this.create()
    InitialMoves.performInitialMoves(gameRules, gameState)

    player.connectionActor.foreach(_ ! GameStarted(id, self, started))
    player.connectionActor.foreach(_ ! GameJoined(id, gameState.view(player.userId), possibleMoves(Some(player.userId))))
  }

  override def receiveRequest = {
    case gr: GameRequest => handleGameRequest(gr)
    case im: InternalMessage => handleInternalMessage(im)
    case x => log.warn("GameService received unknown message [" + x.getClass.getSimpleName + "].")
  }

  private[this] def handleGameRequest(gr: GameRequest) = {
    //log.debug("Handling [" + gr.message.getClass.getSimpleName.replace("$", "") + "] message from user [" + gr.userId + "] for game [" + id + "].")
    try {
      val time = new LocalDateTime()
      gameMessages += ((gr.message, gr.userId, time))
      moveCount += 1
      lastMoveMade = Some(time)
      update()
      gr.message match {
        case GetPossibleMoves => timeReceive(GetPossibleMoves) { handleGetPossibleMoves(gr.userId) }
        case sc: SelectCard => timeReceive(sc) { handleSelectCard(gr.userId, sc.card, sc.pile) }
        case sp: SelectPile => timeReceive(sp) { handleSelectPile(gr.userId, sp.pile) }
        case mc: MoveCards => timeReceive(mc) { handleMoveCards(gr.userId, mc.cards, mc.src, mc.tgt) }
        case Undo => timeReceive(Undo) { handleUndo(gr.userId) }
        case Redo => timeReceive(Redo) { handleRedo(gr.userId) }
        case r => log.warn("GameService received unknown game message [" + r.getClass.getSimpleName.replace("$", "") + "].")
      }
    } catch {
      case x: Exception =>
        log.error("Exception processing game request [" + gr + "].", x)
        sender() ! ServerError(x.getClass.getSimpleName, x.getMessage)
    }
  }

  private[this] def handleInternalMessage(im: InternalMessage) = {
    //log.debug("Handling [" + im.getClass.getSimpleName.replace("$", "") + "] internal message for game [" + id + "].")
    try {
      im match {
        case ap: AddPlayer => timeReceive(ap) { handleAddPlayer(ap.userId, ap.name, ap.connectionId, ap.connectionActor) }
        case ao: AddObserver => timeReceive(ao) { handleAddObserver(ao.userId, ao.name, ao.connectionId, ao.connectionActor, ao.as) }
        case cs: ConnectionStopped => timeReceive(cs) { handleConnectionStopped(cs.connectionId) }
        case StopGame => timeReceive(StopGame) { handleStopGame() }
        case StopGameIfEmpty => timeReceive(StopGameIfEmpty) { handleStopGameIfEmpty() }
        case gt: GameTrace => timeReceive(gt) { handleGameTrace() }
        case _ => log.warn("GameService received unhandled internal message [" + im.getClass.getSimpleName + "].")
      }
    } catch {
      case x: Exception => log.error("Exception processing internal message [" + im + "].", x)
    }
  }
}
