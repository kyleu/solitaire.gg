package client

import msg.rsp.{Profile, SocketResponseMessage}

trait MessageHelper { this: SolitaireGG =>
  protected[this] def handleSocketResponseMessage(msg: SocketResponseMessage) = msg match {
    case p: Profile => utils.Logging.info(s"Profile: $p")
    case _ => utils.Logging.info(s"Unhandled SocketResponseMessage: [$msg].")
  }
}
