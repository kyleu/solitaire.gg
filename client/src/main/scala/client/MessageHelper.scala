package client

import msg.rsp.{Profile, SocketResponseMessage}
import util.Logging

trait MessageHelper { this: SolitaireGG =>
  protected[this] def handleSocketResponseMessage(msg: SocketResponseMessage) = msg match {
    case p: Profile => Logging.info(s"Profile: $p")
    case _ => Logging.info(s"Unhandled SocketResponseMessage: [$msg].")
  }
}
