package services.socket

import java.util.UUID

import akka.actor.{ActorRef, Props}
import models.{SocketStarted, SocketStopped}
import models.user.User
import msg.Howdy
import utils.Logging
import utils.metrics.InstrumentedActor

object SocketService {
  type i18n = (String, Seq[Any]) => String

  def props(id: Option[UUID], supervisor: ActorRef, user: User, out: ActorRef, sourceAddress: String, messages: i18n) = {
    Props(SocketService(id.getOrElse(UUID.randomUUID), supervisor, user, out, sourceAddress))
  }
}

case class SocketService(
    id: UUID, supervisor: ActorRef, user: User, out: ActorRef, sourceAddress: String
) extends InstrumentedActor with SocketMessageHelper with Logging {

  override def preStart() = {
    log.info(s"Starting connection for user [${user.id}: ${user.username.getOrElse("-")}].")
    supervisor ! SocketStarted(user.id, user.username, id, self)
    out ! Howdy(0) // TODO UserSettings(user.id, user.username, user.email, user.preferences)
  }

  override def postStop() = {
    supervisor ! SocketStopped(id)
  }
}
