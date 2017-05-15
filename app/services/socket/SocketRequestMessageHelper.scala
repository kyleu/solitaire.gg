package services.socket

import models.InternalMessage
import msg.req.{Ping, SaveSettings}
import msg.rsp.Pong
import services.user.UserService
import utils.metrics.InstrumentedActor

trait SocketRequestMessageHelper extends InstrumentedActor { this: SocketService =>
  override def receiveRequest = {
    case p: Ping => out ! Pong(p.ts)
    case ss: SaveSettings => UserService.saveSettings(user.id, ss.settings)
    case im: InternalMessage => handleInternalMessage(im)
    case x => throw new IllegalArgumentException(s"Unhandled socket request message [${x.getClass.getSimpleName}].")
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case x => throw new IllegalArgumentException(s"Unhandled internal message [${x.getClass.getSimpleName}].")
  }
}
