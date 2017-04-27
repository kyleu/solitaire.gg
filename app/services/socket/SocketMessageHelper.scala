package services.socket

import models.InternalMessage
import msg.{Hello, Howdy}
import utils.metrics.InstrumentedActor

trait SocketMessageHelper extends InstrumentedActor { this: SocketService =>
  override def receiveRequest = {
    case h: Hello => out ! Howdy(h.s.hashCode)
    case im: InternalMessage => handleInternalMessage(im)
    case x => throw new IllegalArgumentException(s"Unhandled request message [${x.getClass.getSimpleName}].")
  }

  private[this] def handleInternalMessage(im: InternalMessage) = im match {
    case x => throw new IllegalArgumentException(s"Unhandled internal message [${x.getClass.getSimpleName}].")
  }
}
