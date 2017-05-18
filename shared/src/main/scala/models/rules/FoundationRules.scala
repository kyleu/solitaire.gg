package models.rules

import enumeratum.values._
import models.card.{Color, Rank, Suit}

sealed abstract class FoundationInitialCardRestriction(val value: Int) extends IntEnumEntry
object FoundationInitialCardRestriction extends IntEnum[FoundationInitialCardRestriction] with IntCirceEnum[FoundationInitialCardRestriction] {
  case object UniqueColors extends FoundationInitialCardRestriction(1)
  case object UniqueSuits extends FoundationInitialCardRestriction(2)
  case class SpecificColorUniqueSuits(c: Color) extends FoundationInitialCardRestriction(3)
  case class SpecificSuit(s: Suit) extends FoundationInitialCardRestriction(4)
  override val values = findValues
}

sealed abstract class FoundationLowRank(val value: Int) extends IntEnumEntry
object FoundationLowRank extends IntEnum[FoundationLowRank] with IntCirceEnum[FoundationLowRank] {
  case object AnyCard extends FoundationLowRank(1)
  case object DeckLowRank extends FoundationLowRank(2)
  case object DeckHighRank extends FoundationLowRank(3)
  case object Ascending extends FoundationLowRank(4)
  case class SpecificRank(r: Rank) extends FoundationLowRank(5)
  override val values = findValues
}

sealed abstract class FoundationCanMoveFrom(val value: Int) extends IntEnumEntry
object FoundationCanMoveFrom extends IntEnum[FoundationCanMoveFrom] with IntCirceEnum[FoundationCanMoveFrom] {
  case object Never extends FoundationCanMoveFrom(1)
  case object Always extends FoundationCanMoveFrom(2)
  case object EmptyStock extends FoundationCanMoveFrom(3)
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
