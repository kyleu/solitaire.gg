package services.game

import akka.actor.PoisonPill
import models._

trait GameServiceMessageHelper { this: GameService =>
  protected[this] def handleStopGameIfEmpty() {
    val hasPlayer = player.connectionId.isDefined || observerConnections.exists(_._1.connectionId.isDefined)
    if (!hasPlayer) {
      self ! StopGame
    }
  }

  protected[this] def handleStopGame() {
    log.info(s"Stopping game [$id].")
    if (getStatus == "started") {
      setStatus("abandoned")
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
}
