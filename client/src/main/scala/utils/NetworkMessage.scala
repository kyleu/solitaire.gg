package utils

import models.RequestMessage

object NetworkMessage {
  private[this] var latencyMs: Option[Int] = None
  private[this] var latencyCallback: Option[Int => Unit] = None

  def setLatencyCallback(f: Int => Unit) = latencyCallback = Some(f)
  def setLatencyMs(ms: Int) = {
    latencyMs = Some(ms)
    latencyCallback.foreach(_(ms))
  }
  def getLatency = latencyMs.getOrElse(-1)

  var sentMessageCount = 0
  var receivedMessageCount = 0

  private[this] var sendF: Option[(RequestMessage) => Unit] = None

  def register(f: (RequestMessage) => Unit) = sendF match {
    case Some(dbf) => throw new IllegalStateException("Double registration.")
    case None => sendF = Some(f)
  }

  def sendMessage(requestMessage: RequestMessage) = sendF match {
    case Some(f) => f(requestMessage)
    case None => throw new IllegalStateException("Message send before start.")
  }
}
