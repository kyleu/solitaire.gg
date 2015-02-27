package models

import models.game.{Card, GameState}

sealed trait ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage

case class GameJoined(id: String, players: List[String], state: GameState) extends ResponseMessage

case class RevealCard(card: Card) extends ResponseMessage
case class CardMoved(card: String, source: String, target: String, targetIndex: Option[Int] = None, turnFaceUp: Boolean = false, turnFaceDown: Boolean = false) extends ResponseMessage
case class CancelCardMove(cards: List[String], source: String) extends ResponseMessage

case class MessageSet(messages: List[ResponseMessage]) extends ResponseMessage
