package models.game.rules

import models.game._

sealed trait FoundationLowRank
object FoundationLowRank {
  case object AnyCard extends FoundationLowRank
  case object DeckLowRank extends FoundationLowRank
  case object DeckHighRank extends FoundationLowRank
  case object Ascending extends FoundationLowRank
  case class SpecificRank(r: Rank) extends FoundationLowRank
}

sealed trait FoundationCanMoveFrom
object FoundationCanMoveFrom {
  case object Never extends FoundationCanMoveFrom
  case object Always extends FoundationCanMoveFrom
  case object EmptyStock extends FoundationCanMoveFrom
}

case class FoundationRules(
  name: String,
  numPiles: Int,
  lowRank: FoundationLowRank,
  initialCards: InitialCards,
  suitMatchRule: SuitMatchRule,
  rankMatchRule: RankMatchRule,
  wrapFromKingToAce: Boolean,
  moveCompleteSequencesOnly: Boolean,
  maxCards: Int,
  canMoveFrom: FoundationCanMoveFrom,
  mayMoveToFrom: Seq[String],
  offscreen: Boolean,
  autoMoveCards: Boolean,
  autoMoveFrom: Seq[String]
)
