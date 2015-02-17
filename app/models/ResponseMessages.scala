package models

import models.game.Deck

sealed trait ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage
case class GameJoined(id: String, deck: Deck) extends ResponseMessage
