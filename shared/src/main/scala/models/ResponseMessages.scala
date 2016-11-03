package models

import java.util.UUID

import models.card.Card
import models.game.GameState
import models.user.{UserPreferences, UserStatistics}

sealed trait ResponseMessage
trait ReversibleResponseMessage extends ResponseMessage

case class ServerError(reason: String, content: String) extends ResponseMessage
case class Notification(recipient: Option[UUID], message: String) extends ResponseMessage
case class Pong(timestamp: Long) extends ResponseMessage
case class VersionResponse(version: String) extends ResponseMessage
case object SendDebugInfo extends ResponseMessage
case class Disconnected(reason: String) extends ResponseMessage

case class GameJoined(id: UUID, state: GameState, elapsedMs: Int, moves: Seq[PossibleMove], preferences: UserPreferences) extends ResponseMessage

case class GameResult(moves: Int, undos: Int, redos: Int, score: Int, durationSeconds: Int, leaderboardRanking: Int)
case class GameLost(id: UUID, rules: String, result: GameResult, stats: UserStatistics) extends ResponseMessage
case class GameWon(id: UUID, rules: String, firstForRules: Boolean, firstForSeed: Boolean, result: GameResult, stats: UserStatistics) extends ResponseMessage

case class PossibleMove(moveType: String, cards: Seq[UUID], sourcePile: String, targetPile: Option[String] = None)
case class PossibleMoves(moves: Seq[PossibleMove], undosAvailable: Int, redosAvailable: Int) extends ResponseMessage

case class CardRevealed(card: Card) extends ReversibleResponseMessage
case class CardHidden(id: UUID) extends ReversibleResponseMessage

case class CardMoveCancelled(cards: Seq[UUID], source: String) extends ResponseMessage
case class CardMoved(card: UUID, source: String, target: String, turn: Option[Boolean] = None) extends ReversibleResponseMessage
case class CardsMoved(cards: Seq[UUID], source: String, target: String, turn: Option[Boolean] = None) extends ReversibleResponseMessage

case class MessageSet(messages: Seq[ResponseMessage]) extends ReversibleResponseMessage
