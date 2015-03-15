package models

import java.util.UUID

import models.game.{Card, GameState}

sealed trait ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage
case object SendDebugInfo extends ResponseMessage

case class GameJoined(id: UUID, players: Seq[String], state: GameState) extends ResponseMessage
case class GameLost(id: UUID) extends ResponseMessage
case class GameWon(id: UUID) extends ResponseMessage

case class CardRevealed(card: Card) extends ResponseMessage
case class CardMoved(card: UUID, source: String, target: String, targetIndex: Option[Int] = None, turnFaceUp: Boolean = false, turnFaceDown: Boolean = false) extends ResponseMessage
case class CardRemoved(card: UUID) extends ResponseMessage
case class CardMoveCancelled(cards: Seq[UUID], source: String) extends ResponseMessage

case class MessageSet(messages: Seq[ResponseMessage]) extends ResponseMessage
