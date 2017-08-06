package client

import msg.rsp.{Profile, SocketResponseMessage}

trait MessageHelper { this: SolitaireGG =>
  protected[this] def handleSocketResponseMessage(msg: SocketResponseMessage) = msg match {
    case p: Profile => logger.info(s"Profile: $p")
    case _ => logger.info(s"Unhandled SocketResponseMessage: [$msg].")
  }
}
