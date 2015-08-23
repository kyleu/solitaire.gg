package services.game

import akka.actor.PoisonPill
import models._
import services.history.GameHistoryService
import utils.DateUtils

trait GameServiceMessageHelper { this: GameService =>
  protected[this] def handleStopGameIfEmpty() {
    val hasPlayer = player.connectionId.isDefined || observerConnections.exists(_._1.connectionId.isDefined)
    if (!hasPlayer) {
      self ! StopGame
    }
  }

  protected[this] def handleStopGame() {
    log.info(s"Stopping game [$id].")
    if (completed.isEmpty) {
      completeGame(false)
    }
    context.parent ! GameStopped(id)
    self ! PoisonPill
  }

  protected[this] def sendToAll(context: String, messages: Seq[ResponseMessage]): Unit = {
    if (messages.isEmpty) {
      log.info(s"No messages to send to all players for game [$rules:$seed] in context [$context].")
    } else if (messages.tail.isEmpty) {
      sendToAll(context, messages.headOption.getOrElse(throw new IllegalStateException()))
    } else {
      sendToAll(context, MessageSet(messages))
    }
  }

  protected[this] def sendToAll(context: String, message: ResponseMessage, registerUndoResponse: Boolean = true): Unit = {
    if (registerUndoResponse) {
      undoHelper.registerResponse(message)
    }
    player.connectionActor.foreach(_ ! message)
    observerConnections.foreach { c =>
      c._1.connectionActor.foreach(_ ! message)
    }
  }

  protected[this] def handleGameRequest(gr: GameRequest) = {
    //log.debug("Handling [" + gr.message.getClass.getSimpleName.replace("$", "") + "] message from user [" + gr.userId + "] for game [" + id + "].")
    try {
      val time = DateUtils.now
      gameMessages += ((gr.message, gr.userId, time))
      moveCount += 1
      if (firstMoveMade.isEmpty) {
        firstMoveMade = Some(time)
        GameHistoryService.setFirstMove(id, time)
      }
      lastMoveMade = Some(time)
      update()
      gr.message match {
        case x if completed.isDefined => log.warn(s"Received game message [${x.getClass.getSimpleName}] for completed game [$id].")

        case GetPossibleMoves => timeReceive(GetPossibleMoves) { handleGetPossibleMoves(gr.userId) }

        case sc: SelectCard => timeReceive(sc) { handleSelectCard(gr.userId, sc.card, sc.pile) }
        case sp: SelectPile => timeReceive(sp) { handleSelectPile(gr.userId, sp.pile) }
        case mc: MoveCards => timeReceive(mc) { handleMoveCards(gr.userId, mc.cards, mc.src, mc.tgt) }

        case Undo => timeReceive(Undo) { handleUndo(gr.userId) }
        case Redo => timeReceive(Redo) { handleRedo(gr.userId) }

        case r => log.warn(s"GameService received unknown game message [${r.getClass.getSimpleName.replace("$", "")}].")
      }
    } catch {
      case x: Exception =>
        log.error(s"Exception processing game request [$gr].", x)
        sender() ! ServerError(x.getClass.getSimpleName, x.getMessage)
    }
  }

  protected[this] def handleInternalMessage(im: InternalMessage) = {
    //log.debug("Handling [" + im.getClass.getSimpleName.replace("$", "") + "] internal message for game [" + id + "].")
    try {
      im match {
        case ap: AddPlayer => timeReceive(ap) { handleAddPlayer(ap.userId, ap.name, ap.connectionId, ap.connectionActor, ap.autoFlipOption) }
        case ao: AddObserver => timeReceive(ao) { handleAddObserver(ao.userId, ao.name, ao.connectionId, ao.connectionActor, ao.as) }
        case cs: ConnectionStopped => timeReceive(cs) { handleConnectionStopped(cs.connectionId) }
        case StopGame => timeReceive(StopGame) { handleStopGame() }
        case StopGameIfEmpty => timeReceive(StopGameIfEmpty) { handleStopGameIfEmpty() }
        case gt: GameTrace => timeReceive(gt) { handleGameTrace() }
        case _ => log.warn(s"GameService received unhandled internal message [${im.getClass.getSimpleName}].")
      }
    } catch {
      case x: Exception => log.error(s"Exception processing internal message [$im].", x)
    }
  }
}
