package models.rules

import models.card.{Rank, Suit, Color}

sealed trait FoundationInitialCardRestriction
object FoundationInitialCardRestriction {
  case object UniqueColors extends FoundationInitialCardRestriction
  case object UniqueSuits extends FoundationInitialCardRestriction
  case class SpecificColorUniqueSuits(c: Color) extends FoundationInitialCardRestriction
  case class SpecificSuit(s: Suit) extends FoundationInitialCardRestriction
}

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
  setNumber: Int = 0,
  numPiles: Int = 1,
  cardsShown: Int = 1,
  lowRank: FoundationLowRank = FoundationLowRank.DeckLowRank,
  initialCardRestriction: Option[FoundationInitialCardRestriction] = None,
  initialCards: Int = 0,
  suitMatchRule: SuitMatchRule = SuitMatchRule.SameSuit,
  rankMatchRule: RankMatchRule = RankMatchRule.Up,
  wrap: Boolean = true,
  moveCompleteSequencesOnly: Boolean = false,
  maxCards: Int = -1,
  canMoveFrom: FoundationCanMoveFrom = FoundationCanMoveFrom.Always,
  mayMoveToFrom: Seq[String] = GameRules.allSources,
  visible: Boolean = true,
  autoMoveCards: Boolean = false,
  autoMoveFrom: Seq[String] = GameRules.allSources
)
