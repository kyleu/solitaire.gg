package models

import java.util.UUID

import akka.actor.ActorRef

case class PlayerRecord(accountId: UUID, name: String, var connectionId: Option[UUID], var connectionActor: Option[ActorRef])
