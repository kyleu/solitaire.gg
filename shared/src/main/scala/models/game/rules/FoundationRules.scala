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
  name: String = "Foundation",
  numPiles: Int = 1,
  lowRank: FoundationLowRank = FoundationLowRank.DeckLowRank,
  initialCards: InitialCards = InitialCards.Count(0),
  suitMatchRule: SuitMatchRule =  SuitMatchRule.SameSuit,
  rankMatchRule: RankMatchRule = RankMatchRule.Up,
  wrapFromKingToAce: Boolean = false,
  moveCompleteSequencesOnly: Boolean = false,
  maxCards: Int = -1,
  canMoveFrom: FoundationCanMoveFrom = FoundationCanMoveFrom.Always,
  mayMoveToFrom: Seq[String] = GameRules.allSources,
  offscreen: Boolean = false,
  autoMoveCards: Boolean = false,
  autoMoveFrom: Seq[String] = GameRules.allSources
)
