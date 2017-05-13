package services.socket

import models.InternalMessage
import msg.req.Ping
import msg.rsp.Pong
import utils.metrics.InstrumentedActor

trait SocketRequestMessageHelper extends InstrumentedActor { this: SocketService =>
  override def receiveRequest = {
    case p: Ping => out ! Pong(p.ts)
    case im: InternalMessage => handleInternalMessage(im)
    case x => throw new IllegalArgumentException(s"Unhandled request message [${x.getClass.getSimpleName}].")
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case x => throw new IllegalArgumentException(s"Unhandled internal message [${x.getClass.getSimpleName}].")
  }
}
