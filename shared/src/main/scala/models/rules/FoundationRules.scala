package models.rules

import enumeratum.values._
import models.card.{Color, Rank, Suit}

sealed abstract class FoundationInitialCardRestriction(val value: String) extends StringEnumEntry
object FoundationInitialCardRestriction extends StringEnum[FoundationInitialCardRestriction] with StringUPickleEnum[FoundationInitialCardRestriction] {
  case object UniqueColors extends FoundationInitialCardRestriction("unique-colors")
  case object UniqueSuits extends FoundationInitialCardRestriction("unique-suits")
  case class SpecificColorUniqueSuits(c: Color) extends FoundationInitialCardRestriction("specific-color-unique-suits")
  case class SpecificSuit(s: Suit) extends FoundationInitialCardRestriction("specific-suit")
  override val values = findValues
}

sealed abstract class FoundationLowRank(val value: String) extends StringEnumEntry
object FoundationLowRank extends StringEnum[FoundationLowRank] with StringUPickleEnum[FoundationLowRank] {
  case object AnyCard extends FoundationLowRank("any-card")
  case object DeckLowRank extends FoundationLowRank("deck-low-rank")
  case object DeckHighRank extends FoundationLowRank("deck-high-rank")
  case object Ascending extends FoundationLowRank("ascending")
  case class SpecificRank(r: Rank) extends FoundationLowRank("specific-rank")
  override val values = findValues
}

sealed abstract class FoundationCanMoveFrom(val value: String) extends StringEnumEntry
object FoundationCanMoveFrom extends StringEnum[FoundationCanMoveFrom] with StringUPickleEnum[FoundationCanMoveFrom] {
  case object Never extends FoundationCanMoveFrom("never")
  case object Always extends FoundationCanMoveFrom("always")
  case object EmptyStock extends FoundationCanMoveFrom("empty-stock")
  override val values = findValues
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
