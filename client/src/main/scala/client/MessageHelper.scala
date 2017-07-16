package client

import msg.rsp.{Profile, SocketResponseMessage}

trait MessageHelper { this: SolitaireGG =>
  protected[this] def handleSocketResponseMessage(msg: SocketResponseMessage) = msg match {
    case p: Profile => util.Logging.info(s"Profile: $p")
    case _ => util.Logging.info(s"Unhandled SocketResponseMessage: [$msg].")
  }
}
