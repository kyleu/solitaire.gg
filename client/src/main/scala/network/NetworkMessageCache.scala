package network

import msg.req.SocketRequestMessage

object NetworkMessageCache {
  def cache(msg: SocketRequestMessage) = {
    utils.Logging.info(s"Caching message [$msg].")
  }
}
